package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.FuelItems;
import games.enchanted.verticalslabs.platform.FabricCreativeTabRegistration;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
import net.fabricmc.api.ModInitializer;

public class FabricModEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        EnchantedVerticalSlabsMod.initBeforeRegistration();

        ModBlocks.register();
        WeatheringBlocks.registerWeatheringBlocks();
        FlammableBlocks.registerFlammableBlocks();

        FuelItems.registerFuelItems();

        FabricCreativeTabRegistration.registerTabs();
    }
}
