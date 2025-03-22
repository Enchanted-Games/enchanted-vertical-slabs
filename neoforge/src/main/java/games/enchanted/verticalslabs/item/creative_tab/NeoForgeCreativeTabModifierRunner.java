package games.enchanted.verticalslabs.item.creative_tab;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierEntry;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierRunner;
import games.enchanted.verticalslabs.platform.Services;
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
        try {
            if(modifierEntry.getInsertionPosition().requiresExistingItem && modifierEntry.getExistingItem() == null) {
                return;
            }
            switch (modifierEntry.getInsertionPosition()) {
                case FIRST -> creativeTabEvent.insertFirst(modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                case LAST -> creativeTabEvent.accept(modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                case AFTER -> creativeTabEvent.insertAfter(modifierEntry.getExistingItem(), modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                case BEFORE -> creativeTabEvent.insertBefore(modifierEntry.getExistingItem(), modifierEntry.getItemToAdd(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                default -> throw new NotImplementedException("Insertion position " + modifierEntry.getInsertionPosition() + " not implemented");
            }
        } catch (IllegalArgumentException e) {
            if(Services.PLATFORM.isDevelopmentEnvironment()) {
                EnchantedVerticalSlabsLogging.warn("An exception occured while trying to add item [{}] to Creative Tab [{}]\n{}", modifierEntry.getItemToAdd(), creativeTab.location(), e.getMessage());
            }
        }
    }
}
