package games.enchanted.verticalslabs.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static games.enchanted.verticalslabs.registry.RegistryHelpers.registerVerticalSlab;

@Mixin(BuiltInRegistries.class)
public abstract class BuiltinRegistriesMixin {
    @Inject(
        at = @At("HEAD"),
        method = "freeze"
    )
    private static void freeze(CallbackInfo ci) {
        registerVerticalSlab("test_slab", Blocks.SPRUCE_SLAB.properties());
    }
}
