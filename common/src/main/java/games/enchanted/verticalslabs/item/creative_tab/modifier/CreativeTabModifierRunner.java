package games.enchanted.verticalslabs.item.creative_tab.modifier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public interface CreativeTabModifierRunner {
    void runModifierEntry(CreativeTabModifierEntry modifierEntry, ResourceKey<CreativeModeTab> creativeTab);
}
