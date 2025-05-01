package games.enchanted.verticalslabs.mixin.slab_behaviours;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
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

@Mixin(WallBlock.class)
public abstract class WallBlockMixin {
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
    private boolean evs$isWallHorizontallyConnected(BlockState state) {
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
    private WallSide evs$getWallSideForConnection(boolean connected, BlockState state) {
        if (connected && !evs$isWallHorizontallyConnected(state)) {
            return WallSide.LOW;
        } else if (connected && evs$isWallHorizontallyConnected(state)) {
            return WallSide.TALL;
        }
        return WallSide.NONE;
    }

    @ModifyReturnValue(
        at = @At("RETURN"),
        method = "connectsTo(Lnet/minecraft/world/level/block/state/BlockState;ZLnet/minecraft/core/Direction;)Z"
    )
    // if side block is a vertical slab, connect to it unless vertical slab is "away" from the wall
    private boolean evs$connectWallToVerticalSlabs(boolean original, BlockState state, boolean sideSolid, Direction direction) {
        if(state.getBlock() instanceof BaseVerticalSlabBlock) {
            boolean slabAwayFromWall = state.getValue(BaseVerticalSlabBlock.FACING) == direction.getOpposite();
            return original || !(state.getValue(BaseVerticalSlabBlock.SINGLE) && slabAwayFromWall);
        }
        return original;
    }

    @ModifyReturnValue(
        at = @At("RETURN"),
        method = "updateShape(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;ZZZZ)Lnet/minecraft/world/level/block/state/BlockState;"
    )
    // connect walls up to vertical slabs above them
    private BlockState evs$connectWallUpToVerticalSlabAbove(BlockState original, LevelReader level, BlockState state, BlockPos pos, BlockState aboveState, boolean northConnection, boolean eastConnection, boolean southConnection, boolean westConnection) {
        if (!(aboveState.getBlock() instanceof BaseVerticalSlabBlock && aboveState.getValue(BaseVerticalSlabBlock.SINGLE))) return original;
        return original
            .setValue(NORTH_WALL, evs$getWallSideForConnection(northConnection, original))
            .setValue(EAST_WALL, evs$getWallSideForConnection(eastConnection, original))
            .setValue(SOUTH_WALL, evs$getWallSideForConnection(southConnection, original))
            .setValue(WEST_WALL, evs$getWallSideForConnection(westConnection, original))
            .setValue(UP, !evs$isWallHorizontallyConnected(original));
    }
}