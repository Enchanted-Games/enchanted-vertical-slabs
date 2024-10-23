package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.platform.Services;

public class FlammableBlocks {
    public static void registerFlammableBlocks() {
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_OAK_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_SPRUCE_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_BIRCH_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_JUNGLE_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_ACACIA_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_DARK_OAK_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_MANGROVE_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_CHERRY_SLAB.block(), 5, 20);

        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_BAMBOO_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.block(), 5, 20);
    }
}
