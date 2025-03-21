package games.enchanted.verticalslabs.item.creative_tab;

import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierEntry;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierRunner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.commons.lang3.NotImplementedException;

public class NeoForgeCreativeTabModifierRunner implements CreativeTabModifierRunner {
    final BuildCreativeModeTabContentsEvent creativeTabEvent;

    public NeoForgeCreativeTabModifierRunner(BuildCreativeModeTabContentsEvent creativeTabEvent) {
        this.creativeTabEvent = creativeTabEvent;
    }

    @Override
    public void runModifierEntry(CreativeTabModifierEntry modifierEntry, ResourceKey<CreativeModeTab> creativeTab) {
        switch (modifierEntry.getInsertionPosition()) {
            case FIRST -> creativeTabEvent.insertFirst(modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            case LAST -> creativeTabEvent.accept(modifierEntry.getItemToAdd());
            case AFTER -> {
                if (modifierEntry.getExistingItem() != null) {
                    creativeTabEvent.insertAfter(modifierEntry.getExistingItem(), modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
            case BEFORE -> {
                if (modifierEntry.getExistingItem() != null) {
                    creativeTabEvent.insertBefore(modifierEntry.getExistingItem(), modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
            default -> throw new NotImplementedException("Insertion position " + modifierEntry.getInsertionPosition() + " not implemented");
        }
    }
}
