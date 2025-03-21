package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.FuelItems;
import games.enchanted.verticalslabs.item.creative_tab.FabricCreativeTabModifierRunner;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTab;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTabs;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierRunner;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifiers;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.world.item.ItemStack;

public class FabricModEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        EnchantedVerticalSlabsMod.initBeforeRegistration();

        ModBlocks.register();
        WeatheringBlocks.registerWeatheringBlocks();
        FlammableBlocks.registerFlammableBlocks();

        FuelItems.registerFuelItems();

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

    private static void registerCreativeTabModifiers() {
        CreativeTabModifierRunner runner = new FabricCreativeTabModifierRunner(CreativeTabModifiers.TEST_MODIFIER.getCreativeTab());
        CreativeTabModifiers.TEST_MODIFIER.run(runner);
    }
}
