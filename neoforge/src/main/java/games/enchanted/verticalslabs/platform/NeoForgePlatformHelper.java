package games.enchanted.verticalslabs.platform;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import games.enchanted.verticalslabs.block.WeatheringCopperMap;
import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.function.Supplier;

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
    public void addWaxableBlockPair(Block unwaxed, Block waxed) {
        Supplier<BiMap<Block, Block>> oldWAXABLES = HoneycombItem.WAXABLES;
        HoneycombItem.WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder().putAll(oldWAXABLES.get()).put(unwaxed, waxed).build())::get;
    }

    @Override
    public void addOxidisableBlockPair(Block less, Block more) {
        ImmutableBiMap.Builder<Block,Block> builder = ImmutableBiMap.builder();
        if(WeatheringCopperMap.MAP != null) {
            builder.putAll(WeatheringCopperMap.MAP);
        }
        WeatheringCopperMap.MAP = builder.put(less, more).build();

        System.out.println(WeatheringCopperMap.MAP);
    }

    @Override
    public void addFlammableBlock(Block block, int burnTime, int spread) {
        ((FireBlock) Blocks.FIRE).setFlammable(block, burnTime, spread);
    }
}