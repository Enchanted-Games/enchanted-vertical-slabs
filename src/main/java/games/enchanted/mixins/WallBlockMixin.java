package games.enchanted.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.EnumProperty;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import games.enchanted.registry.types.CombinableVerticalSlabBlock;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin {
    @Shadow
    abstract public BlockState getStateWith(BlockState state, boolean north, boolean east, boolean south, boolean west,
            VoxelShape aboveShape);

    @Shadow
    abstract public boolean shouldHavePost(BlockState state, BlockState aboveState, VoxelShape aboveShape);

    @Shadow
    public static final BooleanProperty UP = Properties.UP;

    @Shadow
    public static final EnumProperty<WallShape> EAST_SHAPE = Properties.EAST_WALL_SHAPE;
    @Shadow
    public static final EnumProperty<WallShape> NORTH_SHAPE = Properties.NORTH_WALL_SHAPE;
    @Shadow
    public static final EnumProperty<WallShape> SOUTH_SHAPE = Properties.SOUTH_WALL_SHAPE;
    @Shadow
    public static final EnumProperty<WallShape> WEST_SHAPE = Properties.WEST_WALL_SHAPE;
    @Shadow
    private static final VoxelShape TALL_POST_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
    @Shadow
    private static final VoxelShape TALL_NORTH_SHAPE = Block.createCuboidShape(7.0, 0.0, 0.0, 9.0, 16.0, 9.0);
    @Shadow
    private static final VoxelShape TALL_SOUTH_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 16.0);
    @Shadow
    private static final VoxelShape TALL_WEST_SHAPE = Block.createCuboidShape(0.0, 0.0, 7.0, 9.0, 16.0, 9.0);
    @Shadow
    private static final VoxelShape TALL_EAST_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 16.0, 16.0, 9.0);

    private boolean horizontallyConnected(BlockState state) {
        if ((state.get(NORTH_SHAPE) != WallShape.NONE && state.get(SOUTH_SHAPE) != WallShape.NONE
                && state.get(EAST_SHAPE) == WallShape.NONE && state.get(WEST_SHAPE) == WallShape.NONE) ||
                (state.get(NORTH_SHAPE) == WallShape.NONE && state.get(SOUTH_SHAPE) == WallShape.NONE
                        && state.get(EAST_SHAPE) != WallShape.NONE && state.get(WEST_SHAPE) != WallShape.NONE)) {
            return true;
        } else {
            return false;
        }
    }

    private WallShape isConnectedUseShape(boolean connected, BlockState blockState) {
        if (connected && !horizontallyConnected(blockState)) {
            return WallShape.LOW;
        } else if (connected && horizontallyConnected(blockState)) {
            return WallShape.TALL;
        }
        return WallShape.NONE;
    }

    @Inject(at = @At("TAIL"), cancellable = true, method = "Lnet/minecraft/block/WallBlock;getStateWith(Lnet/minecraft/world/WorldView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;ZZZZ)Lnet/minecraft/block/BlockState;")
    private BlockState getStateWith(WorldView world, BlockState state, BlockPos pos, BlockState aboveState,
            boolean north, boolean east, boolean south, boolean west,
            CallbackInfoReturnable infor) {

        VoxelShape voxelShape = aboveState.getCollisionShape(world, pos).getFace(Direction.DOWN);
        BlockState blockState = this.getStateWith(state, north, east, south, west, voxelShape);

        if (aboveState.getBlock() instanceof CombinableVerticalSlabBlock) {
            return (BlockState) ((BlockState) ((BlockState) ((BlockState) state.with(NORTH_SHAPE,
                    isConnectedUseShape(north, blockState)))
                    .with(EAST_SHAPE, isConnectedUseShape(east, blockState)))
                    .with(SOUTH_SHAPE, isConnectedUseShape(south, blockState)))
                    .with(WEST_SHAPE, isConnectedUseShape(west, blockState))
                    .with(UP, !horizontallyConnected(blockState));
        } else {
            return (BlockState) blockState.with(UP, this.shouldHavePost(blockState,
                    aboveState, voxelShape));
        }

    }
}