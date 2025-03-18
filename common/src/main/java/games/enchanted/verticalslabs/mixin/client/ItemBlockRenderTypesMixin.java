package games.enchanted.verticalslabs.mixin.client;

import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlockRenderTypes.class)
public abstract class ItemBlockRenderTypesMixin {
    @Inject(
        at = @At("HEAD"),
        method = "getChunkRenderType",
        cancellable = true
    )
    private static void overrideRenderTypeForDynamicVerticalSlab(BlockState blockState, CallbackInfoReturnable<RenderType> cir) {
        Block originalBlock = blockState.getBlock();
        Block blockToGetRenderTypeOf = DynamicVerticalSlabs.VERTICAL_TO_NORMAL_SLAB_MAP.get(originalBlock);
        if(blockToGetRenderTypeOf == null) return;
        BlockState blockStateToGetRenderTypeOf = blockToGetRenderTypeOf.defaultBlockState();
        cir.setReturnValue(ItemBlockRenderTypes.getChunkRenderType(blockStateToGetRenderTypeOf));
    }
}
