package games.enchanted.verticalslabs.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;

import static games.enchanted.verticalslabs.registry.RegistryHelpers.registerVerticalSlab;

public class ModBlocks {
    public static final BlockAndItemHolder VERTICAL_OAK_SLAB = registerVerticalSlab("vertical_oak_slab", Blocks.OAK_SLAB.properties());

    public static final BlockAndItemHolder VERTICAL_SPRUCE_SLAB = registerVerticalSlab("vertical_spruce_slab", Blocks.SPRUCE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_BIRCH_SLAB = registerVerticalSlab("vertical_birch_slab", Blocks.BIRCH_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_JUNGLE_SLAB = registerVerticalSlab("vertical_jungle_slab", Blocks.JUNGLE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_ACACIA_SLAB = registerVerticalSlab("vertical_acacia_slab", Blocks.ACACIA_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_DARK_OAK_SLAB = registerVerticalSlab("vertical_dark_oak_slab", Blocks.DARK_OAK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_MANGROVE_SLAB = registerVerticalSlab("vertical_mangrove_slab", Blocks.MANGROVE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_CHERRY_SLAB = registerVerticalSlab("vertical_cherry_slab", Blocks.CHERRY_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_CRIMSON_SLAB = registerVerticalSlab("vertical_crimson_slab", Blocks.CRIMSON_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_WARPED_SLAB = registerVerticalSlab("vertical_warped_slab", Blocks.WARPED_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_BAMBOO_SLAB = registerVerticalSlab("vertical_bamboo_slab", Blocks.BAMBOO_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_BAMBOO_MOSAIC_SLAB = registerVerticalSlab("vertical_bamboo_mosaic_slab", Blocks.BAMBOO_MOSAIC_SLAB.properties());

    // copper slabs
    public static final BlockAndItemHolder VERTICAL_CUT_COPPER_SLAB = registerVerticalSlab("vertical_cut_copper_slab", Blocks.CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.UNAFFECTED);
    public static final BlockAndItemHolder VERTICAL_EXPOSED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_exposed_cut_copper_slab", Blocks.EXPOSED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.EXPOSED);
    public static final BlockAndItemHolder VERTICAL_WEATHERED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_weathered_cut_copper_slab", Blocks.WEATHERED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.WEATHERED);
    public static final BlockAndItemHolder VERTICAL_OXIDIZED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_oxidized_cut_copper_slab", Blocks.OXIDIZED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.OXIDIZED);

    public static final BlockAndItemHolder VERTICAL_WAXED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_cut_copper_slab", Blocks.WAXED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.UNAFFECTED);
    public static final BlockAndItemHolder VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_exposed_cut_copper_slab", Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.EXPOSED);
    public static final BlockAndItemHolder VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_weathered_cut_copper_slab", Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.WEATHERED);
    public static final BlockAndItemHolder VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_oxidized_cut_copper_slab", Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB.properties(), WeatheringCopper.WeatherState.OXIDIZED);

    // others
    public static final BlockAndItemHolder VERTICAL_STONE_SLAB = registerVerticalSlab("vertical_stone_slab", Blocks.STONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_SMOOTH_STONE_SLAB = registerVerticalSlab("vertical_smooth_stone_slab", Blocks.SMOOTH_STONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_SANDSTONE_SLAB = registerVerticalSlab("vertical_sandstone_slab", Blocks.SANDSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_CUT_SANDSTONE_SLAB = registerVerticalSlab("vertical_cut_sandstone_slab", Blocks.CUT_SANDSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_PETRIFIED_OAK_SLAB = registerVerticalSlab("vertical_petrified_oak_slab", Blocks.PETRIFIED_OAK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_COBBLESTONE_SLAB = registerVerticalSlab("vertical_cobblestone_slab", Blocks.COBBLESTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_BRICK_SLAB = registerVerticalSlab("vertical_brick_slab", Blocks.BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_STONE_BRICK_SLAB = registerVerticalSlab("vertical_stone_brick_slab", Blocks.STONE_BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_QUARTZ_SLAB = registerVerticalSlab("vertical_quartz_slab", Blocks.QUARTZ_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_SMOOTH_QUARTZ_SLAB = registerVerticalSlab("vertical_smooth_quartz_slab", Blocks.SMOOTH_QUARTZ_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_RED_SANDSTONE_SLAB = registerVerticalSlab("vertical_red_sandstone_slab", Blocks.RED_SANDSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_CUT_RED_SANDSTONE_SLAB = registerVerticalSlab("vertical_cut_red_sandstone_slab", Blocks.CUT_RED_SANDSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_PURPUR_SLAB = registerVerticalSlab("vertical_purpur_slab", Blocks.PURPUR_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_PRISMARINE_SLAB = registerVerticalSlab("vertical_prismarine_slab", Blocks.PRISMARINE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_PRISMARINE_BRICK_SLAB = registerVerticalSlab("vertical_prismarine_brick_slab", Blocks.PRISMARINE_BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_DARK_PRISMARINE_SLAB = registerVerticalSlab("vertical_dark_prismarine_slab", Blocks.DARK_PRISMARINE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_GRANITE_SLAB = registerVerticalSlab("vertical_polished_granite_slab", Blocks.POLISHED_GRANITE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = registerVerticalSlab("vertical_smooth_red_sandstone_slab", Blocks.SMOOTH_RED_SANDSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_MOSSY_STONE_BRICK_SLAB = registerVerticalSlab("vertical_mossy_stone_brick_slab", Blocks.MOSSY_STONE_BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_DIORITE_SLAB = registerVerticalSlab("vertical_polished_diorite_slab", Blocks.POLISHED_DIORITE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_MOSSY_COBBLESTONE_SLAB = registerVerticalSlab("vertical_mossy_cobblestone_slab", Blocks.MOSSY_COBBLESTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_ENDSTONE_BRICK_SLAB = registerVerticalSlab("vertical_end_stone_brick_slab", Blocks.END_STONE_BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_SMOOTH_SANDSTONE_SLAB = registerVerticalSlab("vertical_smooth_sandstone_slab", Blocks.SMOOTH_SANDSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_GRANITE_SLAB = registerVerticalSlab("vertical_granite_slab", Blocks.GRANITE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_ANDESITE_SLAB = registerVerticalSlab("vertical_andesite_slab", Blocks.ANDESITE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_ANDESITE_SLAB = registerVerticalSlab("vertical_polished_andesite_slab", Blocks.POLISHED_ANDESITE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_DIORITE_SLAB = registerVerticalSlab("vertical_diorite_slab", Blocks.DIORITE_SLAB.properties());

    public static final BlockAndItemHolder VERTICAL_RED_NETHER_BRICK_SLAB = registerVerticalSlab("vertical_red_nether_brick_slab", Blocks.RED_NETHER_BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_NETHER_BRICK_SLAB = registerVerticalSlab("vertical_nether_brick_slab", Blocks.NETHER_BRICK_SLAB.properties());

    public static final BlockAndItemHolder VERTICAL_COBBLED_DEEPSLATE_SLAB = registerVerticalSlab("vertical_cobbled_deepslate_slab", Blocks.COBBLED_DEEPSLATE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_DEEPSLATE_SLAB = registerVerticalSlab("vertical_polished_deepslate_slab", Blocks.POLISHED_DEEPSLATE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_DEEPSLATE_BRICK_SLAB = registerVerticalSlab("vertical_deepslate_brick_slab", Blocks.DEEPSLATE_BRICK_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_DEEPSLATE_TILE_SLAB = registerVerticalSlab("vertical_deepslate_tile_slab", Blocks.DEEPSLATE_TILE_SLAB.properties());

    public static final BlockAndItemHolder VERTICAL_BLACKSTONE_SLAB = registerVerticalSlab("vertical_blackstone_slab", Blocks.BLACKSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_BLACKSTONE_SLAB = registerVerticalSlab("vertical_polished_blackstone_slab", Blocks.POLISHED_BLACKSTONE_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = registerVerticalSlab("vertical_polished_blackstone_brick_slab", Blocks.POLISHED_BLACKSTONE_BRICK_SLAB.properties());

    public static final BlockAndItemHolder VERTICAL_MUD_BRICK_SLAB = registerVerticalSlab("vertical_mud_brick_slab", Blocks.MUD_BRICK_SLAB.properties());

    public static final BlockAndItemHolder VERTICAL_TUFF_SLAB = registerVerticalSlab("vertical_tuff_slab", Blocks.TUFF_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_POLISHED_TUFF_SLAB = registerVerticalSlab("vertical_polished_tuff_slab", Blocks.POLISHED_TUFF_SLAB.properties());
    public static final BlockAndItemHolder VERTICAL_TUFF_BRICK_SLAB = registerVerticalSlab("vertical_tuff_brick_slab", Blocks.TUFF_BRICK_SLAB.properties());

    /**
     * Calls the class to register blocks
     */
    public static void register() {};
}
