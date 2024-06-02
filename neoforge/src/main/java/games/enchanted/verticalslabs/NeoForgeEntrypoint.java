package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.RegisterInterface;
import games.enchanted.verticalslabs.registry.ModBlocks;
import games.enchanted.verticalslabs.registry.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Mod(VerticalSlabsConstants.LOADER_MOD_ID)
public class NeoForgeEntrypoint implements RegisterInterface {
    Map<ResourceKey<? extends Registry<?>>, Map<String, DeferredRegister<?>>> registers = new HashMap<>();
    public final IEventBus eventBus;

    public NeoForgeEntrypoint(IEventBus bus) {
        this.eventBus = bus;

        bus.addListener((RegisterEvent event) -> {
            if(event.getRegistry().key().equals(Registries.BLOCK)) {
                ModBlocks.register();
            }
            if(event.getRegistry().key().equals(Registries.ITEM)) {
                ModItems.register();
            }
        });

        CommonEntrypoint.init(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T extends R> T register(ResourceKey<? extends Registry<R>> registryKey, Supplier<T> entry, ResourceLocation key) {
        Registry<R> registry = Objects.requireNonNull( BuiltInRegistries.REGISTRY.get((ResourceKey) registryKey) );
        T registered = Registry.register(registry, key, entry.get() );
        return registered;
    }
}