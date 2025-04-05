package games.enchanted.verticalslabs.block.vertical_slab;

import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.mixin.invoker.BlockBehaviourInvoker;
import games.enchanted.verticalslabs.mixin.invoker.BlockInvoker;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
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

    /**
     * If false is returned, the face will always render.
     * If true is returned, the normal culling is used for that face.
     *
     * @param verticalSlabState the vertical slab state
     * @param otherState        the other state
     * @param direction         the direction
     */
    public boolean shouldCullOtherBlock(@NotNull BlockState verticalSlabState, @NotNull BlockState otherState, @NotNull Direction direction) {
        if(verticalSlabState.getValue(BaseVerticalSlabBlock.SINGLE)) {
            return false;
        }

        if(otherState.getBlock() == this) {
            return true;
        }
        return false;
    }

    @Override
    protected VoxelShape getVisualShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        // TODO: implement returning an empty shape for glass-like blocks
        return super.getVisualShape(blockState, blockGetter, blockPos, collisionContext);
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

    @Override
    protected boolean propagatesSkylightDown(@NotNull BlockState state) {
        if(state.getValue(BaseVerticalSlabBlock.SINGLE)) return true;
        return REGULAR_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE).propagatesSkylightDown();
    }

    @Override
    protected float getShadeBrightness(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        if(state.getValue(BaseVerticalSlabBlock.SINGLE)) return 1.0f;
        return REGULAR_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE).getShadeBrightness(level, pos);
    }
}