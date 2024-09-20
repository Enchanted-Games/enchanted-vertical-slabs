package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.ModCreativeTab.ModCreativeTabEntry;
import games.enchanted.verticalslabs.item.ModCreativeTab.DummyCreativeTabData;
import games.enchanted.verticalslabs.mixin.CreativeModeTabsAccessor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class ModCreativeTabs {
	private static final ResourceKey<CreativeModeTab> buildingBlocksTab = CreativeModeTabsAccessor.getBuildingBlocksTab();

    private static final DummyCreativeTabData VERTICAL_SLABS_TAB = new DummyCreativeTabData(
        ModBlocks.VERTICAL_OAK_SLAB.getBlockItem().orElseThrow(),
        "vertical_slabs",
        new ModCreativeTabEntry[] {
            new ModCreativeTabEntry(ModBlocks.VERTICAL_OAK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.OAK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_SPRUCE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.SPRUCE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_BIRCH_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.BIRCH_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_JUNGLE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.JUNGLE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_ACACIA_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.ACACIA_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_DARK_OAK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.DARK_OAK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_MANGROVE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.MANGROVE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_CHERRY_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.CHERRY_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_BAMBOO_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.BAMBOO_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.BAMBOO_MOSAIC_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_CRIMSON_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.CRIMSON_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_WARPED_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.WARPED_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_STONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.STONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_COBBLESTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.COBBLESTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.MOSSY_COBBLESTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.SMOOTH_STONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_STONE_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.STONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.MOSSY_STONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_GRANITE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.GRANITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_GRANITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_DIORITE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.DIORITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_DIORITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_ANDESITE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.ANDESITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_ANDESITE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.COBBLED_DEEPSLATE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_DEEPSLATE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.DEEPSLATE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.DEEPSLATE_TILE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_TUFF_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.TUFF_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_TUFF_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_TUFF_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_TUFF_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.TUFF_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_MUD_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.MUD_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_SANDSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.SMOOTH_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.CUT_STANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.RED_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.SMOOTH_RED_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.CUT_RED_SANDSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_PRISMARINE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.PRISMARINE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.PRISMARINE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.DARK_PRISMARINE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_NETHER_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.NETHER_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.RED_NETHER_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_BLACKSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.BLACKSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_BLACKSTONE_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.POLISHED_BLACKSTONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.END_STONE_BRICK_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_PURPUR_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.PURPUR_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_QUARTZ_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.QUARTZ_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.SMOOTH_QUARTZ_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.EXPOSED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.WEATHERED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.OXIDIZED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.WAXED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.WAXED_EXPOSED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.WAXED_WEATHERED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED),
			new ModCreativeTabEntry(ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.getBlockItem().orElseThrow(), buildingBlocksTab, Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, ModCreativeTab.INSERT_AFTER_RELATED)
        }
    );

    /**
     * DummyCreativeTabData to be registered by either Fabric or NeoForge
     */
    public static DummyCreativeTabData[] modCreativeTabs = {VERTICAL_SLABS_TAB};
}
