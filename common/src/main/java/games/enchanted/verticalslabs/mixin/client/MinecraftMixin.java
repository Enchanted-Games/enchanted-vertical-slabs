package games.enchanted.verticalslabs.mixin.client;

import games.enchanted.verticalslabs.dynamic.pack.DynamicResourcePackManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(
        at = @At("RETURN"),
        method = "<init>"
    )
    private void initialiseDynamicResourcepack(GameConfig gameConfig, CallbackInfo ci) {
        DynamicResourcePackManager.initialise();
    }
}