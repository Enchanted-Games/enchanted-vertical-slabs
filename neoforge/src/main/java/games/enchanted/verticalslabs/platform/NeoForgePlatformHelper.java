package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.mixin.FireBlockAccessorMixin;
import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

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
        return !FMLLoader.getCurrent().isProduction();
    }

    @Override
    public void addWaxableBlockPair(Block unwaxed, Block waxed) {}

    @Override
    public void addWeatheringBlockPair(Block less, Block more) {}

    @Override
    public void addFlammableBlock(Block block, int burnTime, int spread) {
        ((FireBlockAccessorMixin) ((FireBlock) Blocks.FIRE)).evs$setFlammable(block, burnTime, spread);
    }
}