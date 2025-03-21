package games.enchanted.verticalslabs.item.creative_tab.modifier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.ArrayList;

public class CreativeTabModifier {
    protected final ResourceKey<CreativeModeTab> creativeTab;
    protected final ArrayList<CreativeTabModifierEntry> modifierEntries;

    public CreativeTabModifier(ResourceKey<CreativeModeTab> creativeTab, ArrayList<CreativeTabModifierEntry> modifierEntries) {
        this.creativeTab = creativeTab;
        this.modifierEntries = modifierEntries;
    }

    public void addModifierEntry(CreativeTabModifierEntry modifierEntry) {
        modifierEntries.add(modifierEntry);
    }

    public ResourceKey<CreativeModeTab> getCreativeTab() {
        return creativeTab;
    }

    public void run(CreativeTabModifierRunner runner) {
        modifierEntries.forEach(modifierEntry -> runner.runModifierEntry(modifierEntry, creativeTab));
    }
}
