package games.enchanted.verticalslabs.block;

import games.enchanted.verticalslabs.VerticalSlabsConstants;
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
    public static final BooleanProperty SINGLE = BooleanProperty.create("single_slab");

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(SINGLE);
        stateManager.add(BlockStateProperties.HORIZONTAL_FACING);
        stateManager.add(BlockStateProperties.WATERLOGGED);
    }

    public VerticalSlabBlock(Properties settings) {
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
        switch (state.getValue(FACING)) {
            case NORTH:
                return Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
            case SOUTH:
                return Shapes.create(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
            case WEST:
                return Shapes.create(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            case EAST:
                return Shapes.create(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            default:
                return Shapes.block();
        }
    }

    @Override
    public boolean useShapeForLightOcclusion(@NotNull BlockState state) {
        return true;
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
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
            case NORTH:
                return hitposZ >= 0.5;
            case EAST:
                return hitposX <= 0.5;
            case SOUTH:
                return hitposZ <= 0.5;
            case WEST:
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
        return super.getFluidState(state);
    }
    
    @Override
    public boolean placeLiquid(@NotNull LevelAccessor world, @NotNull BlockPos pos, BlockState state, @NotNull FluidState fluidState) {
        if ( state.getValue(SINGLE) ) {
            return SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, fluidState);
        }
        return false;
    }

    @Override
    public boolean canPlaceLiquid(@NotNull BlockGetter world, @NotNull BlockPos pos, BlockState state, @NotNull Fluid fluid) {
        if ( state.getValue(SINGLE) ) {
            return SimpleWaterloggedBlock.super.canPlaceLiquid(world, pos, state, fluid);
        }
        return false;
    }
    
    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }
}
