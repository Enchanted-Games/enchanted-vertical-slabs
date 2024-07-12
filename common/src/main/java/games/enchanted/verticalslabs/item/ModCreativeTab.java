package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.VerticalSlabsConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static class DummyCreativeTabData {
        public final Item groupIcon;
        public final Component groupTitle;
        public final ModCreativeTabEntry[] groupEntries;
        public final String registryName;

        /**
         * A class to hold data required to register a CreativeModeTab without directly creating a CreativeModeTab
         *
         * @param groupIcon    the creative tab icon
         * @param registryName the creative tab registry name
         * @param groupEntries   the creative tab group items
         */
        public DummyCreativeTabData(Item groupIcon, String registryName, ModCreativeTabEntry[] groupEntries){
            this.groupIcon = groupIcon;
            this.groupTitle = Component.translatable("itemGroup." + VerticalSlabsConstants.MOD_ID + "." + registryName);
            this.groupEntries = groupEntries;
            this.registryName = registryName;
        }
    }

    public static final int INSERT_LAST = 0;
    public static final int INSERT_AFTER_RELATED = 2;
    public static final int INSERT_BEFORE_RELATED = 3;

    public static class ModCreativeTabEntry {
        public final Item item;
        public final Item relatedItem;
        public final ResourceKey<CreativeModeTab> additionalTab;
        public final int insertionPosition;

        /**
         * A class to hold data for an item to be added to a ModCreativeTab
         *
         * @param item              the item to be added to a creative tab
         * @param additionalTab     another creative tab to add the item to
         * @param relatedItem       an item to insert this one before or after
         * @param insertionPosition the insertion position, see ModCreativeTab.InsertionPosition
         */
        public ModCreativeTabEntry(Item item, ResourceKey<CreativeModeTab> additionalTab, Item relatedItem, int insertionPosition) {
            this.item = item;
            this.additionalTab = additionalTab;
            this.relatedItem = relatedItem;
            this.insertionPosition = insertionPosition;
            new ItemStack(item);
        }

        /**
         * A class to hold data for an item to be added to a ModCreativeTab
         *
         * @param item              the item to be added to a creative tab
         */
        public ModCreativeTabEntry(Item item){
            this(item, null, null, -1);
        }

        public boolean canBeAddedToOtherGroups() {
            return !(additionalTab == null || relatedItem == null || insertionPosition == -1);
        }
    }
}