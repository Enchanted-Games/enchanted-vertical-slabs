package games.enchanted.verticalslabs.mixin.slab_behaviours;

import games.enchanted.verticalslabs.block.vertical_slab.BaseVerticalSlabBlock;
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
    public static EnumProperty<WallSide> EAST_WALL;
    @Final  @Shadow
    public static EnumProperty<WallSide> NORTH_WALL;
    @Final @Shadow
    public static EnumProperty<WallSide> SOUTH_WALL;
    @Final @Shadow
    public static EnumProperty<WallSide> WEST_WALL;

    @Unique
    private boolean enchanted_vertical_slabs$isWallHorizontallyConnected(BlockState state) {
        return ((
            state.getValue(NORTH_WALL) != WallSide.NONE && state.getValue(SOUTH_WALL) != WallSide.NONE &&
            state.getValue(EAST_WALL) == WallSide.NONE && state.getValue(WEST_WALL) == WallSide.NONE
        ) ||
        (
            state.getValue(NORTH_WALL) == WallSide.NONE && state.getValue(SOUTH_WALL) == WallSide.NONE &&
            state.getValue(EAST_WALL) != WallSide.NONE && state.getValue(WEST_WALL) != WallSide.NONE
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
        if(state.getBlock() instanceof BaseVerticalSlabBlock) {
            boolean slabAwayFromWall = state.getValue(BaseVerticalSlabBlock.FACING) == side.getOpposite();
            cir.setReturnValue(!(state.getValue(BaseVerticalSlabBlock.SINGLE) && slabAwayFromWall));
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

        if ( aboveState.getBlock() instanceof BaseVerticalSlabBlock && aboveState.getValue(BaseVerticalSlabBlock.SINGLE) ) {
            cir.setReturnValue( state
                .setValue(NORTH_WALL, enchanted_vertical_slabs$getWallSideForConnection(north, blockState))
                .setValue(EAST_WALL, enchanted_vertical_slabs$getWallSideForConnection(east, blockState))
                .setValue(SOUTH_WALL, enchanted_vertical_slabs$getWallSideForConnection(south, blockState))
                .setValue(WEST_WALL, enchanted_vertical_slabs$getWallSideForConnection(west, blockState))
                .setValue(UP, !enchanted_vertical_slabs$isWallHorizontallyConnected(blockState))
            );
        }

    }
}