package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.item.ModCreativeTab.ModCreativeTabEntry;
import games.enchanted.verticalslabs.item.ModCreativeTab;
import games.enchanted.verticalslabs.item.ModCreativeTabs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class NeoForgeCreativeTabRegistration {
    /**
     * Add items to existing creative tabs
     *
     * @param event BuildCreativeModeTabContentsEvent
     */
    public static void addItemsInExistingTabs(BuildCreativeModeTabContentsEvent event) {
        for (int i = 0; i < ModCreativeTabs.modCreativeTabs.length; i++) {
        for (int j = 0; j < ModCreativeTabs.modCreativeTabs[i].groupEntries.length; j++) {
            ModCreativeTabEntry entry = ModCreativeTabs.modCreativeTabs[i].groupEntries[j];

            if (!(entry.canBeAddedToOtherGroups() && event.getTabKey() == entry.additionalTab)) {
                continue;
            }

            try {
                switch (entry.insertionPosition) {
                    case ModCreativeTab.INSERT_LAST:
                        event.accept(entry.item.getDefaultInstance());
                        break;
                    case ModCreativeTab.INSERT_AFTER_RELATED:
                        event.insertAfter(entry.relatedItem.getDefaultInstance(), entry.item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        break;
                    case ModCreativeTab.INSERT_BEFORE_RELATED:
                        event.insertBefore(entry.relatedItem.getDefaultInstance(), entry.item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: '" + entry.insertionPosition + "' for creative mode tab insertionPosition");
                }
            } catch(IllegalStateException e) {
                EnchantedVerticalSlabsLogging.warn("An exception occured running BuildCreativeModeTabContentsEvent:\n", e);
            }
        }}
    }

    /**
     * Register creative mode tabs from ModCreativeTabs.modCreativeTabs
     */
    public static void registerTabs() {
        for (int i = 0; i < ModCreativeTabs.modCreativeTabs.length; i++) {
            int finalI = i;
            final CreativeModeTab TAB = CreativeModeTab.builder()
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
    }
}
