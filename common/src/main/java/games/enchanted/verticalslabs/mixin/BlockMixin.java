package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(
        at = @At("HEAD"),
        method = "shouldRenderFace",
        cancellable = true
    )
    private static void evs$shouldRenderFace(BlockState state, BlockState adjacentState, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if(adjacentState.getBlock() instanceof DynamicVerticalSlabBlock dynamicSlabBlock) {
            if(dynamicSlabBlock.shouldRenderOtherBlockFace(adjacentState, state, direction)) {
                cir.setReturnValue(true);
            }
        } else if(state.getBlock() instanceof DynamicVerticalSlabBlock dynamicSlabBlock) {
            if(dynamicSlabBlock.shouldRenderOtherBlockFace(state, adjacentState, direction.getOpposite())) {
                cir.setReturnValue(true);
            }
        }
    }
}