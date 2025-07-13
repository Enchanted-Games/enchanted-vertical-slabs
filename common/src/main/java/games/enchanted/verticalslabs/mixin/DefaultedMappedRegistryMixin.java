package games.enchanted.verticalslabs.mixin;

import com.mojang.serialization.Lifecycle;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabsManager;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.SlabBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultedMappedRegistry.class)
public abstract class DefaultedMappedRegistryMixin<T> extends MappedRegistry<T> implements DefaultedRegistry<T> {
    public DefaultedMappedRegistryMixin(ResourceKey<? extends Registry<T>> key, Lifecycle registryLifecycle) {
        super(key, registryLifecycle);
    }

    @Inject(
        at = @At("TAIL"),
        method = "register(Lnet/minecraft/resources/ResourceKey;Ljava/lang/Object;Lnet/minecraft/core/RegistrationInfo;)Lnet/minecraft/core/Holder$Reference;"
    )
    public void evs$onRegisteredSomething(ResourceKey<T> key, T value, RegistrationInfo registrationInfo, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        // if a SlabBlock is being registered to the BLOCK registry
        if(key().equals(Registries.BLOCK) && value instanceof SlabBlock) {
            if(key.location().getNamespace().equals("minecraft")) {
                EnchantedVerticalSlabsLogging.debug("Registered a Vanilla SlabBlock: " + key.location());
                DynamicVerticalSlabsManager.addDynamicSlabForVanilla(key.location());
                return;
            }
            EnchantedVerticalSlabsLogging.debug("Registered a SlabBlock: " + key.location());
            DynamicVerticalSlabsManager.addDynamicSlab(key.location());
        }
    }
}