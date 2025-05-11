package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabsManager;
import net.neoforged.neoforge.registries.GameData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameData.class)
public abstract class NeoForgeGameDataMixin {
    @Inject(
        method = "freezeData()V",
        at = @At("HEAD")
    )
    private static void endOfRegistration(CallbackInfo ci) {
        DynamicVerticalSlabsManager.registerDynamicSlabs();
    }
}