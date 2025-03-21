package games.enchanted.verticalslabs.item.creative_tab;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ModCreativeTab {
    protected final ItemLike icon;
    protected final ResourceLocation location;
    protected final ArrayList<ItemLike> items;
    protected final Component title;

    public ModCreativeTab(ItemLike icon, ResourceLocation location, ArrayList<ItemLike> items) {
        this(
            icon,
            location,
            items,
            Component.translatable("itemGroup." + location.getNamespace() + "." + location.getPath().replaceAll("/", "."))
        );
    }
    public ModCreativeTab(ItemLike icon, ResourceLocation location, ArrayList<ItemLike> items, Component title) {
        this.icon = icon;
        this.location = location;
        this.items = items;
        this.title = title;
    }

    public void addItem(ItemLike item) {
        items.add(item);
    }

    public void forAllItems(Consumer<ItemLike> itemConsumer) {
        items.forEach(itemConsumer);
    }

    public Component getTitle() {
        return title;
    }

    public ArrayList<ItemLike> getItems() {
        return items;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public ItemLike getIcon() {
        return icon;
    }

    public record FinalisedTab (CreativeModeTab creativeTab, ResourceLocation location) {}
}
