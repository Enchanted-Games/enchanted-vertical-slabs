package games.enchanted.verticalslabs.item.creative_tab.modifier;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.mixin.accessor.CreativeModeTabsAccessor;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabModifiers {
    public static CreativeTabModifier TEST_MODIFIER = new CreativeTabModifier(
        CreativeModeTabsAccessor.evs$getBuildingBlocksTab(),
        new ArrayList<>(List.of(
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_OAK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.OAK_SLAB.getDefaultInstance())
        ))
    );
}
