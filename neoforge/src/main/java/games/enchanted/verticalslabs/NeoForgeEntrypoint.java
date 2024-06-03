package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.NeoForgeCreativeTabRegistration;
import games.enchanted.verticalslabs.platform.RegisterInterface;
import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.ModItems;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Objects;
import java.util.function.Supplier;

@Mod(VerticalSlabsConstants.LOADER_MOD_ID)
public class NeoForgeEntrypoint implements RegisterInterface {
    public final IEventBus eventBus;

    public NeoForgeEntrypoint(IEventBus bus) {
        this.eventBus = bus;
        CommonEntrypoint.initBeforeRegistration(this);

        // register stuff
        bus.addListener((RegisterEvent event) -> {
            if(event.getRegistry().key().equals(Registries.BLOCK)) {
                ModBlocks.register();
                WeatheringBlocks.registerWeatheringBlocks();
                FlammableBlocks.registerFlammableBlocks();
            }
            if(event.getRegistry().key().equals(Registries.ITEM)) {
                ModItems.register();
            }
            if(event.getRegistry().key().equals(Registries.CREATIVE_MODE_TAB)) {
                NeoForgeCreativeTabRegistration.registerTabs();
            }
        });

        bus.addListener(NeoForgeCreativeTabRegistration::addItemsInExistingTabs);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T extends R> T register(ResourceKey<? extends Registry<R>> registryKey, Supplier<T> entry, ResourceLocation key) {
        Registry<R> registry = Objects.requireNonNull( BuiltInRegistries.REGISTRY.get((ResourceKey) registryKey) );
        return Registry.register(registry, key, entry.get() );
    }
}