package games.enchanted.verticalslabs.item.creative_tab.modifier;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class CreativeTabModifierEntry {
    protected final ItemStack itemToAdd;
    @Nullable protected final ItemStack existingItem;
    protected final CreativeTabInsertionPosition insertionPosition;

    public CreativeTabModifierEntry(ItemLike itemToAdd, CreativeTabInsertionPosition insertionPosition, @Nullable ItemStack existingItem) {
        if(existingItem == null && insertionPosition != CreativeTabInsertionPosition.LAST) {
            throw new IllegalArgumentException("Cannot create Creative Tab Modifier Entry with insertion position " + insertionPosition.name() + " and no existing item");
        }
        this.itemToAdd = itemToAdd.asItem().getDefaultInstance();
        this.insertionPosition = insertionPosition;
        this.existingItem = existingItem;
    }

    public ItemStack getItemToAdd() {
        return itemToAdd;
    }

    public @Nullable ItemStack getExistingItem() {
        return existingItem;
    }

    public CreativeTabInsertionPosition getInsertionPosition() {
        return insertionPosition;
    }
}
