package games.enchanted.verticalslabs.block;

import net.minecraft.world.level.block.WeatheringCopper;

import static games.enchanted.verticalslabs.registry.RegistryHelpers.registerVerticalSlab;

public class ModBlocks {
    public static final BlockAndItem VERTICAL_OAK_SLAB = registerVerticalSlab("vertical_oak_slab", ModBlockBehaviours.GENERIC_WOOD);

    public static final BlockAndItem VERTICAL_SPRUCE_SLAB =  registerVerticalSlab("vertical_spruce_slab", ModBlockBehaviours.GENERIC_WOOD);
    public static final BlockAndItem VERTICAL_BIRCH_SLAB =  registerVerticalSlab("vertical_birch_slab", ModBlockBehaviours.GENERIC_WOOD);
    public static final BlockAndItem VERTICAL_JUNGLE_SLAB =  registerVerticalSlab("vertical_jungle_slab", ModBlockBehaviours.GENERIC_WOOD);
    public static final BlockAndItem VERTICAL_ACACIA_SLAB =  registerVerticalSlab("vertical_acacia_slab", ModBlockBehaviours.GENERIC_WOOD);
    public static final BlockAndItem VERTICAL_DARK_OAK_SLAB =  registerVerticalSlab("vertical_dark_oak_slab", ModBlockBehaviours.GENERIC_WOOD);
    public static final BlockAndItem VERTICAL_MANGROVE_SLAB = registerVerticalSlab("vertical_mangrove_slab", ModBlockBehaviours.GENERIC_WOOD);
    public static final BlockAndItem VERTICAL_CHERRY_SLAB = registerVerticalSlab("vertical_cherry_slab", ModBlockBehaviours.CHERRY_WOOD);
    public static final BlockAndItem VERTICAL_CRIMSON_SLAB = registerVerticalSlab("vertical_crimson_slab", ModBlockBehaviours.NETHER_WOOD);
    public static final BlockAndItem VERTICAL_WARPED_SLAB = registerVerticalSlab("vertical_warped_slab", ModBlockBehaviours.NETHER_WOOD);
    public static final BlockAndItem VERTICAL_BAMBOO_SLAB = registerVerticalSlab("vertical_bamboo_slab", ModBlockBehaviours.BAMBOO_WOOD);
    public static final BlockAndItem VERTICAL_BAMBOO_MOSAIC_SLAB = registerVerticalSlab("vertical_bamboo_mosaic_slab", ModBlockBehaviours.BAMBOO_WOOD);

    // copper slabs
    public static final BlockAndItem VERTICAL_CUT_COPPER_SLAB = registerVerticalSlab("vertical_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.UNAFFECTED);
    public static final BlockAndItem VERTICAL_EXPOSED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_exposed_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.EXPOSED);
    public static final BlockAndItem VERTICAL_WEATHERED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_weathered_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.WEATHERED);
    public static final BlockAndItem VERTICAL_OXIDIZED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_oxidized_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.OXIDIZED);

    public static final BlockAndItem VERTICAL_WAXED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.UNAFFECTED);
    public static final BlockAndItem VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_exposed_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.EXPOSED);
    public static final BlockAndItem VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_weathered_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.WEATHERED);
    public static final BlockAndItem VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = registerVerticalSlab("vertical_waxed_oxidized_cut_copper_slab", ModBlockBehaviours.COPPER, WeatheringCopper.WeatherState.OXIDIZED);

    // others
    public static final BlockAndItem VERTICAL_STONE_SLAB = registerVerticalSlab("vertical_stone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_SMOOTH_STONE_SLAB = registerVerticalSlab("vertical_smooth_stone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_SANDSTONE_SLAB = registerVerticalSlab("vertical_sandstone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_CUT_SANDSTONE_SLAB = registerVerticalSlab("vertical_cut_sandstone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_PETRIFIED_OAK_SLAB = registerVerticalSlab("vertical_petrified_oak_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_COBBLESTONE_SLAB = registerVerticalSlab("vertical_cobblestone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_BRICK_SLAB = registerVerticalSlab("vertical_brick_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_STONE_BRICK_SLAB = registerVerticalSlab("vertical_stone_brick_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_QUARTZ_SLAB = registerVerticalSlab("vertical_quartz_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_SMOOTH_QUARTZ_SLAB = registerVerticalSlab("vertical_smooth_quartz_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_RED_SANDSTONE_SLAB = registerVerticalSlab("vertical_red_sandstone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_CUT_RED_SANDSTONE_SLAB = registerVerticalSlab("vertical_cut_red_sandstone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_PURPUR_SLAB = registerVerticalSlab("vertical_purpur_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_PRISMARINE_SLAB = registerVerticalSlab("vertical_prismarine_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_PRISMARINE_BRICK_SLAB = registerVerticalSlab("vertical_prismarine_brick_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_DARK_PRISMARINE_SLAB = registerVerticalSlab("vertical_dark_prismarine_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_POLISHED_GRANITE_SLAB = registerVerticalSlab("vertical_polished_granite_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = registerVerticalSlab("vertical_smooth_red_sandstone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_MOSSY_STONE_BRICK_SLAB = registerVerticalSlab("vertical_mossy_stone_brick_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_POLISHED_DIORITE_SLAB = registerVerticalSlab("vertical_polished_diorite_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_MOSSY_COBBLESTONE_SLAB = registerVerticalSlab("vertical_mossy_cobblestone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_ENDSTONE_BRICK_SLAB = registerVerticalSlab("vertical_end_stone_brick_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_SMOOTH_SANDSTONE_SLAB = registerVerticalSlab("vertical_smooth_sandstone_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_GRANITE_SLAB = registerVerticalSlab("vertical_granite_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_ANDESITE_SLAB = registerVerticalSlab("vertical_andesite_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_RED_NETHER_BRICK_SLAB = registerVerticalSlab("vertical_red_nether_brick_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_POLISHED_ANDESITE_SLAB = registerVerticalSlab("vertical_polished_andesite_slab", ModBlockBehaviours.STONE);
    public static final BlockAndItem VERTICAL_DIORITE_SLAB = registerVerticalSlab("vertical_diorite_slab", ModBlockBehaviours.STONE);

    public static final BlockAndItem VERTICAL_NETHER_BRICK_SLAB = registerVerticalSlab("vertical_nether_brick_slab", ModBlockBehaviours.NETHER_BRICK);

    public static final BlockAndItem VERTICAL_COBBLED_DEEPSLATE_SLAB = registerVerticalSlab("vertical_cobbled_deepslate_slab", ModBlockBehaviours.COBBLED_DEEPSLATE);
    public static final BlockAndItem VERTICAL_POLISHED_DEEPSLATE_SLAB = registerVerticalSlab("vertical_polished_deepslate_slab", ModBlockBehaviours.DEEPSLATE);
    public static final BlockAndItem VERTICAL_DEEPSLATE_BRICK_SLAB = registerVerticalSlab("vertical_deepslate_brick_slab", ModBlockBehaviours.DEEPSLATE_BRICK);
    public static final BlockAndItem VERTICAL_DEEPSLATE_TILE_SLAB = registerVerticalSlab("vertical_deepslate_tile_slab", ModBlockBehaviours.DEEPSLATE_TILE);

    public static final BlockAndItem VERTICAL_BLACKSTONE_SLAB = registerVerticalSlab("vertical_blackstone_slab", ModBlockBehaviours.BLACKSTONE);
    public static final BlockAndItem VERTICAL_POLISHED_BLACKSTONE_SLAB = registerVerticalSlab("vertical_polished_blackstone_slab", ModBlockBehaviours.BLACKSTONE);
    public static final BlockAndItem VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = registerVerticalSlab("vertical_polished_blackstone_brick_slab", ModBlockBehaviours.BLACKSTONE);

    public static final BlockAndItem VERTICAL_MUD_BRICK_SLAB = registerVerticalSlab("vertical_mud_brick_slab", ModBlockBehaviours.MUD_BRICK);

    public static final BlockAndItem VERTICAL_TUFF_SLAB = registerVerticalSlab("vertical_tuff_slab", ModBlockBehaviours.TUFF);
    public static final BlockAndItem VERTICAL_POLISHED_TUFF_SLAB = registerVerticalSlab("vertical_polished_tuff_slab", ModBlockBehaviours.POLISHED_TUFF);
    public static final BlockAndItem VERTICAL_TUFF_BRICK_SLAB = registerVerticalSlab("vertical_tuff_brick_slab", ModBlockBehaviours.TUFF_BRICK);

    /**
     * Calls the class to register blocks
     */
    public static void register() {};
}
