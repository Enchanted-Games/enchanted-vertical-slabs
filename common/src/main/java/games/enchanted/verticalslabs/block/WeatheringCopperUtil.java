package games.enchanted.verticalslabs.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

public class WeatheringCopperUtil {
    public static final BiMap<Block, Block> WEATHERING_PAIRS = HashBiMap.create();
    public static final Supplier<BiMap<Block, Block>> WEATHERING_PAIRS_INVERSE = Suppliers.memoize(WEATHERING_PAIRS::inverse);
    public static final BiMap<Block, Block> WAXABLE_PAIRS = HashBiMap.create();

    public static void addWeatheringPair(Block original, Block weathered) {
        WEATHERING_PAIRS.put(original, weathered);
    }

    public static void addDynamicWeatheringPairs(ArrayList<DynamicSlab> slabs) {
        for (DynamicSlab slab : slabs) {
            Block regularSlabBlock = RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation());
            if(!(regularSlabBlock instanceof WeatheringCopper)) continue;
            Optional<Block> nextOxidationSlabBlock = WeatheringCopper.getNext(regularSlabBlock);
            if(nextOxidationSlabBlock.isEmpty()) continue;

            @Nullable DynamicVerticalSlabBlock verticalSlabBlock = DynamicVerticalSlabs.NORMAL_TO_VERTICAL_SLAB_MAP.get(regularSlabBlock);
            @Nullable DynamicVerticalSlabBlock nextOxidationVerticalSlabBlock = DynamicVerticalSlabs.NORMAL_TO_VERTICAL_SLAB_MAP.get(nextOxidationSlabBlock.get());
            if(verticalSlabBlock != null && nextOxidationVerticalSlabBlock != null) {
                addWeatheringPair(verticalSlabBlock, nextOxidationVerticalSlabBlock);
            }
        }
    }

    public static void addWaxablePair(Block original, Block waxed) {
        WAXABLE_PAIRS.put(original, waxed);
        // TODO: implement waxable blocks
    }

}
