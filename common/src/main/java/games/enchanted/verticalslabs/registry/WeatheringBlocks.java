package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.platform.Services;

public class WeatheringBlocks {
    public static void registerWeatheringBlocks() {
        // Oxidisable pairs
        Services.PLATFORM.addOxidisableBlockPair(ModBlocks.VERTICAL_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.asBlock());
        Services.PLATFORM.addOxidisableBlockPair(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.asBlock());
        Services.PLATFORM.addOxidisableBlockPair(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.asBlock());

        // Waxable pairs
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.asBlock());
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.asBlock());
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.asBlock());
        Services.PLATFORM.addWaxableBlockPair(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.asBlock(), ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.asBlock());
    }
}
