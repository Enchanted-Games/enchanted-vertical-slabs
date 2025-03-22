package games.enchanted.verticalslabs.mixin.item;

import games.enchanted.verticalslabs.item.creative_tab.CreativeTabAccess;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin implements CreativeTabAccess {
    @Shadow private Set<ItemStack> displayItemsSearchTab;

    @Override
    public boolean evs$containsItem(Item item) {
        return this.displayItemsSearchTab.stream().map(ItemStack::getItem).toList().contains(item);
    }

    @Override
    public boolean evs$containsItem(ItemStack stack) {
        return this.evs$containsItem(stack.getItem());
    }
}
