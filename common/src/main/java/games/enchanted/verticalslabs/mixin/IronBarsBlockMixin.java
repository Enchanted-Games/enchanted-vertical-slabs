package games.enchanted.verticalslabs.mixin;

import com.mojang.serialization.MapCodec;
import games.enchanted.verticalslabs.VerticalSlabsConstants;
import games.enchanted.verticalslabs.block.VerticalSlabBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronBarsBlock.class)
public abstract class IronBarsBlockMixin extends CrossCollisionBlock {
    protected IronBarsBlockMixin(float $$0, float $$1, float $$2, float $$3, float $$4, Properties $$5) {
        super($$0, $$1, $$2, $$3, $$4, $$5);
    }

    @Override
    public MapCodec<? extends CrossCollisionBlock> codec() {
        return null;
    }

    @Unique
    private boolean enchanted_vertical_slabs$getConnectionForDirection(BlockState state, Direction direction) {
        if(!(state.getBlock() instanceof VerticalSlabBlock)) {
            throw new IllegalArgumentException("BlockState block must contain an instance of VerticalSlabBlock");
        }
        boolean slabAwayFromWall = state.getValue(VerticalSlabBlock.FACING) == direction;
        return !(state.getValue(VerticalSlabBlock.SINGLE) && slabAwayFromWall);
    }

    @Inject(
        at = @At("TAIL"),
        method = "getStateForPlacement(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;",
        cancellable = true
    )
    // attach iron bars (and similar blocks like glass panes) to vertical slabs when placed
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir) {
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        BlockGetter blockGetter = blockPlaceContext.getLevel();

        BlockState northBlockState = blockGetter.getBlockState(blockPos.north());
        BlockState eastBlockState = blockGetter.getBlockState(blockPos.east());
        BlockState southBlockState = blockGetter.getBlockState(blockPos.south());
        BlockState westBlockState = blockGetter.getBlockState(blockPos.west());

        if(northBlockState.getBlock() instanceof VerticalSlabBlock) {
            cir.setReturnValue(cir.getReturnValue().setValue(NORTH, enchanted_vertical_slabs$getConnectionForDirection(northBlockState, Direction.NORTH)));
        }
        if(eastBlockState.getBlock() instanceof VerticalSlabBlock) {
            cir.setReturnValue(cir.getReturnValue().setValue(EAST, enchanted_vertical_slabs$getConnectionForDirection(eastBlockState, Direction.EAST)));
        }
        if(southBlockState.getBlock() instanceof VerticalSlabBlock) {
            cir.setReturnValue(cir.getReturnValue().setValue(SOUTH, enchanted_vertical_slabs$getConnectionForDirection(southBlockState, Direction.SOUTH)));
        }
        if(westBlockState.getBlock() instanceof VerticalSlabBlock) {
            cir.setReturnValue(cir.getReturnValue().setValue(WEST, enchanted_vertical_slabs$getConnectionForDirection(westBlockState, Direction.WEST)));
        }
    }

    @Inject(
        at = @At("HEAD"),
        method = "updateShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;",
        cancellable = true
    )
    // attach iron bars (and similar blocks like glass panes) to vertical slabs
    protected void updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2, CallbackInfoReturnable<BlockState> cir) {
        if(blockState2.getBlock() instanceof VerticalSlabBlock) {
            boolean slabAwayFromBarBlock = blockState2.getValue(VerticalSlabBlock.FACING) == direction;
            cir.setReturnValue(direction.getAxis().isHorizontal() ? blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), !slabAwayFromBarBlock) : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2));
        }
    }
}
