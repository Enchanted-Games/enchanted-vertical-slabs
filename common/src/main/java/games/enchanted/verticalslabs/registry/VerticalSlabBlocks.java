package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;

import static games.enchanted.verticalslabs.registry.RegistryHelpers.registerVerticalSlab;

public class VerticalSlabBlocks {
    public static final BlockAndItemContainer VERTICAL_OAK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_OAK_SLAB, Blocks.OAK_SLAB.properties());

    public static final BlockAndItemContainer VERTICAL_SPRUCE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_SPRUCE_SLAB, Blocks.SPRUCE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_BIRCH_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_BIRCH_SLAB, Blocks.BIRCH_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_JUNGLE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_JUNGLE_SLAB, Blocks.JUNGLE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_ACACIA_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_ACACIA_SLAB, Blocks.ACACIA_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_DARK_OAK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_DARK_OAK_SLAB, Blocks.DARK_OAK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_MANGROVE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_MANGROVE_SLAB, Blocks.MANGROVE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_CHERRY_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_CHERRY_SLAB, Blocks.CHERRY_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_CRIMSON_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_CRIMSON_SLAB, Blocks.CRIMSON_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_WARPED_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_WARPED_SLAB, Blocks.WARPED_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_BAMBOO_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_BAMBOO_SLAB, Blocks.BAMBOO_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_BAMBOO_MOSAIC_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_BAMBOO_MOSAIC_SLAB, Blocks.BAMBOO_MOSAIC_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_PALE_OAK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_PALE_OAK_SLAB, Blocks.PALE_OAK_SLAB.properties());

    // copper slabs
    public static final BlockAndItemContainer VERTICAL_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.weathering().unaffected().properties(), WeatheringCopper.WeatherState.UNAFFECTED);
    public static final BlockAndItemContainer VERTICAL_EXPOSED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_EXPOSED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.weathering().exposed().properties(), WeatheringCopper.WeatherState.EXPOSED);
    public static final BlockAndItemContainer VERTICAL_WEATHERED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_WEATHERED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.weathering().weathered().properties(), WeatheringCopper.WeatherState.WEATHERED);
    public static final BlockAndItemContainer VERTICAL_OXIDIZED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_OXIDIZED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.weathering().oxidized().properties(), WeatheringCopper.WeatherState.OXIDIZED);

    public static final BlockAndItemContainer VERTICAL_WAXED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_WAXED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.waxed().unaffected().properties(), WeatheringCopper.WeatherState.UNAFFECTED);
    public static final BlockAndItemContainer VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.waxed().exposed().properties(), WeatheringCopper.WeatherState.EXPOSED);
    public static final BlockAndItemContainer VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.waxed().weathered().properties(), WeatheringCopper.WeatherState.WEATHERED);
    public static final BlockAndItemContainer VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB.waxed().oxidized().properties(), WeatheringCopper.WeatherState.OXIDIZED);

    // others
    public static final BlockAndItemContainer VERTICAL_STONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_STONE_SLAB, Blocks.STONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_SMOOTH_STONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_SMOOTH_STONE_SLAB, Blocks.SMOOTH_STONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_SANDSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_SANDSTONE_SLAB, Blocks.SANDSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_CUT_SANDSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_CUT_SANDSTONE_SLAB, Blocks.CUT_SANDSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_PETRIFIED_OAK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_PETRIFIED_OAK_SLAB, Blocks.PETRIFIED_OAK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_COBBLESTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_COBBLESTONE_SLAB, Blocks.COBBLESTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_BRICK_SLAB, Blocks.BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_STONE_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_STONE_BRICK_SLAB, Blocks.STONE_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_QUARTZ_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_QUARTZ_SLAB, Blocks.QUARTZ_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_SMOOTH_QUARTZ_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_SMOOTH_QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_RED_SANDSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_RED_SANDSTONE_SLAB, Blocks.RED_SANDSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_CUT_RED_SANDSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_CUT_RED_SANDSTONE_SLAB, Blocks.CUT_RED_SANDSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_PURPUR_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_PURPUR_SLAB, Blocks.PURPUR_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_PRISMARINE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_PRISMARINE_SLAB, Blocks.PRISMARINE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_PRISMARINE_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_DARK_PRISMARINE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_GRANITE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_MOSSY_STONE_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_DIORITE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_MOSSY_COBBLESTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_END_STONE_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_SMOOTH_SANDSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_GRANITE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_GRANITE_SLAB, Blocks.GRANITE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_ANDESITE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_ANDESITE_SLAB, Blocks.ANDESITE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_ANDESITE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_DIORITE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_DIORITE_SLAB, Blocks.DIORITE_SLAB.properties());

    public static final BlockAndItemContainer VERTICAL_RED_NETHER_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_RED_NETHER_BRICK_SLAB, Blocks.RED_NETHER_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_NETHER_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB.properties());

    public static final BlockAndItemContainer VERTICAL_COBBLED_DEEPSLATE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_DEEPSLATE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_DEEPSLATE_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_DEEPSLATE_TILE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_SLAB.properties());

    public static final BlockAndItemContainer VERTICAL_BLACKSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_BLACKSTONE_SLAB, Blocks.BLACKSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_BLACKSTONE_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB.properties());

    public static final BlockAndItemContainer VERTICAL_MUD_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_MUD_BRICK_SLAB, Blocks.MUD_BRICK_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_RESIN_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_RESIN_BRICK_SLAB, Blocks.RESIN_BRICK_SLAB.properties());

    public static final BlockAndItemContainer VERTICAL_TUFF_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_TUFF_SLAB, Blocks.TUFF_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_POLISHED_TUFF_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_POLISHED_TUFF_SLAB, Blocks.POLISHED_TUFF_SLAB.properties());
    public static final BlockAndItemContainer VERTICAL_TUFF_BRICK_SLAB = registerVerticalSlab(VerticalSlabIds.VERTICAL_TUFF_BRICK_SLAB, Blocks.TUFF_BRICK_SLAB.properties());


    /**
     * Calls the class to register blocks
     */
    public static void register() {};
}
