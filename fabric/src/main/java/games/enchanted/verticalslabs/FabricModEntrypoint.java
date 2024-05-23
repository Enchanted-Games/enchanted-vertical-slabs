package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.RegisterInterface;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.function.Supplier;

public class FabricModEntrypoint implements ModInitializer, RegisterInterface {
    @Override
    public void onInitialize() {
        CommonClass.init(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T extends R> Supplier<T> register(ResourceKey<? extends Registry<R>> registryKey, Supplier<T> entry, ResourceLocation key) {
        Registry<R> registry = Objects.requireNonNull( BuiltInRegistries.REGISTRY.get((ResourceKey) registryKey) );
        T registered = Registry.register(registry, key, entry.get() );
        return () -> registered;
    }
}
