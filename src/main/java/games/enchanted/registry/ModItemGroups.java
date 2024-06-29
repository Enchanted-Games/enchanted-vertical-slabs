package games.enchanted.registry;

import games.enchanted.VerticalSlabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

	public static void createItemGroups() {

		final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
				.icon(() -> new ItemStack(ModBlocks.VERTICAL_OAK_SLAB))
				.displayName(Text.translatable("itemGroup.enchanted-vertical-slabs.vertical_slabs"))
				.build();
		Registry.register(Registries.ITEM_GROUP, new Identifier(VerticalSlabs.MOD_ID, "vertical_slabs"), ITEM_GROUP);

		final RegistryKey<ItemGroup> ITEM_GROUP_REGISTRY_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
				new Identifier(VerticalSlabs.MOD_ID, "vertical_slabs"));

		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_REGISTRY_KEY).register(content -> {
			content.add(ModBlocks.VERTICAL_OAK_SLAB);
			content.add(ModBlocks.VERTICAL_SPRUCE_SLAB);
			content.add(ModBlocks.VERTICAL_BIRCH_SLAB);
			content.add(ModBlocks.VERTICAL_JUNGLE_SLAB);
			content.add(ModBlocks.VERTICAL_ACACIA_SLAB);
			content.add(ModBlocks.VERTICAL_DARK_OAK_SLAB);
			content.add(ModBlocks.VERTICAL_MANGROVE_SLAB);
			content.add(ModBlocks.VERTICAL_CHERRY_SLAB);
			content.add(ModBlocks.VERTICAL_BAMBOO_SLAB);
			content.add(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB);
			content.add(ModBlocks.VERTICAL_CRIMSON_SLAB);
			content.add(ModBlocks.VERTICAL_WARPED_SLAB);
			content.add(ModBlocks.VERTICAL_STONE_SLAB);
			content.add(ModBlocks.VERTICAL_COBBLESTONE_SLAB);
			content.add(ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB);
			content.add(ModBlocks.VERTICAL_SMOOTH_STONE_SLAB);
			content.add(ModBlocks.VERTICAL_STONE_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_GRANITE_SLAB);
			content.add(ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB);
			content.add(ModBlocks.VERTICAL_DIORITE_SLAB);
			content.add(ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB);
			content.add(ModBlocks.VERTICAL_ANDESITE_SLAB);
			content.add(ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB);
			content.add(ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB);
			content.add(ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB);
			content.add(ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB);
			content.add(ModBlocks.VERTICAL_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_MUD_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_SANDSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_RED_SANDSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_PRISMARINE_SLAB);
			content.add(ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB);
			content.add(ModBlocks.VERTICAL_NETHER_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_BLACKSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB);
			content.add(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB);
			content.add(ModBlocks.VERTICAL_PURPUR_SLAB);
			content.add(ModBlocks.VERTICAL_QUARTZ_SLAB);
			content.add(ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB);
			content.add(ModBlocks.VERTICAL_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
			content.add(ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
			content.addAfter(Items.OAK_SLAB, ModBlocks.VERTICAL_OAK_SLAB);
			content.addAfter(Items.SPRUCE_SLAB, ModBlocks.VERTICAL_SPRUCE_SLAB);
			content.addAfter(Items.BIRCH_SLAB, ModBlocks.VERTICAL_BIRCH_SLAB);
			content.addAfter(Items.JUNGLE_SLAB, ModBlocks.VERTICAL_JUNGLE_SLAB);
			content.addAfter(Items.ACACIA_SLAB, ModBlocks.VERTICAL_ACACIA_SLAB);
			content.addAfter(Items.DARK_OAK_SLAB, ModBlocks.VERTICAL_DARK_OAK_SLAB);
			content.addAfter(Items.MANGROVE_SLAB, ModBlocks.VERTICAL_MANGROVE_SLAB);
			content.addAfter(Items.CHERRY_SLAB, ModBlocks.VERTICAL_CHERRY_SLAB);
			content.addAfter(Items.BAMBOO_SLAB, ModBlocks.VERTICAL_BAMBOO_SLAB);
			content.addAfter(Items.BAMBOO_MOSAIC_SLAB, ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB);
			content.addAfter(Items.CRIMSON_SLAB, ModBlocks.VERTICAL_CRIMSON_SLAB);
			content.addAfter(Items.WARPED_SLAB, ModBlocks.VERTICAL_WARPED_SLAB);
			content.addAfter(Items.STONE_SLAB, ModBlocks.VERTICAL_STONE_SLAB);
			content.addAfter(Items.COBBLESTONE_SLAB, ModBlocks.VERTICAL_COBBLESTONE_SLAB);
			content.addAfter(Items.MOSSY_COBBLESTONE_SLAB, ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB);
			content.addAfter(Items.SMOOTH_STONE_SLAB, ModBlocks.VERTICAL_SMOOTH_STONE_SLAB);
			content.addAfter(Items.STONE_BRICK_SLAB, ModBlocks.VERTICAL_STONE_BRICK_SLAB);
			content.addAfter(Items.MOSSY_STONE_BRICK_SLAB, ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB);
			content.addAfter(Items.GRANITE_SLAB, ModBlocks.VERTICAL_GRANITE_SLAB);
			content.addAfter(Items.POLISHED_GRANITE_SLAB, ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB);
			content.addAfter(Items.DIORITE_SLAB, ModBlocks.VERTICAL_DIORITE_SLAB);
			content.addAfter(Items.POLISHED_DIORITE_SLAB, ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB);
			content.addAfter(Items.ANDESITE_SLAB, ModBlocks.VERTICAL_ANDESITE_SLAB);
			content.addAfter(Items.POLISHED_ANDESITE_SLAB, ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB);
			content.addAfter(Items.COBBLED_DEEPSLATE_SLAB, ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB);
			content.addAfter(Items.POLISHED_DEEPSLATE_SLAB, ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB);
			content.addAfter(Items.DEEPSLATE_BRICK_SLAB, ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB);
			content.addAfter(Items.DEEPSLATE_TILE_SLAB, ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB);
			content.addAfter(Items.BRICK_SLAB, ModBlocks.VERTICAL_BRICK_SLAB);
			content.addAfter(Items.MUD_BRICK_SLAB, ModBlocks.VERTICAL_MUD_BRICK_SLAB);
			content.addAfter(Items.SANDSTONE_SLAB, ModBlocks.VERTICAL_SANDSTONE_SLAB);
			content.addAfter(Items.SMOOTH_SANDSTONE_SLAB, ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB);
			content.addAfter(Items.CUT_SANDSTONE_SLAB, ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB);
			content.addAfter(Items.RED_SANDSTONE_SLAB, ModBlocks.VERTICAL_RED_SANDSTONE_SLAB);
			content.addAfter(Items.SMOOTH_RED_SANDSTONE_SLAB, ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB);
			content.addAfter(Items.CUT_RED_SANDSTONE_SLAB, ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB);
			content.addAfter(Items.PRISMARINE_SLAB, ModBlocks.VERTICAL_PRISMARINE_SLAB);
			content.addAfter(Items.PRISMARINE_BRICK_SLAB, ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB);
			content.addAfter(Items.DARK_PRISMARINE_SLAB, ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB);
			content.addAfter(Items.NETHER_BRICK_SLAB, ModBlocks.VERTICAL_NETHER_BRICK_SLAB);
			content.addAfter(Items.RED_NETHER_BRICK_SLAB, ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB);
			content.addAfter(Items.BLACKSTONE_SLAB, ModBlocks.VERTICAL_BLACKSTONE_SLAB);
			content.addAfter(Items.POLISHED_BLACKSTONE_SLAB, ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB);
			content.addAfter(Items.POLISHED_BLACKSTONE_BRICK_SLAB, ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB);
			content.addAfter(Items.END_STONE_BRICK_SLAB, ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB);
			content.addAfter(Items.PURPUR_SLAB, ModBlocks.VERTICAL_PURPUR_SLAB);
			content.addAfter(Items.QUARTZ_SLAB, ModBlocks.VERTICAL_QUARTZ_SLAB);
			content.addAfter(Items.SMOOTH_QUARTZ_SLAB, ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB);
			content.addAfter(Items.CUT_COPPER_SLAB, ModBlocks.VERTICAL_CUT_COPPER_SLAB);
			content.addAfter(Items.EXPOSED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB);
			content.addAfter(Items.WEATHERED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB);
			content.addAfter(Items.OXIDIZED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
			content.addAfter(Items.WAXED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB);
			content.addAfter(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
			content.addAfter(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
			content.addAfter(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
		});

	}
}