package games.enchanted.verticalslabs.block.vertical_slab;

import games.enchanted.verticalslabs.mixin.invoker.BlockBehaviourInvoker;
import games.enchanted.verticalslabs.mixin.invoker.BlockInvoker;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.NotNull;

public class DynamicVerticalSlabBlock extends BaseVerticalSlabBlock {
    Block REGULAR_SLAB;

    public DynamicVerticalSlabBlock(Properties settings, Block regularSlabBlock) {
        super(settings);
        REGULAR_SLAB = regularSlabBlock;
    }

    public Block getRegularSlabBlock() {
        return REGULAR_SLAB;
    }

    public boolean isStateThisOrRegularSlab(BlockState state) {
        return state.is(this) || state.is(REGULAR_SLAB);
    }

    public boolean shouldRenderOtherBlockFace(BlockState verticalSlabState, BlockState otherBlockState, Direction direction) {
        // TODO: improve culling against regular slab (especially for translucent vertical slabs)
        if(isStateThisOrRegularSlab(verticalSlabState) && isStateThisOrRegularSlab(otherBlockState)) return false;
        return true;
    }

    @Override
    public void popExperience(@NotNull ServerLevel level, @NotNull BlockPos pos, int amount) {
        ((BlockInvoker) REGULAR_SLAB).evs$invoke$popExperience(level, pos, amount);
    }

    @Override
    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
        REGULAR_SLAB.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    protected boolean skipRendering(@NotNull BlockState state, @NotNull BlockState adjacentState, @NotNull Direction direction) {
//        return true;
        return ((BlockBehaviourInvoker) REGULAR_SLAB).evs$invoke$skipRendering(state, adjacentState, direction);
    }
}