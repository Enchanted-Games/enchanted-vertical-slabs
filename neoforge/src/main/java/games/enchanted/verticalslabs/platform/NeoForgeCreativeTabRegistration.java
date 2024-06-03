package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.CommonEntrypoint;
import games.enchanted.verticalslabs.VerticalSlabsConstants;
import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.ModCreativeTabs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class NeoForgeCreativeTabRegistration {
    /**
     * Add vertical slabs to BUILDING_BLOCKS tab
     *
     * @param event BuildCreativeModeTabContentsEvent
     */
    public static void addItemsInExistingTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.VERTICAL_OAK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_SPRUCE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_BIRCH_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_JUNGLE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_ACACIA_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_DARK_OAK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_MANGROVE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_CHERRY_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_BAMBOO_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_CRIMSON_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_WARPED_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_STONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_COBBLESTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_STONE_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_GRANITE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_DIORITE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_ANDESITE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_MUD_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_SANDSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_PRISMARINE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_NETHER_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_BLACKSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_PURPUR_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_QUARTZ_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.asBlockItem());
            event.accept(ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.asBlockItem());
        }
    }

    public static void registerTabs() {
        for (int i = 0; i < ModCreativeTabs.modCreativeTabs.length; i++) {
            int finalI = i;
            final CreativeModeTab TAB = CreativeModeTab.builder()
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
                new ResourceLocation(VerticalSlabsConstants.MOD_ID, ModCreativeTabs.modCreativeTabs[finalI].registryName)
            );
        }
    }
}
