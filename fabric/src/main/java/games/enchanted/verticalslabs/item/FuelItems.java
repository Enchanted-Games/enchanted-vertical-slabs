package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.registry.VerticalSlabBlocks;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class FuelItems {
    public static void registerFuelItems() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(VerticalSlabBlocks.VERTICAL_OAK_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_SPRUCE_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_BIRCH_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_JUNGLE_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_ACACIA_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_DARK_OAK_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_MANGROVE_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_CHERRY_SLAB.block(), 300);
            builder.add(VerticalSlabBlocks.VERTICAL_PALE_OAK_SLAB.block(), 300);

            builder.add(VerticalSlabBlocks.VERTICAL_BAMBOO_SLAB.block(), 150);
            builder.add(VerticalSlabBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.block(), 150);
        });
    }
}
