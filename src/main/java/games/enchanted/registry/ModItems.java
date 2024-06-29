package games.enchanted.registry;

import games.enchanted.VerticalSlabs;
import games.enchanted.registry.types.VerticalSlabItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
	// block items

	// coppers
	public static final BlockItem VERTICAL_CUT_COPPER_SLAB = registerBlockItem(ModBlocks.VERTICAL_CUT_COPPER_SLAB,
			"vertical_cut_copper_slab");
	public static final BlockItem VERTICAL_EXPOSED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB, "vertical_exposed_cut_copper_slab");
	public static final BlockItem VERTICAL_WEATHERED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB, "vertical_weathered_cut_copper_slab");
	public static final BlockItem VERTICAL_OXIDIZED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB, "vertical_oxidized_cut_copper_slab");

	public static final BlockItem VERTICAL_WAXED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB, "vertical_waxed_cut_copper_slab");
	public static final BlockItem VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB, "vertical_waxed_exposed_cut_copper_slab");
	public static final BlockItem VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB, "vertical_waxed_weathered_cut_copper_slab");
	public static final BlockItem VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB, "vertical_waxed_oxidized_cut_copper_slab");

	// wood slabs
	public static final BlockItem VERTICAL_OAK_SLAB = registerBlockItem(ModBlocks.VERTICAL_OAK_SLAB,
			"vertical_oak_slab");
	public static final BlockItem VERTICAL_SPRUCE_SLAB = registerBlockItem(ModBlocks.VERTICAL_SPRUCE_SLAB,
			"vertical_spruce_slab");
	public static final BlockItem VERTICAL_BIRCH_SLAB = registerBlockItem(ModBlocks.VERTICAL_BIRCH_SLAB,
			"vertical_birch_slab");
	public static final BlockItem VERTICAL_JUNGLE_SLAB = registerBlockItem(ModBlocks.VERTICAL_JUNGLE_SLAB,
			"vertical_jungle_slab");
	public static final BlockItem VERTICAL_ACACIA_SLAB = registerBlockItem(ModBlocks.VERTICAL_ACACIA_SLAB,
			"vertical_acacia_slab");
	public static final BlockItem VERTICAL_DARK_OAK_SLAB = registerBlockItem(ModBlocks.VERTICAL_DARK_OAK_SLAB,
			"vertical_dark_oak_slab");
	public static final BlockItem VERTICAL_MANGROVE_SLAB = registerBlockItem(ModBlocks.VERTICAL_MANGROVE_SLAB,
			"vertical_mangrove_slab");
	public static final BlockItem VERTICAL_CHERRY_SLAB = registerBlockItem(ModBlocks.VERTICAL_CHERRY_SLAB,
			"vertical_cherry_slab");
	public static final BlockItem VERTICAL_CRIMSON_SLAB = registerBlockItem(ModBlocks.VERTICAL_CRIMSON_SLAB,
			"vertical_crimson_slab");
	public static final BlockItem VERTICAL_WARPED_SLAB = registerBlockItem(ModBlocks.VERTICAL_WARPED_SLAB,
			"vertical_warped_slab");

	// stone slabs
	public static final BlockItem VERTICAL_STONE_SLAB = registerBlockItem(ModBlocks.VERTICAL_STONE_SLAB,
			"vertical_stone_slab");
	public static final BlockItem VERTICAL_SMOOTH_STONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_SMOOTH_STONE_SLAB,
			"vertical_smooth_stone_slab");
	public static final BlockItem VERTICAL_SANDSTONE_SLAB = registerBlockItem(ModBlocks.VERTICAL_SANDSTONE_SLAB,
			"vertical_sandstone_slab");
	public static final BlockItem VERTICAL_CUT_SANDSTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB,
			"vertical_cut_sandstone_slab");
	public static final BlockItem VERTICAL_PETRIFIED_OAK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_PETRIFIED_OAK_SLAB,
			"vertical_petrified_oak_slab");
	public static final BlockItem VERTICAL_COBBLESTONE_SLAB = registerBlockItem(ModBlocks.VERTICAL_COBBLESTONE_SLAB,
			"vertical_cobblestone_slab");
	public static final BlockItem VERTICAL_BRICK_SLAB = registerBlockItem(ModBlocks.VERTICAL_BRICK_SLAB,
			"vertical_brick_slab");
	public static final BlockItem VERTICAL_STONE_BRICK_SLAB = registerBlockItem(ModBlocks.VERTICAL_STONE_BRICK_SLAB,
			"vertical_stone_brick_slab");
	public static final BlockItem VERTICAL_NETHER_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_NETHER_BRICK_SLAB,
			"vertical_nether_brick_slab");
	public static final BlockItem VERTICAL_MUD_BRICK_SLAB = registerBlockItem(ModBlocks.VERTICAL_MUD_BRICK_SLAB,
			"vertical_mud_brick_slab");
	public static final BlockItem VERTICAL_QUARTZ_SLAB = registerBlockItem(ModBlocks.VERTICAL_QUARTZ_SLAB,
			"vertical_quartz_slab");
	public static final BlockItem VERTICAL_SMOOTH_QUARTZ_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB,
			"vertical_smooth_quartz_slab");
	public static final BlockItem VERTICAL_RED_SANDSTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_RED_SANDSTONE_SLAB,
			"vertical_red_sandstone_slab");
	public static final BlockItem VERTICAL_CUT_RED_SANDSTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB, "vertical_cut_red_sandstone_slab");
	public static final BlockItem VERTICAL_PURPUR_SLAB = registerBlockItem(ModBlocks.VERTICAL_PURPUR_SLAB,
			"vertical_purpur_slab");
	public static final BlockItem VERTICAL_PRISMARINE_SLAB = registerBlockItem(ModBlocks.VERTICAL_PRISMARINE_SLAB,
			"vertical_prismarine_slab");
	public static final BlockItem VERTICAL_PRISMARINE_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB, "vertical_prismarine_brick_slab");
	public static final BlockItem VERTICAL_DARK_PRISMARINE__SLAB = registerBlockItem(
			ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB, "vertical_dark_prismarine_slab");
	public static final BlockItem VERTICAL_POLISHED_GRANITE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB, "vertical_polished_granite_slab");
	public static final BlockItem VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB, "vertical_smooth_red_sandstone_slab");
	public static final BlockItem VERTICAL_MOSSY_STONE_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB, "vertical_mossy_stone_brick_slab");
	public static final BlockItem VERTICAL_POLISHED_DIORITE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB, "vertical_polished_diorite_slab");
	public static final BlockItem VERTICAL_MOSSY_COBBLESTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB, "vertical_mossy_cobblestone_slab");
	public static final BlockItem VERTICAL_ENDSTONE_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB, "vertical_end_stone_brick_slab");
	public static final BlockItem VERTICAL_SMOOTH_SANDSTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB, "vertical_smooth_sandstone_slab");
	public static final BlockItem VERTICAL_GRANITE_SLAB = registerBlockItem(ModBlocks.VERTICAL_GRANITE_SLAB,
			"vertical_granite_slab");
	public static final BlockItem VERTICAL_ANDESITE_SLAB = registerBlockItem(ModBlocks.VERTICAL_ANDESITE_SLAB,
			"vertical_andesite_slab");
	public static final BlockItem VERTICAL_RED_NETHER_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB, "vertical_red_nether_brick_slab");
	public static final BlockItem VERTICAL_POLISHED_ANDESITE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB, "vertical_polished_andesite_slab");
	public static final BlockItem VERTICAL_DIORITE_SLAB = registerBlockItem(ModBlocks.VERTICAL_DIORITE_SLAB,
			"vertical_diorite_slab");
	public static final BlockItem VERTICAL_COBBLED_DEEPSLATE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB, "vertical_cobbled_deepslate_slab");
	public static final BlockItem VERTICAL_POLISHED_DEEPSLATE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB, "vertical_polished_deepslate_slab");
	public static final BlockItem VERTICAL_DEEPSLATE_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB, "vertical_deepslate_brick_slab");
	public static final BlockItem VERTICAL_DEEPSLATE_TILE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB, "vertical_deepslate_tile_slab");
	public static final BlockItem VERTICAL_BLACKSTONE_SLAB = registerBlockItem(ModBlocks.VERTICAL_BLACKSTONE_SLAB,
			"vertical_blackstone_slab");
	public static final BlockItem VERTICAL_POLISHED_BLACKSTONE_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB, "vertical_polished_blackstone_slab");
	public static final BlockItem VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB, "vertical_polished_blackstone_brick_slab");

	public static final BlockItem VERTICAL_BAMBOO_SLAB = registerBlockItem(ModBlocks.VERTICAL_BAMBOO_SLAB,
			"vertical_bamboo_slab");
	public static final BlockItem VERTICAL_BAMBOO_MOSAIC_SLAB = registerBlockItem(
			ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB,
			"vertical_bamboo_mosaic_slab");

	// registers all block items
	// item groups are done in the ModItemGroups class
	public static BlockItem registerBlockItem(Block blockId, String id) {
		Item.Settings settings = new Item.Settings();
		return Registry.register(Registries.ITEM, new Identifier(VerticalSlabs.MOD_ID, id),
				new VerticalSlabItem(blockId, settings));
	}
	// public static BlockItem registerBlockItemWithFeatureFlag(Block blockId
	// ,String id, FeatureFlag featureFlag) {
	// Item.Settings settings = new Item.Settings().requires(featureFlag);
	// return Registry.register(Registries.ITEM, new
	// Identifier(VerticalSlabs.MOD_ID, id), new VerticalSlabItem(blockId,
	// settings));
	// }

	public static void registerItems() {
	}
}
