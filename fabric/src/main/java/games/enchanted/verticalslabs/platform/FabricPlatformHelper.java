package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.FabricModEntrypoint;
import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Optional;

public class FabricPlatformHelper implements PlatformHelperInterface {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Path getMinecraftDirectory() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    public @Nullable Path getResourcePathFromModJar(String... strings) {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("evs");
        if(container.isPresent()) {
            Optional<Path> path = container.get().findPath(String.join("/", strings));
            return path.orElse(null);
        }
        return null;
    }

    @Override
    public void buildCreativeTabs() {
        FabricModEntrypoint.registerCreativeTabModifiers();
    }
}
