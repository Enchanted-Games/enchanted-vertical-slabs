package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.RegisterInterface;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod(Constants.MOD_ID)
public class NeoForgeEntrypoint implements RegisterInterface {
    Map<ResourceKey<? extends Registry<?>>, Map<String, DeferredRegister<?>>> registers = new HashMap<>();
    public final IEventBus eventBus;

    public NeoForgeEntrypoint(IEventBus bus) {
        this.eventBus = bus;
        CommonClass.init(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T extends R> Supplier<T> register(ResourceKey<? extends Registry<R>> registry, Supplier<T> entry, ResourceLocation key) {
        var register =
            (DeferredRegister<R>) registers.computeIfAbsent( registry, k -> new HashMap<>() )
            .computeIfAbsent( key.toString(), k -> {
                var r = DeferredRegister.create(registry, key.getNamespace());
                r.register(eventBus);
                return r;
            });
        return register.register(key.getPath(), entry);
    }
}