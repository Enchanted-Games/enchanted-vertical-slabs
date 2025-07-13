package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.item.creative_tab.FabricCreativeTabModifierRunner;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTab;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTabs;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifier;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierRunner;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FabricModEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        EnchantedVerticalSlabsMod.initBeforeRegistration();

        ModCreativeTabs.buildTabs(FabricModEntrypoint::tabBuilder);
        registerCreativeTabModifiers();
    }

    private static ModCreativeTab.FinalisedTab tabBuilder(ModCreativeTab modCreativeTab) {
        modCreativeTab.setFinalised();
        return new ModCreativeTab.FinalisedTab(
            FabricItemGroup.builder()
                .title(modCreativeTab.getTitle())
                .icon(() -> new ItemStack(modCreativeTab.getIcon(), 1))
                .displayItems((params, output) -> {
                    modCreativeTab.forAllItems(output::accept);
                })
            .build(),
            modCreativeTab.getLocation()
        );
    }

    public static void registerCreativeTabModifiers() {
        for(ResourceKey<CreativeModeTab> creativeTab : CreativeTabModifiers.CREATIVE_TAB_TO_MODIFIER_LIST_MAP.keySet()) {
            CreativeTabModifierRunner runner = new FabricCreativeTabModifierRunner(creativeTab);
            for(CreativeTabModifier modifier : CreativeTabModifiers.CREATIVE_TAB_TO_MODIFIER_LIST_MAP.get(creativeTab)) {
                modifier.run(runner);
            }
        }
    }
}
