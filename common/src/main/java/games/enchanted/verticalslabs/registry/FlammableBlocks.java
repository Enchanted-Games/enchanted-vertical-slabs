package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.platform.Services;

public class FlammableBlocks {
    public static void registerFlammableBlocks() {
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_OAK_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_SPRUCE_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_BIRCH_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_JUNGLE_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_ACACIA_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_DARK_OAK_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_MANGROVE_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_CHERRY_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_PALE_OAK_SLAB.block(), 5, 20);

        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_BAMBOO_SLAB.block(), 5, 20);
        Services.PLATFORM.addFlammableBlock(VerticalSlabBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.block(), 5, 20);
    }
}
