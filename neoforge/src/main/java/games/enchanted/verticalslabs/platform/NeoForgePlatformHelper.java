package games.enchanted.verticalslabs.platform;

import com.google.common.collect.ImmutableBiMap;
import games.enchanted.verticalslabs.block.SpecialBlockMaps;
import games.enchanted.verticalslabs.mixin.FireBlockAccessor;
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
    public void addWaxableBlockPair(Block unwaxed, Block waxed) {
        ImmutableBiMap.Builder<Block,Block> builder = ImmutableBiMap.builder();
        if(SpecialBlockMaps.WAXABLE_BLOCKS != null) {
            builder.putAll(SpecialBlockMaps.WAXABLE_BLOCKS);
        }
        SpecialBlockMaps.WAXABLE_BLOCKS = builder.put(waxed, unwaxed).build();
    }

    @Override
    public void addWeatheringBlockPair(Block less, Block more) {
        ImmutableBiMap.Builder<Block,Block> builder = ImmutableBiMap.builder();
        if(SpecialBlockMaps.WEATHERING_COPPER_MAP != null) {
            builder.putAll(SpecialBlockMaps.WEATHERING_COPPER_MAP);
        }
        SpecialBlockMaps.WEATHERING_COPPER_MAP = builder.put(less, more).build();
    }

    @Override
    public void addFlammableBlock(Block block, int burnTime, int spread) {
        ((FireBlockAccessor) ((FireBlock) Blocks.FIRE)).evs$setFlammable(block, burnTime, spread);
    }
}