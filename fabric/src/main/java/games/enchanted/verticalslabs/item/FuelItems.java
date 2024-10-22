package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

public class FuelItems {
    public static void registerFuelItems() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.VERTICAL_OAK_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_SPRUCE_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_BIRCH_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_JUNGLE_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_ACACIA_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_DARK_OAK_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_MANGROVE_SLAB.getBlock().orElseThrow(), 300);
            builder.add(ModBlocks.VERTICAL_CHERRY_SLAB.getBlock().orElseThrow(), 300);

            builder.add(ModBlocks.VERTICAL_BAMBOO_SLAB.getBlock().orElseThrow(), 150);
            builder.add(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.getBlock().orElseThrow(), 150);
        });
    }
}
