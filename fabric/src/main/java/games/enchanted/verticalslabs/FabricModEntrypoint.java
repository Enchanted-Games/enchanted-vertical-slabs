package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.FabricCreativeTabRegistration;
import games.enchanted.verticalslabs.platform.RegisterInterface;
import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.ModItems;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
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
        CommonEntrypoint.initBeforeRegistration(this);

        ModBlocks.register();
        WeatheringBlocks.registerWeatheringBlocks();
        FlammableBlocks.registerFlammableBlocks();

        ModItems.register();

        FabricCreativeTabRegistration.registerTabs();

    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T extends R> T register(ResourceKey<? extends Registry<R>> registryKey, Supplier<T> entry, ResourceLocation key) {
        Registry<R> registry = Objects.requireNonNull( BuiltInRegistries.REGISTRY.get((ResourceKey) registryKey) );
        return Registry.register(registry, key, entry.get() );
    }
}
