package games.enchanted.verticalslabs.item.creative_tab;

import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierEntry;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierRunner;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.commons.lang3.NotImplementedException;

public class FabricCreativeTabModifierRunner implements CreativeTabModifierRunner {
    final ResourceKey<CreativeModeTab> tabToRunFor;

    public FabricCreativeTabModifierRunner(ResourceKey<CreativeModeTab> tabToRunFor) {
        this.tabToRunFor = tabToRunFor;
    }

    @Override
    public void runModifierEntry(CreativeTabModifierEntry modifierEntry, ResourceKey<CreativeModeTab> creativeTab) {
        ItemGroupEvents.modifyEntriesEvent(tabToRunFor).register(event -> {
            switch (modifierEntry.getInsertionPosition()) {
                case FIRST -> event.prepend(modifierEntry.getItemToAdd());
                case LAST -> event.accept(modifierEntry.getItemToAdd());
                case AFTER -> event.addAfter(modifierEntry.getExistingItem(), modifierEntry.getItemToAdd());
                case BEFORE -> event.addBefore(modifierEntry.getExistingItem(), modifierEntry.getItemToAdd());
                default -> throw new NotImplementedException("Insertion position " + modifierEntry.getInsertionPosition() + " not implemented");
            }
        });
    }
}
