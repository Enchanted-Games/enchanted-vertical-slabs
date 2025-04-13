package games.enchanted.verticalslabs.item.creative_tab.modifier;

import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTabs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreativeTabModifiers {
    public static final HashMap<ResourceKey<CreativeModeTab>, ArrayList<CreativeTabModifier>> CREATIVE_TAB_TO_MODIFIER_LIST_MAP = new HashMap<>();

    public static CreativeTabModifier ADD_TO_VERTICAL_SLABS_TAB = new CreativeTabModifier(
        ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ModCreativeTabs.VERTICAL_SLABS_TAB_LOCATION),
        new ArrayList<>()
    );
    public static CreativeTabModifier ADD_TO_MODDED_VERTICAL_SLABS_TAB = new CreativeTabModifier(
        ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ModCreativeTabs.MODDED_VERTICAL_SLABS_TAB_LOCATION),
        new ArrayList<>()
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
        addModifier(ADD_TO_VERTICAL_SLABS_TAB);
        addModifier(ADD_TO_MODDED_VERTICAL_SLABS_TAB);
    }
}
