package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.CommonEntrypoint;
import games.enchanted.verticalslabs.VerticalSlabsConstants;
import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.ModCreativeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FabricCreativeTabRegistration {
    /**
     * Add vertical slabs to BUILDING_BLOCKS tab after their regular slab counterparts
     */
    public static void addItemsInExistingTabs() {
        // Adding items before/after others is fabric only for now as NeoForge doesn't seem to have a built-in way to do it.
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.OAK_SLAB, ModBlocks.VERTICAL_OAK_SLAB.getBlockItem());
            content.addAfter(Items.SPRUCE_SLAB, ModBlocks.VERTICAL_SPRUCE_SLAB.getBlockItem());
            content.addAfter(Items.BIRCH_SLAB, ModBlocks.VERTICAL_BIRCH_SLAB.getBlockItem());
            content.addAfter(Items.JUNGLE_SLAB, ModBlocks.VERTICAL_JUNGLE_SLAB.getBlockItem());
            content.addAfter(Items.ACACIA_SLAB, ModBlocks.VERTICAL_ACACIA_SLAB.getBlockItem());
            content.addAfter(Items.DARK_OAK_SLAB, ModBlocks.VERTICAL_DARK_OAK_SLAB.getBlockItem());
            content.addAfter(Items.MANGROVE_SLAB, ModBlocks.VERTICAL_MANGROVE_SLAB.getBlockItem());
            content.addAfter(Items.CHERRY_SLAB, ModBlocks.VERTICAL_CHERRY_SLAB.getBlockItem());
            content.addAfter(Items.BAMBOO_SLAB, ModBlocks.VERTICAL_BAMBOO_SLAB.getBlockItem());
            content.addAfter(Items.BAMBOO_MOSAIC_SLAB, ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.getBlockItem());
            content.addAfter(Items.CRIMSON_SLAB, ModBlocks.VERTICAL_CRIMSON_SLAB.getBlockItem());
            content.addAfter(Items.WARPED_SLAB, ModBlocks.VERTICAL_WARPED_SLAB.getBlockItem());
            content.addAfter(Items.STONE_SLAB, ModBlocks.VERTICAL_STONE_SLAB.getBlockItem());
            content.addAfter(Items.COBBLESTONE_SLAB, ModBlocks.VERTICAL_COBBLESTONE_SLAB.getBlockItem());
            content.addAfter(Items.MOSSY_COBBLESTONE_SLAB, ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.getBlockItem());
            content.addAfter(Items.SMOOTH_STONE_SLAB, ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.getBlockItem());
            content.addAfter(Items.STONE_BRICK_SLAB, ModBlocks.VERTICAL_STONE_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.MOSSY_STONE_BRICK_SLAB, ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.GRANITE_SLAB, ModBlocks.VERTICAL_GRANITE_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_GRANITE_SLAB, ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.getBlockItem());
            content.addAfter(Items.DIORITE_SLAB, ModBlocks.VERTICAL_DIORITE_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_DIORITE_SLAB, ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.getBlockItem());
            content.addAfter(Items.ANDESITE_SLAB, ModBlocks.VERTICAL_ANDESITE_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_ANDESITE_SLAB, ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.getBlockItem());
            content.addAfter(Items.COBBLED_DEEPSLATE_SLAB, ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_DEEPSLATE_SLAB, ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.getBlockItem());
            content.addAfter(Items.DEEPSLATE_BRICK_SLAB, ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.DEEPSLATE_TILE_SLAB, ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.getBlockItem());
            content.addAfter(Items.BRICK_SLAB, ModBlocks.VERTICAL_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.MUD_BRICK_SLAB, ModBlocks.VERTICAL_MUD_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.SANDSTONE_SLAB, ModBlocks.VERTICAL_SANDSTONE_SLAB.getBlockItem());
            content.addAfter(Items.SMOOTH_SANDSTONE_SLAB, ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.getBlockItem());
            content.addAfter(Items.CUT_STANDSTONE_SLAB, ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.getBlockItem());
            content.addAfter(Items.RED_SANDSTONE_SLAB, ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.getBlockItem());
            content.addAfter(Items.SMOOTH_RED_SANDSTONE_SLAB, ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.getBlockItem());
            content.addAfter(Items.CUT_RED_SANDSTONE_SLAB, ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.getBlockItem());
            content.addAfter(Items.PRISMARINE_SLAB, ModBlocks.VERTICAL_PRISMARINE_SLAB.getBlockItem());
            content.addAfter(Items.PRISMARINE_BRICK_SLAB, ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.DARK_PRISMARINE_SLAB, ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.getBlockItem());
            content.addAfter(Items.NETHER_BRICK_SLAB, ModBlocks.VERTICAL_NETHER_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.RED_NETHER_BRICK_SLAB, ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.BLACKSTONE_SLAB, ModBlocks.VERTICAL_BLACKSTONE_SLAB.getBlockItem());
            content.addAfter(Items.TUFF_SLAB, ModBlocks.VERTICAL_TUFF_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_TUFF_SLAB, ModBlocks.VERTICAL_POLISHED_TUFF_SLAB.getBlockItem());
            content.addAfter(Items.TUFF_BRICK_SLAB, ModBlocks.VERTICAL_TUFF_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_BLACKSTONE_SLAB, ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.getBlockItem());
            content.addAfter(Items.POLISHED_BLACKSTONE_BRICK_SLAB, ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.END_STONE_BRICK_SLAB, ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.getBlockItem());
            content.addAfter(Items.PURPUR_SLAB, ModBlocks.VERTICAL_PURPUR_SLAB.getBlockItem());
            content.addAfter(Items.QUARTZ_SLAB, ModBlocks.VERTICAL_QUARTZ_SLAB.getBlockItem());
            content.addAfter(Items.SMOOTH_QUARTZ_SLAB, ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.getBlockItem());
            content.addAfter(Items.CUT_COPPER_SLAB, ModBlocks.VERTICAL_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.EXPOSED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.WEATHERED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.OXIDIZED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.WAXED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.getBlockItem());
            content.addAfter(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.getBlockItem());
        });
    }

    /**
     * Register creative mode tabs from ModCreativeTabs.modCreativeTabs
     */
    public static void registerTabs() {
        for (int i = 0; i < ModCreativeTabs.modCreativeTabs.length; i++) {
            int finalI = i;
            final CreativeModeTab TAB = FabricItemGroup.builder()
                .title(ModCreativeTabs.modCreativeTabs[finalI].groupTitle)
                .icon(() -> new ItemStack(ModCreativeTabs.modCreativeTabs[finalI].groupIcon))
                .displayItems((params, output) -> {
                    for (int i2 = 0; i2 < ModCreativeTabs.modCreativeTabs[finalI].groupItems.length; i2++) {
                        output.accept(ModCreativeTabs.modCreativeTabs[finalI].groupItems[i2]);
                    }
                })
                .build();

            CommonEntrypoint.platformRegister.register(
                BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                () -> TAB,
                ResourceLocation.fromNamespaceAndPath(VerticalSlabsConstants.MOD_ID, ModCreativeTabs.modCreativeTabs[finalI].registryName)
            );
        }

        addItemsInExistingTabs();
    }
}
