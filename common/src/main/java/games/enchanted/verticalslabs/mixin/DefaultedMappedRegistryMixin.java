package games.enchanted.verticalslabs.mixin;

import com.mojang.serialization.Lifecycle;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
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
        if(key().equals(Registries.BLOCK) && !key.location().getNamespace().equals("minecraft")) {
            // if something is being registered to the BLOCK registry
            if(value instanceof SlabBlock) {
                System.out.println("Registered a SlabBlock: " + key.location());
                DynamicVerticalSlabs.addSlabLocation(key.location());
            }
        }
    }
}