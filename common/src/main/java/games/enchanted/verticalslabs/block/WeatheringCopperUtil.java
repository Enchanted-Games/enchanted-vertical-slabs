package games.enchanted.verticalslabs.block;

import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabsManager;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;

public class WeatheringCopperUtil {
    public static void addDynamicCopperPairs(ArrayList<DynamicSlab> slabs) {
        for (DynamicSlab slab : slabs) {
            Block regularSlabBlock = RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation());
            if (!(regularSlabBlock instanceof WeatheringCopper)) continue;
            Optional<Block> nextOxidationSlabBlock = WeatheringCopper.getNext(regularSlabBlock);
            Optional<BlockState> waxedSlabBlock = HoneycombItem.getWaxed(regularSlabBlock.defaultBlockState());
            if (nextOxidationSlabBlock.isEmpty() && waxedSlabBlock.isEmpty()) continue;

            @Nullable DynamicVerticalSlabBlock verticalSlabBlock = DynamicVerticalSlabsManager.NORMAL_TO_VERTICAL_SLAB_MAP.get(regularSlabBlock);
            if(verticalSlabBlock == null) continue;

            if(nextOxidationSlabBlock.isPresent()) {
                @Nullable DynamicVerticalSlabBlock nextOxidationVerticalSlabBlock = DynamicVerticalSlabsManager.NORMAL_TO_VERTICAL_SLAB_MAP.get(nextOxidationSlabBlock.get());
                if (nextOxidationVerticalSlabBlock != null) {
                    Services.PLATFORM.addWeatheringBlockPair(verticalSlabBlock, nextOxidationVerticalSlabBlock);
                }
            }
            if(waxedSlabBlock.isPresent()) {
                @Nullable DynamicVerticalSlabBlock waxedVerticalSlabBlock = DynamicVerticalSlabsManager.NORMAL_TO_VERTICAL_SLAB_MAP.get(waxedSlabBlock.get().getBlock());
                if(waxedVerticalSlabBlock != null) {
                    Services.PLATFORM.addWaxableBlockPair(verticalSlabBlock, waxedVerticalSlabBlock);
                }
            }
        }
    }
}
