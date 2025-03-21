package games.enchanted.verticalslabs.item.creative_tab;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ModCreativeTabs {
    public static ModCreativeTab VERTICAL_SLABS_TAB = new ModCreativeTab(
        ModBlocks.VERTICAL_OAK_SLAB.blockItem(),
        ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "vertical_slabs"),
        new ArrayList<>(List.of(ModBlocks.VERTICAL_OAK_SLAB.blockItem(),
            ModBlocks.VERTICAL_SPRUCE_SLAB.blockItem(),
            ModBlocks.VERTICAL_BIRCH_SLAB.blockItem(),
            ModBlocks.VERTICAL_JUNGLE_SLAB.blockItem(),
            ModBlocks.VERTICAL_ACACIA_SLAB.blockItem(),
            ModBlocks.VERTICAL_DARK_OAK_SLAB.blockItem(),
            ModBlocks.VERTICAL_MANGROVE_SLAB.blockItem(),
            ModBlocks.VERTICAL_CHERRY_SLAB.blockItem(),
            ModBlocks.VERTICAL_PALE_OAK_SLAB.blockItem(),
            ModBlocks.VERTICAL_BAMBOO_SLAB.blockItem(),
            ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.blockItem(),
            ModBlocks.VERTICAL_CRIMSON_SLAB.blockItem(),
            ModBlocks.VERTICAL_WARPED_SLAB.blockItem(),
            ModBlocks.VERTICAL_STONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_COBBLESTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_STONE_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_GRANITE_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.blockItem(),
            ModBlocks.VERTICAL_DIORITE_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.blockItem(),
            ModBlocks.VERTICAL_ANDESITE_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.blockItem(),
            ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.blockItem(),
            ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.blockItem(),
            ModBlocks.VERTICAL_TUFF_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_TUFF_SLAB.blockItem(),
            ModBlocks.VERTICAL_TUFF_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_MUD_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_RESIN_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_SANDSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_PRISMARINE_SLAB.blockItem(),
            ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.blockItem(),
            ModBlocks.VERTICAL_NETHER_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_BLACKSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.blockItem(),
            ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.blockItem(),
            ModBlocks.VERTICAL_PURPUR_SLAB.blockItem(),
            ModBlocks.VERTICAL_QUARTZ_SLAB.blockItem(),
            ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.blockItem(),
            ModBlocks.VERTICAL_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.blockItem(),
            ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.blockItem()
        ))
    );

    public static void buildTabs(Function<ModCreativeTab, ModCreativeTab.FinalisedTab> tabBuilder) {
        Stream<ModCreativeTab.FinalisedTab> finalisedTabs = Stream.of(VERTICAL_SLABS_TAB).map(tabBuilder);
        finalisedTabs.forEach(creativeModeTab -> {
            EnchantedVerticalSlabsMod.register(BuiltInRegistries.CREATIVE_MODE_TAB.key(), creativeModeTab::creativeTab, creativeModeTab.location());
        });
    }
}
