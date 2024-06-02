package games.enchanted.verticalslabs.platform;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public interface RegisterInterface {
    <R, T extends R> T register(ResourceKey<? extends Registry<R>> registry, Supplier<T> entry, ResourceLocation key);
}