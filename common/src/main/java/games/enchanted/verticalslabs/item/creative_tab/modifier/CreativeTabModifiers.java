package games.enchanted.verticalslabs.item.creative_tab.modifier;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.mixin.accessor.CreativeModeTabsAccessor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreativeTabModifiers {
    public static final HashMap<ResourceKey<CreativeModeTab>, ArrayList<CreativeTabModifier>> CREATIVE_TAB_TO_MODIFIER_LIST_MAP = new HashMap<>();

    public static CreativeTabModifier TEST_MODIFIER = new CreativeTabModifier(
        CreativeModeTabsAccessor.evs$getBuildingBlocksTab(),
        new ArrayList<>(List.of(
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_OAK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.OAK_SLAB.getDefaultInstance())
        ))
    );

    public static void addModifier(CreativeTabModifier modifier) {
        ResourceKey<CreativeModeTab> creativeTab = modifier.getCreativeTab();
        @Nullable ArrayList<CreativeTabModifier> tabModifiers = CREATIVE_TAB_TO_MODIFIER_LIST_MAP.get(creativeTab);
        if(tabModifiers == null) {
            CREATIVE_TAB_TO_MODIFIER_LIST_MAP.put(creativeTab, new ArrayList<>(List.of(modifier)));
        } else {
            tabModifiers.add(modifier);
        }
    }

    static {
        addModifier(TEST_MODIFIER);
    }
}
