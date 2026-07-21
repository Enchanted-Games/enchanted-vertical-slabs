package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.registry.VerticalSlabBlocks;
import games.enchanted.verticalslabs.item.ModCreativeTab.ModCreativeTabEntry;
import games.enchanted.verticalslabs.item.ModCreativeTab.DummyCreativeTabData;
import games.enchanted.verticalslabs.mixin.CreativeModeTabsAccessor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class ModCreativeTabs {
	private static final ResourceKey<CreativeModeTab> buildingBlocksTab = CreativeModeTabsAccessor.getBuildingBlocksTab();

    private static final DummyCreativeTabData VERTICAL_SLABS_TAB = new DummyCreativeTabData(
        VerticalSlabBlocks.VERTICAL_OAK_SLAB.blockItem(),
        "vertical_slabs",
        new ModCreativeTabEntry[] {
            new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_OAK_SLAB.blockItem(), buildingBlocksTab, Items.OAK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_SPRUCE_SLAB.blockItem(), buildingBlocksTab, Items.SPRUCE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_BIRCH_SLAB.blockItem(), buildingBlocksTab, Items.BIRCH_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_JUNGLE_SLAB.blockItem(), buildingBlocksTab, Items.JUNGLE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_ACACIA_SLAB.blockItem(), buildingBlocksTab, Items.ACACIA_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_DARK_OAK_SLAB.blockItem(), buildingBlocksTab, Items.DARK_OAK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_MANGROVE_SLAB.blockItem(), buildingBlocksTab, Items.MANGROVE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_CHERRY_SLAB.blockItem(), buildingBlocksTab, Items.CHERRY_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_PALE_OAK_SLAB.blockItem(), buildingBlocksTab, Items.PALE_OAK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_BAMBOO_SLAB.blockItem(), buildingBlocksTab, Items.BAMBOO_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.blockItem(), buildingBlocksTab, Items.BAMBOO_MOSAIC_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_CRIMSON_SLAB.blockItem(), buildingBlocksTab, Items.CRIMSON_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_WARPED_SLAB.blockItem(), buildingBlocksTab, Items.WARPED_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_STONE_SLAB.blockItem(), buildingBlocksTab, Items.STONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_COBBLESTONE_SLAB.blockItem(), buildingBlocksTab, Items.COBBLESTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.blockItem(), buildingBlocksTab, Items.MOSSY_COBBLESTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_SMOOTH_STONE_SLAB.blockItem(), buildingBlocksTab, Items.SMOOTH_STONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_STONE_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.STONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.MOSSY_STONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_GRANITE_SLAB.blockItem(), buildingBlocksTab, Items.GRANITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_GRANITE_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_GRANITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_DIORITE_SLAB.blockItem(), buildingBlocksTab, Items.DIORITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_DIORITE_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_DIORITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_ANDESITE_SLAB.blockItem(), buildingBlocksTab, Items.ANDESITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_ANDESITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.blockItem(), buildingBlocksTab, Items.COBBLED_DEEPSLATE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_DEEPSLATE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.DEEPSLATE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.blockItem(), buildingBlocksTab, Items.DEEPSLATE_TILE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_TUFF_SLAB.blockItem(), buildingBlocksTab, Items.TUFF_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_TUFF_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_TUFF_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_TUFF_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.TUFF_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_MUD_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.MUD_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_RESIN_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.RESIN_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_SANDSTONE_SLAB.blockItem(), buildingBlocksTab, Items.SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.blockItem(), buildingBlocksTab, Items.SMOOTH_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_CUT_SANDSTONE_SLAB.blockItem(), buildingBlocksTab, Items.CUT_STANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_RED_SANDSTONE_SLAB.blockItem(), buildingBlocksTab, Items.RED_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.blockItem(), buildingBlocksTab, Items.SMOOTH_RED_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.blockItem(), buildingBlocksTab, Items.CUT_RED_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_PRISMARINE_SLAB.blockItem(), buildingBlocksTab, Items.PRISMARINE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.PRISMARINE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_DARK_PRISMARINE_SLAB.blockItem(), buildingBlocksTab, Items.DARK_PRISMARINE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_NETHER_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.NETHER_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.RED_NETHER_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_BLACKSTONE_SLAB.blockItem(), buildingBlocksTab, Items.BLACKSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_BLACKSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.POLISHED_BLACKSTONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_END_STONE_BRICK_SLAB.blockItem(), buildingBlocksTab, Items.END_STONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_PURPUR_SLAB.blockItem(), buildingBlocksTab, Items.PURPUR_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_QUARTZ_SLAB.blockItem(), buildingBlocksTab, Items.QUARTZ_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.blockItem(), buildingBlocksTab, Items.SMOOTH_QUARTZ_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.weathering().unaffected(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.weathering().exposed(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.weathering().weathered(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.weathering().oxidized(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.waxed().unaffected(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.waxed().exposed(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.waxed().weathered(), ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(VerticalSlabBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.blockItem(), buildingBlocksTab, Items.CUT_COPPER_SLAB.waxed().oxidized(), ModCreativeTab.INSERT_AFTER_RELATED)
        }
    );

    /**
     * DummyCreativeTabData to be registered by either Fabric or NeoForge
     */
    public static DummyCreativeTabData[] modCreativeTabs = {VERTICAL_SLABS_TAB};
}
