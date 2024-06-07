package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.block.ModBlocks;
import net.minecraft.world.item.Item;

public class ModCreativeTabs {
    private static final ModCreativeTab.DummyCreativeTabData VERTICAL_SLABS_TAB = new ModCreativeTab.DummyCreativeTabData(
        ModBlocks.VERTICAL_OAK_SLAB.getBlockItem(),
        "vertical_slabs",
        new Item[]{
            ModBlocks.VERTICAL_OAK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_SPRUCE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_BIRCH_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_JUNGLE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_ACACIA_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_DARK_OAK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_MANGROVE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_CHERRY_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_BAMBOO_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_CRIMSON_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_WARPED_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_STONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_COBBLESTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_STONE_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_GRANITE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_DIORITE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_ANDESITE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_TUFF_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_TUFF_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_TUFF_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_MUD_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_SANDSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_PRISMARINE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_NETHER_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_BLACKSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_PURPUR_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_QUARTZ_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.getBlockItem(),
			ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.getBlockItem()
        }
    );

    /**
     * DummyCreativeTabData to be registered by either Fabric or NeoForge
     */
    public static ModCreativeTab.DummyCreativeTabData[] modCreativeTabs = {VERTICAL_SLABS_TAB};
}
