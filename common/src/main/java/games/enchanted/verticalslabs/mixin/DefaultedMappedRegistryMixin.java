package games.enchanted.verticalslabs.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(DefaultedMappedRegistry.class)
public abstract class DefaultedMappedRegistryMixin<T> extends MappedRegistry<T> implements DefaultedRegistry<T> {
    public DefaultedMappedRegistryMixin(ResourceKey<? extends Registry<T>> key, Lifecycle registryLifecycle) {
        super(key, registryLifecycle);
    }

    @Inject(
        at = @At("TAIL"),
        method = "register(Lnet/minecraft/resources/ResourceKey;Ljava/lang/Object;Lnet/minecraft/core/RegistrationInfo;)Lnet/minecraft/core/Holder$Reference;"
    )
    public void onRegisteredSomething(ResourceKey<T> key, T value, RegistrationInfo registrationInfo, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        if(key.location().equals(ResourceLocation.withDefaultNamespace("stone"))) {
            if(key().equals(Registries.BLOCK)) {
                System.out.println("just registered stone block");
            }
        }
    }
}