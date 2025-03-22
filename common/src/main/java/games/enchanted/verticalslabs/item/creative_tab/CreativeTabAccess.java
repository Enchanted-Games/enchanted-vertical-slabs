package games.enchanted.verticalslabs.item.creative_tab;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface CreativeTabAccess {
    boolean evs$containsItem(Item item);
    boolean evs$containsItem(ItemStack stack);
}
