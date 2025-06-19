package games.enchanted.verticalslabs.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.block.VerticalSlabBlock;
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
    protected IronBarsBlockMixin(float nodeWidth, float extensionWidth, float nodeHeight, float extensionHeight, float collisionHeight, Properties properties) {
        super(nodeWidth, extensionWidth, nodeHeight, extensionHeight, collisionHeight, properties);
    }

    @Unique
    private boolean evs$getConnectionForDirection(BlockState state, Direction direction) {
        if(!(state.getBlock() instanceof VerticalSlabBlock)) {
            return false;
        }
        boolean slabAwayFromWall = state.getValue(VerticalSlabBlock.FACING) == direction;
        return !(state.getValue(VerticalSlabBlock.SINGLE) && slabAwayFromWall);
    }

    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isFaceSturdy(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z"),
        method = {"getStateForPlacement", "updateShape"}
    )
    private boolean evs$attachToVerticalSlabs(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, Direction direction, Operation<Boolean> original) {
        return original.call(instance, blockGetter, blockPos, direction) || evs$getConnectionForDirection(instance, direction.getOpposite());
    }
}

