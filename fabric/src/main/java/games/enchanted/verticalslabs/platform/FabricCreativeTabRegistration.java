package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.item.ModCreativeTab;
import games.enchanted.verticalslabs.item.ModCreativeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FabricCreativeTabRegistration {
    /**
     * Add items to existing creative tabs
     */
    public static void addItemsInExistingTabs() {
        for (int i = 0; i < ModCreativeTabs.modCreativeTabs.length; i++) {
        for (int j = 0; j < ModCreativeTabs.modCreativeTabs[i].groupEntries.length; j++) {
            ModCreativeTab.ModCreativeTabEntry entry = ModCreativeTabs.modCreativeTabs[i].groupEntries[j];

            ItemGroupEvents.modifyEntriesEvent(entry.additionalTab).register(event -> {
                try {
                    switch (entry.insertionPosition) {
                        case ModCreativeTab.INSERT_LAST:
                            event.accept(entry.item.getDefaultInstance());
                            break;
                        case ModCreativeTab.INSERT_AFTER_RELATED:
                            event.addAfter(entry.relatedItem.getDefaultInstance(), entry.item.getDefaultInstance());
                            break;
                        case ModCreativeTab.INSERT_BEFORE_RELATED:
                            event.addBefore(entry.relatedItem.getDefaultInstance(), entry.item.getDefaultInstance());
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: '" + entry.insertionPosition + "' for creative mode tab insertionPosition");
                    }
                } catch(IllegalStateException e) {
                    EnchantedVerticalSlabsLogging.warn("An exception occured running BuildCreativeModeTabContentsEvent:\n", e);
                }
            });
        }}
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
                    for (int i2 = 0; i2 < ModCreativeTabs.modCreativeTabs[finalI].groupEntries.length; i2++) {
                        output.accept(ModCreativeTabs.modCreativeTabs[finalI].groupEntries[i2].item);
                    }
                })
                .build();

            EnchantedVerticalSlabsMod.register(
                BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                () -> TAB,
                ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, ModCreativeTabs.modCreativeTabs[finalI].registryName)
            );
        }

        addItemsInExistingTabs();
    }
}
