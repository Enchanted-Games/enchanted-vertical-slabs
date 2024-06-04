package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.VerticalSlabsConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class ModCreativeTab {

    public static class DummyCreativeTabData {
        public final Item groupIcon;
        public final Component groupTitle;
        public final Item[] groupItems;
        public final String registryName;

        /**
         * A class to hold data required to register a CreativeModeTab without directly creating a CreativeModeTab
         *
         * @param groupIcon    the creative tab icon
         * @param registryName the creative tab registry name
         * @param groupItems   the creative tab group items
         */
        public DummyCreativeTabData(Item groupIcon, String registryName, Item[] groupItems){
            this.groupIcon = groupIcon;
            this.groupTitle = Component.translatable("itemGroup." + VerticalSlabsConstants.MOD_ID + "." + registryName);
            this.groupItems = groupItems;
            this.registryName = registryName;
        }
    }
}
