package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModCreativeTabs {

    private static final ModCreativeTab.DummyCreativeTabData verticalSlabsTab = new ModCreativeTab.DummyCreativeTabData(
        ModBlocks.VERTICAL_OAK_SLAB.asBlockItem(),
        "vertical_slabs",
        new Item[]{
            ModBlocks.VERTICAL_OAK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_SPRUCE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_BIRCH_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_JUNGLE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_ACACIA_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_DARK_OAK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_MANGROVE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_CHERRY_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_BAMBOO_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_CRIMSON_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_WARPED_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_STONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_COBBLESTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_STONE_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_GRANITE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_DIORITE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_ANDESITE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_MUD_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_SANDSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_PRISMARINE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_NETHER_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_BLACKSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_PURPUR_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_QUARTZ_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.asBlockItem(),
			ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.asBlockItem()
        }
    );

    /**
     * DummyCreativeTabData to be registered by either Fabric or NeoForge
     */
    public static ModCreativeTab.DummyCreativeTabData[] modCreativeTabs = {verticalSlabsTab};
}
