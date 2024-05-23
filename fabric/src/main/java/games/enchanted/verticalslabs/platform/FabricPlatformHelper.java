package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.fabricmc.loader.api.FabricLoader;

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
}
