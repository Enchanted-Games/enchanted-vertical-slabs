package games.enchanted.verticalslabs.mixin.slab_behaviours;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.block.vertical_slab.BaseVerticalSlabBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IronBarsBlock.class)
public abstract class IronBarsBlockMixin extends CrossCollisionBlock {
    protected IronBarsBlockMixin(float $$0, float $$1, float $$2, float $$3, float $$4, Properties $$5) {
        super($$0, $$1, $$2, $$3, $$4, $$5);
    }

    @Unique
    private boolean evs$getConnectionForDirection(BlockState state, Direction direction) {
        if(!(state.getBlock() instanceof BaseVerticalSlabBlock)) {
            return false;
        }
        boolean slabAwayFromWall = state.getValue(BaseVerticalSlabBlock.FACING) == direction;
        return !(state.getValue(BaseVerticalSlabBlock.SINGLE) && slabAwayFromWall);
    }

    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isFaceSturdy(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z"),
        method = {"getStateForPlacement", "updateShape"}
    )
    private boolean evs$attachToVerticalSlabs(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, Direction direction, Operation<Boolean> original) {
        return original.call(instance, blockGetter, blockPos, direction) || evs$getConnectionForDirection(instance, direction.getOpposite());
    }
}
