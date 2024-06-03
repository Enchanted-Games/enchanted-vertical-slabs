package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.Block;

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
    public void addWaxableBlockPair(Block unwaxed, Block waxed) {
        OxidizableBlocksRegistry.registerWaxableBlockPair(unwaxed, waxed);
    }

    @Override
    public void addOxidisableBlockPair(Block less, Block more) {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(less, more);
    }

    @Override
    public void addFlammableBlock(Block block, int burnTime, int spread) {
        FlammableBlockRegistry.getDefaultInstance().add(block, burnTime, spread);
    }
}
