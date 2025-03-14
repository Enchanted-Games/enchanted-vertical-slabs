package games.enchanted.verticalslabs.mixin.client;

import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(
        at = @At("HEAD"),
        method = "onGameLoadFinished"
    )
    private void evs$initialiseDynamicResourcepack(CallbackInfo ci) {
        if(DynamicResourcePackManager.INSTANCE.requiresReloadToApply()) {
            DynamicResourcePackManager.INSTANCE.initialise();
            Minecraft.getInstance().reloadResourcePacks();
            DynamicResourcePackManager.INSTANCE.triggeredReload();
        }
    }
}