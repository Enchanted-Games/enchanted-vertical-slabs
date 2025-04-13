package games.enchanted.verticalslabs.item.creative_tab;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

public class ModCreativeTabs {
    public static final ResourceLocation VERTICAL_SLABS_TAB_LOCATION = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "vertical_slabs");
    public static final ResourceLocation MODDED_VERTICAL_SLABS_TAB_LOCATION = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "modded_vertical_slabs");

    public static ModCreativeTab VERTICAL_SLABS_TAB = new ModCreativeTab(
        () -> RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "vertical_oak_slab")),
        VERTICAL_SLABS_TAB_LOCATION,
        new ArrayList<>()
    );

    public static ModCreativeTab MODDED_VERTICAL_SLABS_TAB = new ModCreativeTab(
        () -> RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "vertical_purpur_slab")),
        MODDED_VERTICAL_SLABS_TAB_LOCATION,
        new ArrayList<>()
    );

    public static void buildTabs(Function<ModCreativeTab, ModCreativeTab.FinalisedTab> tabBuilder) {
        Stream<ModCreativeTab.FinalisedTab> finalisedTabs = Stream.of(
            VERTICAL_SLABS_TAB, MODDED_VERTICAL_SLABS_TAB
        ).map(tabBuilder);
        finalisedTabs.forEach(creativeModeTab -> {
            EnchantedVerticalSlabsMod.register(BuiltInRegistries.CREATIVE_MODE_TAB.key(), creativeModeTab::creativeTab, creativeModeTab.location());
        });
    }
}
