package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabInsertionPosition;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifier;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierEntry;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifiers;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicVerticalSlabs {
    public static final ArrayList<DynamicSlab> DYNAMIC_SLAB_BLOCKS = new ArrayList<>();
    public static Map<Block, Block> VERTICAL_TO_NORMAL_SLAB_MAP = new HashMap<>();

    public static void addDynamicSlab(ResourceLocation regularSlabLocation) {
        DYNAMIC_SLAB_BLOCKS.add(new DynamicSlab(regularSlabLocation));
    }

    public static void registerDynamicSlabs() {
        for (DynamicSlab slab : DYNAMIC_SLAB_BLOCKS) {
            Block regularSlabBlock = RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation());
            BlockAndItemContainer registeredBlock = RegistryHelpers.registerDynamicVerticalSlab(slab.getVerticalSlabLocation(), regularSlabBlock.properties(), slab);
            VERTICAL_TO_NORMAL_SLAB_MAP.put(registeredBlock.block(), regularSlabBlock);

            CreativeTabModifiers.ADD_TO_MODDED_VERTICAL_SLABS_TAB.addModifierEntry(
                new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.LAST, null)
            );

            Item regularSlabItem = regularSlabBlock.asItem();
            if(regularSlabItem == Items.AIR) regularSlabItem = RegistryHelpers.getItemFromLocation(slab.getOriginalSlabLocation());
            if(regularSlabItem != Items.AIR) {
                addSlabToCreativeInventory(new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.AFTER, regularSlabItem.getDefaultInstance()));
            } else {
                addSlabToCreativeInventory(new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.LAST, null));
            }
        }
        Services.PLATFORM.buildCreativeTabs();
    }

    private static void addSlabToCreativeInventory(CreativeTabModifierEntry modifierEntry) {
        List<CreativeModeTab> tabs = CreativeModeTabs.allTabs();
        tabs.forEach((creativeModeTab -> CreativeTabModifiers.addModifier(
            new CreativeTabModifier(
                ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), RegistryHelpers.getLocationFromCreativeTab(creativeModeTab)),
                new ArrayList<>(List.of(modifierEntry))
            )
        )));
    }
}
