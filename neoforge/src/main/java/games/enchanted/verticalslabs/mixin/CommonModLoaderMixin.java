package games.enchanted.verticalslabs.mixin;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.internal.CommonModLoader;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static games.enchanted.verticalslabs.registry.RegistryHelpers.registerVerticalSlab;

@Debug(export = true)
@Mixin(CommonModLoader.class)
public abstract class CommonModLoaderMixin {
    @Inject(
        method = "lambda$begin$0",
        at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/GameData;freezeData()V")
    )
    private static void endOfRegistration(CallbackInfo ci) {
        System.out.println("t");
        registerVerticalSlab("test_slab", Blocks.SPRUCE_SLAB.properties());
    }
}