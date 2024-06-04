package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.platform.Services;

public class WeatheringBlocks {
    /**
     * Register weathering and waxable block pairs
     */
    public static void registerWeatheringBlocks() {
        // Weathering pairs
        Services.PLATFORM.addWeatheringBlockPair(ModBlocks.VERTICAL_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.getBlock());
        Services.PLATFORM.addWeatheringBlockPair(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.getBlock());
        Services.PLATFORM.addWeatheringBlockPair(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.getBlock());

        // Waxable pairs
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.getBlock());
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.getBlock());
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.getBlock());
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.getBlock(), ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.getBlock());
    }
}
