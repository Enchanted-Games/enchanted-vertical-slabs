package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.GameData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static games.enchanted.verticalslabs.registry.RegistryHelpers.registerVerticalSlab;

@Mixin(GameData.class)
public abstract class NeoForgeGameDataMixin {
    @Inject(
        method = "freezeData()V",
        at = @At("HEAD")
    )
    private static void endOfRegistration(CallbackInfo ci) {
        System.out.println("t");
        registerVerticalSlab("test_slab", Blocks.SPRUCE_SLAB.properties());
        DynamicVerticalSlabs.registerDynamicSlabs();
    }
}