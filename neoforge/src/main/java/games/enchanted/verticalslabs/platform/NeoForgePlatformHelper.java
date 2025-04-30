package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.NeoForgeEntrypoint;
import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public class NeoForgePlatformHelper implements PlatformHelperInterface {
    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Path getMinecraftDirectory() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public @Nullable Path getResourcePathFromModJar(String... strings) {
        return NeoForgeEntrypoint.CONTAINER.getModInfo().getOwningFile().getFile().findResource(strings);
    }

    @Override
    public void buildCreativeTabs() {
        // no op
    }
}