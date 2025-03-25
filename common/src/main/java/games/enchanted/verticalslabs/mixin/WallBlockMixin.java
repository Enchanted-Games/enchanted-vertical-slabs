package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.block.VerticalSlabBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin {
    @Shadow
    protected abstract BlockState updateSides(BlockState state, boolean north, boolean east, boolean south, boolean west, VoxelShape aboveShape);

    @Final @Shadow
    public static BooleanProperty UP;
    @Final @Shadow
    public static EnumProperty<WallSide> EAST;
    @Final @Shadow
    public static EnumProperty<WallSide> NORTH;
    @Final @Shadow
    public static EnumProperty<WallSide> SOUTH;
    @Final @Shadow
    public static EnumProperty<WallSide> WEST;

    @Unique
    private boolean enchanted_vertical_slabs$isWallHorizontallyConnected(BlockState state) {
        return ((
            state.getValue(NORTH) != WallSide.NONE && state.getValue(SOUTH) != WallSide.NONE &&
            state.getValue(EAST) == WallSide.NONE && state.getValue(WEST) == WallSide.NONE
        ) ||
        (
            state.getValue(NORTH) == WallSide.NONE && state.getValue(SOUTH) == WallSide.NONE &&
            state.getValue(EAST) != WallSide.NONE && state.getValue(WEST) != WallSide.NONE
        ));
    }

    @Unique
    private WallSide enchanted_vertical_slabs$getWallSideForConnection(boolean connected, BlockState state) {
        if (connected && !enchanted_vertical_slabs$isWallHorizontallyConnected(state)) {
            return WallSide.LOW;
        } else if (connected && enchanted_vertical_slabs$isWallHorizontallyConnected(state)) {
            return WallSide.TALL;
        }
        return WallSide.NONE;
    }

    @Inject(
        at = @At("HEAD"),
        method = "connectsTo(Lnet/minecraft/world/level/block/state/BlockState;ZLnet/minecraft/core/Direction;)Z",
        cancellable = true
    )
    // if side block is a vertical slab, connect to it unless vertical slab is "away" from the wall
    private void connectsTo(BlockState state, boolean isFaceSturdy, Direction side, CallbackInfoReturnable<Boolean> cir) {
        if(state.getBlock() instanceof VerticalSlabBlock) {
            boolean slabAwayFromWall = state.getValue(VerticalSlabBlock.FACING) == side.getOpposite();
            cir.setReturnValue(!(state.getValue(VerticalSlabBlock.SINGLE) && slabAwayFromWall));
        }
    }

    @Inject(
        at = @At("TAIL"),
        cancellable = true,
        method = "updateShape(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;ZZZZ)Lnet/minecraft/world/level/block/state/BlockState;"
    )
    // connect walls up to vertical slabs above them
    private void updateShape(LevelReader world, BlockState state, BlockPos pos, BlockState aboveState, boolean north, boolean east, boolean south, boolean west, CallbackInfoReturnable<BlockState> cir) {
        VoxelShape voxelShape = aboveState.getCollisionShape(world, pos).getFaceShape(Direction.DOWN);
        BlockState blockState = this.updateSides(state, north, east, south, west, voxelShape);

        if ( aboveState.getBlock() instanceof VerticalSlabBlock && aboveState.getValue(VerticalSlabBlock.SINGLE) ) {
            cir.setReturnValue( state
                .setValue(NORTH, enchanted_vertical_slabs$getWallSideForConnection(north, blockState))
                .setValue(EAST, enchanted_vertical_slabs$getWallSideForConnection(east, blockState))
                .setValue(SOUTH, enchanted_vertical_slabs$getWallSideForConnection(south, blockState))
                .setValue(WEST, enchanted_vertical_slabs$getWallSideForConnection(west, blockState))
                .setValue(UP, !enchanted_vertical_slabs$isWallHorizontallyConnected(blockState))
            );
        }

    }
}