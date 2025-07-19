package games.enchanted.verticalslabs.block.vertical_slab;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import games.enchanted.verticalslabs.block.CullMode;
import games.enchanted.verticalslabs.config.dynamic.SlabBehaviourFile;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.mixin.invoker.BlockInvoker;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DynamicVerticalSlabBlock extends BaseVerticalSlabBlock {
    public static final MapCodec<DynamicVerticalSlabBlock> CODEC = RecordCodecBuilder.mapCodec((recordCodecBuilderInstance) ->
        recordCodecBuilderInstance.group(
            ResourceLocation.CODEC.fieldOf("regular_slab").forGetter((instance) -> RegistryHelpers.getLocationFromBlock(instance.getRegularSlabBlock())),
            propertiesCodec()
        ).apply(recordCodecBuilderInstance, (orginalSlabLocation, properties) -> new DynamicVerticalSlabBlock(properties, new DynamicSlab(orginalSlabLocation)))
    );

    final Block REGULAR_SLAB;
    final @Nullable Block REGULAR_BLOCK;

    public DynamicVerticalSlabBlock(Properties settings, DynamicSlab dynamicSlab) {
        super(settings);
        REGULAR_SLAB = RegistryHelpers.getBlockFromLocation(dynamicSlab.getOriginalSlabLocation());
        if(dynamicSlab.getRegularBlockLocation().isPresent()) {
            REGULAR_BLOCK = RegistryHelpers.getBlockFromLocation(dynamicSlab.getRegularBlockLocation().get());
        } else {
            REGULAR_BLOCK = null;
        }
    }

    @Override
    protected @NotNull MapCodec<? extends BaseVerticalSlabBlock> codec() {
        return CODEC;
    }

    public Block getRegularSlabBlock() {
        return REGULAR_SLAB;
    }

    public BlockState getRegularSlabDoubleState() {
        return REGULAR_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE);
    }

//    /**
//     * @param currentState the vertical slab state, guaranteed to be an instance of this
//     * @param otherState        the other state
//     * @param direction         the direction
//     */
//    public CullMode shouldCullOtherBlock(@NotNull BlockState currentState, @NotNull BlockState otherState, @NotNull Direction direction) {
//        // this code is so bad ... but it works
//        if(otherState.getBlock() == this) {
//            // both states are this same block
//            if(!otherState.getValue(SINGLE) && !currentState.getValue(SINGLE)) return CullMode.CULL;
//            if(otherState.getValue(SINGLE) && otherState.getValue(FACING) == direction.getOpposite()) return CullMode.CULL;
//            if(currentState.getValue(SINGLE) && !otherState.getValue(SINGLE)) return CullMode.CULL;
//            if(currentState.getValue(FACING) == otherState.getValue(FACING) && currentState.getValue(SINGLE) && otherState.getValue(SINGLE) && direction != currentState.getValue(FACING)) return CullMode.CULL;
//            if(direction == Direction.UP || direction == Direction.DOWN) {
//                if(currentState.getValue(SINGLE) && !otherState.getValue(SINGLE)) return CullMode.CULL;
//            }
//            return CullMode.NO_CULL;
//        } else if (otherState.getBlock() == REGULAR_SLAB) {
//            // other state is regular slab
//            if(otherState.getValue(SlabBlock.TYPE) == SlabType.DOUBLE && currentState.getValue(FACING) == direction) return CullMode.CULL;
//            if(otherState.getValue(SlabBlock.TYPE) == SlabType.DOUBLE && !currentState.getValue(SINGLE)) return CullMode.CULL;
//            if(direction == Direction.UP && otherState.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) return CullMode.CULL;
//            if(direction == Direction.DOWN && otherState.getValue(SlabBlock.TYPE) == SlabType.TOP) return CullMode.CULL;
//        } else if (otherState.getBlock() == REGULAR_BLOCK) {
//            // other state is regular block
//        }
//        // if state is any other block
//        return otherState.getBlock() == this ? CullMode.VANILLA_CULL : CullMode.NO_CULL;
//    }

    @Override
    protected @NotNull VoxelShape getVisualShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        if(getRegularSlabDoubleState().getVisualShape(blockGetter, blockPos, collisionContext).isEmpty()) {
            return Shapes.empty();
        }
        return super.getVisualShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Override
    public void popExperience(@NotNull ServerLevel level, @NotNull BlockPos pos, int amount) {
        ((BlockInvoker) REGULAR_SLAB).evs$invoke$popExperience(level, pos, amount);
    }

    @Override
    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, double fallDistance) {
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
        return getRegularSlabDoubleState().propagatesSkylightDown();
    }

    @Override
    protected float getShadeBrightness(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        if(state.getValue(BaseVerticalSlabBlock.SINGLE)) return 1.0f;
        return getRegularSlabDoubleState().getShadeBrightness(level, pos);
    }

    @Override
    protected @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean includeData) {
        if(SlabBehaviourFile.INSTANCE.getUseSeparateVerticalSlabItems()) {
            return super.getCloneItemStack(level, pos, state, includeData);
        }
        return new ItemStack(getRegularSlabBlock().asItem());
    }

    @Override
    protected @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder params) {
        if(SlabBehaviourFile.INSTANCE.getUseSeparateVerticalSlabItems()) {
            return super.getDrops(state, params);
        }
        BlockState regularSlabState;
        if(state.getValue(BaseVerticalSlabBlock.SINGLE)) {
            regularSlabState = getRegularSlabBlock().defaultBlockState();
        } else {
            regularSlabState = getRegularSlabDoubleState();
        }
        return regularSlabState.getDrops(params);
    }
}