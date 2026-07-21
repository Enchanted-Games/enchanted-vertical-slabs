package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.registry.VerticalSlabBlocks;
import games.enchanted.verticalslabs.item.FuelItems;
import games.enchanted.verticalslabs.platform.FabricCreativeTabRegistration;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
import net.fabricmc.api.ModInitializer;

public class FabricModEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        EnchantedVerticalSlabsMod.initBeforeRegistration();

        VerticalSlabBlocks.register();
        WeatheringBlocks.registerWeatheringBlocks();
        FlammableBlocks.registerFlammableBlocks();

        FuelItems.registerFuelItems();

        FabricCreativeTabRegistration.registerTabs();
    }
}
