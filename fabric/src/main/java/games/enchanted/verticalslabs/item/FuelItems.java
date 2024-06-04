package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class FuelItems {
    public static void registerFuelItems() {
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_OAK_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_SPRUCE_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_BIRCH_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_JUNGLE_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_ACACIA_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_DARK_OAK_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_MANGROVE_SLAB.getBlock(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_CHERRY_SLAB.getBlock(), 300);

        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_BAMBOO_SLAB.getBlock(), 150);
        FuelRegistry.INSTANCE.add(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.getBlock(), 150);
    }
}
