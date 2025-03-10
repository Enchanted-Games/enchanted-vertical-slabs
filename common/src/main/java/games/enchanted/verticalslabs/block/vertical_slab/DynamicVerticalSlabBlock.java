package games.enchanted.verticalslabs.block.vertical_slab;

import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.mixin.invoker.BlockBehaviourInvoker;
import games.enchanted.verticalslabs.mixin.invoker.BlockInvoker;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DynamicVerticalSlabBlock extends BaseVerticalSlabBlock {
    Block REGULAR_SLAB;
    @Nullable Block REGULAR_BLOCK;

    public DynamicVerticalSlabBlock(Properties settings, DynamicSlab dynamicSlab) {
        super(settings);
        REGULAR_SLAB = RegistryHelpers.getBlockFromLocation(dynamicSlab.getOriginalSlabLocation());
        if(dynamicSlab.getRegularBlockLocation().isPresent()) {
            REGULAR_BLOCK = RegistryHelpers.getBlockFromLocation(dynamicSlab.getRegularBlockLocation().get());
        } else {
            REGULAR_BLOCK = null;
        }
    }

    public Block getRegularSlabBlock() {
        return REGULAR_SLAB;
    }

    public boolean isStateThisOrRegularSlab(BlockState state) {
        return state.is(this) || state.is(REGULAR_SLAB);
    }

    public boolean shouldRenderOtherBlockFace(BlockState verticalSlabState, BlockState otherBlockState, Direction direction) {
        // TODO: improve culling against regular slab (especially for translucent vertical slabs), and make solid slabs cull other blocks properly
        if(isStateThisOrRegularSlab(verticalSlabState) && isStateThisOrRegularSlab(otherBlockState)) return false;
        return true;
    }

    @Override
    protected boolean skipRendering(@NotNull BlockState state, @NotNull BlockState adjacentState, @NotNull Direction direction) {
        // TODO: make this not crash when called with unexpected blockstates
        if(REGULAR_BLOCK == null) {
            return super.skipRendering(state, adjacentState, direction);
        }
        return ((BlockBehaviourInvoker) REGULAR_BLOCK).evs$invoke$skipRendering(state, adjacentState, direction);
    }

    @Override
    public void popExperience(@NotNull ServerLevel level, @NotNull BlockPos pos, int amount) {
        ((BlockInvoker) REGULAR_SLAB).evs$invoke$popExperience(level, pos, amount);
    }

    @Override
    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
        if(REGULAR_BLOCK == null) {
            super.fallOn(level, state, pos, entity, fallDistance);
            return;
        }
        super.fallOn(level, REGULAR_BLOCK.defaultBlockState(), pos, entity, fallDistance);
    }

    @Override
    protected int getLightBlock(@NotNull BlockState state) {
        if(state.getValue(BaseVerticalSlabBlock.SINGLE)) return 0;
        if(REGULAR_BLOCK == null) {
            return super.getLightBlock(state);
        }
        return super.getLightBlock(REGULAR_BLOCK.defaultBlockState());
    }
}