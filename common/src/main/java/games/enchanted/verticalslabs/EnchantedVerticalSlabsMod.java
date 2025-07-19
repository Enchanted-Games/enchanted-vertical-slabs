package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.config.dynamic.SlabBehaviourFile;
import games.enchanted.verticalslabs.platform.Services;
import net.minecraft.FileUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

public class EnchantedVerticalSlabsMod {
    public static void initBeforeRegistration() {
        getEVSModDirectory();
        EnchantedVerticalSlabsLogging.info("Mod is loading in a {} environment!", Services.PLATFORM.getPlatformName());
        SlabBehaviourFile.INSTANCE.readOrCreateSettingsFile();
    }

    public static Path getEVSModDirectory() {
        Path modFolderPath = Services.PLATFORM.getMinecraftDirectory().resolve(EnchantedVerticalSlabsConstants.MOD_FOLDER);
        try {
            FileUtil.createDirectoriesSafe(modFolderPath);
            return modFolderPath;
        } catch (IOException e) {
            throw new IllegalStateException("Could not create directory '" + EnchantedVerticalSlabsConstants.MOD_FOLDER + "' in minecraft folder.\n" + e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <R, T extends R> T register(ResourceKey<? extends Registry<R>> registryKey, Supplier<T> entry, ResourceLocation key) {
        Registry<R> registry = Objects.requireNonNull( BuiltInRegistries.REGISTRY.getValue((ResourceKey) registryKey));
        return Registry.register(registry, key, entry.get());
    }
}