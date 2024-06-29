package games.enchanted.registry;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;

public class ModFabricRegistries {

    public static void registerFlammables(){
        // Flammable blocks
        int Encouragement = 5;
        int Flammability = 20;

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_OAK_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_SPRUCE_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_BIRCH_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_JUNGLE_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_ACACIA_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_DARK_OAK_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_MANGROVE_SLAB, Encouragement, Flammability);
        
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_BAMBOO_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB, Encouragement, Flammability);
    }

    public static void registerOxidisables(){
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.VERTICAL_CUT_COPPER_SLAB, ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
        
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.VERTICAL_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
    }

}
