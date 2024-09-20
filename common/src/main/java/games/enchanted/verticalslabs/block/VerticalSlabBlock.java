package games.enchanted.verticalslabs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

public class VerticalSlabBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<? extends VerticalSlabBlock> CODEC = simpleCodec(VerticalSlabBlock::new);
    public static final BooleanProperty SINGLE = BooleanProperty.create("single_slab");

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(SINGLE);
        stateManager.add(BlockStateProperties.HORIZONTAL_FACING);
        stateManager.add(BlockStateProperties.WATERLOGGED);
    }

    public VerticalSlabBlock(BlockBehaviour.Properties settings) {
        super(settings);
        registerDefaultState(stateDefinition.any().trySetValue(SINGLE, true));
        registerDefaultState(stateDefinition.any().trySetValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
        registerDefaultState(stateDefinition.any().trySetValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter view, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        if( !state.getValue(SINGLE) ) {
            return Shapes.block();
        }
        return switch ( state.getValue(FACING) ) {
            case Direction.NORTH -> Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
            case Direction.EAST -> Shapes.create(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            case Direction.SOUTH -> Shapes.create(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
            case Direction.WEST -> Shapes.create(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            default -> Shapes.block();
        };
    }

    @Override
    public boolean useShapeForLightOcclusion(@NotNull BlockState state) {
        return true;
    }

    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        ItemStack itemStack = context.getItemInHand();
        if ( !state.getValue(SINGLE) || !(itemStack.is(this.asItem())) ) {
            return false;
        }
        if ( !context.replacingClickedOnBlock() ) {
            return true;
        }

        double hitposX = context.getClickLocation().x - (double)context.getClickedPos().getX();
        double hitposZ = context.getClickLocation().z - (double)context.getClickedPos().getZ();

        Direction facingDirection = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        switch (facingDirection) {
            case Direction.NORTH:
                return hitposZ >= 0.5;
            case Direction.EAST:
                return hitposX <= 0.5;
            case Direction.SOUTH:
                return hitposZ <= 0.5;
            case Direction.WEST:
                return hitposX >= 0.5;
            default:
                return false;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos blockPos = ctx.getClickedPos();
        BlockState replacingBlockState = ctx.getLevel().getBlockState(blockPos);
        FluidState replacingFluidState = ctx.getLevel().getFluidState(blockPos);

        // if replacing a vertical slab, turn existing slab into double slab
        if (replacingBlockState.is(this)) {
            return replacingBlockState
                .setValue(SINGLE, false)
                .setValue(BlockStateProperties.WATERLOGGED, false);
        }
        
        // normal placement logic
        return this.defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection())
            .setValue(BlockStateProperties.WATERLOGGED, replacingFluidState.getType() == Fluids.WATER);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        }
        return state.getFluidState();
    }
    
    @Override
    public boolean placeLiquid(@NotNull LevelAccessor world, @NotNull BlockPos pos, BlockState state, @NotNull FluidState fluidState) {
        if ( state.getValue(SINGLE) ) {
            return SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, fluidState);
        }
        return false;
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, @NotNull BlockGetter world, @NotNull BlockPos pos, BlockState state, @NotNull Fluid fluid) {
        if ( state.getValue(SINGLE) ) {
            return SimpleWaterloggedBlock.super.canPlaceLiquid(player, world, pos, state, fluid);
        }
        return false;
    }
    
    @Override
    protected @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }
    
    @Override
    protected boolean isPathfindable(BlockState state, @NotNull PathComputationType type) {
        return !state.getValue(SINGLE);
    }

    @Override
    protected @NotNull MapCodec<? extends VerticalSlabBlock> codec() {
        return CODEC;
    }
}
