package games.enchanted.verticalslabs.mixin.client;

import games.enchanted.verticalslabs.dynamic.pack.DynamicResourcePackManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Unique private static boolean evs$initialReloadDone = false;

    @Inject(
        at = @At("HEAD"),
        method = "onGameLoadFinished"
    )
    private void initialiseDynamicResourcepack(CallbackInfo ci) {
        if(!evs$initialReloadDone) {
            System.out.println("TEMPORARY: reload hack");
            DynamicResourcePackManager.initialise();
            Minecraft.getInstance().reloadResourcePacks();
            evs$initialReloadDone = true;
        }
    }
}