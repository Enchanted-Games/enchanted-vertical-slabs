package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.DynamicResourcePackManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Final private ReloadableResourceManager resourceManager;

    @Inject(
        at = @At("RETURN"),
        method = "<init>"
    )
    private void oninit(GameConfig gameConfig, CallbackInfo ci) {
        DynamicResourcePackManager.onAddBlockstates();
        DynamicResourcePackManager.onAddModels();
        DynamicResourcePackManager.onAddItemDefinitions();
    }
}