package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.registry.RegistryHelpers;
import games.enchanted.verticalslabs.util.BlockRenderTypeUtil;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
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
        Block block = blockState.getBlock();
        ResourceLocation blockLocation = RegistryHelpers.getLocationFromBlock(block);
        ResourceLocation blockToGetRenderTypeOf = BlockRenderTypeUtil.BLOCK_ALIAS_MAP.get(blockLocation);
        if(blockToGetRenderTypeOf == null) return;
        BlockState originalBlockState = RegistryHelpers.getBlockFromLocation(blockToGetRenderTypeOf).defaultBlockState();
        cir.setReturnValue(ItemBlockRenderTypes.getChunkRenderType(originalBlockState));
    }
}
