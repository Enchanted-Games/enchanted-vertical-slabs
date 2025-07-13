package games.enchanted.verticalslabs.mixin.client;

import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabsManager;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
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
    private static void evs$overrideRenderTypeForDynamicVerticalSlab(BlockState blockState, CallbackInfoReturnable<ChunkSectionLayer> cir) {
        Block originalBlock = blockState.getBlock();
        Block blockToGetRenderTypeOf = DynamicVerticalSlabsManager.VERTICAL_TO_NORMAL_SLAB_MAP.get(originalBlock);
        if(blockToGetRenderTypeOf == null) return;
        BlockState blockStateToGetRenderTypeOf = blockToGetRenderTypeOf.defaultBlockState();
        cir.setReturnValue(ItemBlockRenderTypes.getChunkRenderType(blockStateToGetRenderTypeOf));
    }
}
