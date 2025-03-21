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

    public static CreativeTabModifier VANILLA_SLABS_MODIFIER = new CreativeTabModifier(
        CreativeModeTabsAccessor.evs$getBuildingBlocksTab(),
        new ArrayList<>(List.of(
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_OAK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.OAK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_SPRUCE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.SPRUCE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_BIRCH_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.BIRCH_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_JUNGLE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.JUNGLE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_ACACIA_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.ACACIA_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_DARK_OAK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.DARK_OAK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_MANGROVE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.MANGROVE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_CHERRY_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.CHERRY_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_PALE_OAK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.PALE_OAK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_BAMBOO_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.BAMBOO_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.BAMBOO_MOSAIC_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_CRIMSON_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.CRIMSON_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_WARPED_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.WARPED_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_STONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.STONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_COBBLESTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.COBBLESTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.MOSSY_COBBLESTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_SMOOTH_STONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.SMOOTH_STONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_STONE_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.STONE_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.MOSSY_STONE_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_GRANITE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.GRANITE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_GRANITE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_GRANITE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_DIORITE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.DIORITE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_DIORITE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_DIORITE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_ANDESITE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.ANDESITE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_ANDESITE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_ANDESITE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.COBBLED_DEEPSLATE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_DEEPSLATE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.DEEPSLATE_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_DEEPSLATE_TILE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.DEEPSLATE_TILE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_TUFF_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.TUFF_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_TUFF_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_TUFF_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_TUFF_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.TUFF_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_MUD_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.MUD_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_RESIN_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.RESIN_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_SANDSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.SANDSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.SMOOTH_SANDSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_CUT_SANDSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.CUT_STANDSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_RED_SANDSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.RED_SANDSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.SMOOTH_RED_SANDSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_PRISMARINE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.PRISMARINE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_PRISMARINE_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.PRISMARINE_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_DARK_PRISMARINE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.DARK_PRISMARINE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_NETHER_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.NETHER_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_RED_NETHER_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.RED_NETHER_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_BLACKSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.BLACKSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_BLACKSTONE_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.POLISHED_BLACKSTONE_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_ENDSTONE_BRICK_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.END_STONE_BRICK_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_PURPUR_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.PURPUR_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_QUARTZ_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.QUARTZ_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.SMOOTH_QUARTZ_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.EXPOSED_CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.WEATHERED_CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.OXIDIZED_CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.WAXED_CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.WAXED_EXPOSED_CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.WAXED_WEATHERED_CUT_COPPER_SLAB.getDefaultInstance()),
            new CreativeTabModifierEntry(ModBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB.blockItem(), CreativeTabInsertionPosition.AFTER, Items.WAXED_OXIDIZED_CUT_COPPER_SLAB.getDefaultInstance())
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
        addModifier(VANILLA_SLABS_MODIFIER);
    }
}
