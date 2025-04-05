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
    private static void renderFace(BlockState currentFace, BlockState neighboringFace, Direction face, CallbackInfoReturnable<Boolean> cir) {
        if(neighboringFace.getBlock() instanceof DynamicVerticalSlabBlock dynamicBlock) {
            boolean shouldCull = dynamicBlock.shouldCullOtherBlock(neighboringFace, currentFace, face.getOpposite());
            if(!shouldCull) cir.setReturnValue(true);
        }
        if(currentFace.getBlock() instanceof DynamicVerticalSlabBlock dynamicBlock) {
            boolean shouldCull = dynamicBlock.shouldCullOtherBlock(currentFace, neighboringFace, face);
            if(!shouldCull) cir.setReturnValue(true);
        }
    }
}
