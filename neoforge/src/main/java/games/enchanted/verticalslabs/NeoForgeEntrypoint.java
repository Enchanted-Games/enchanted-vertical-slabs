package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.platform.NeoForgeCreativeTabRegistration;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(EnchantedVerticalSlabsConstants.LOADER_MOD_ID)
public class NeoForgeEntrypoint {
    public final IEventBus eventBus;

    public NeoForgeEntrypoint(IEventBus bus) {
        this.eventBus = bus;
        EnchantedVerticalSlabsMod.initBeforeRegistration();

        // register stuff
        bus.addListener((RegisterEvent event) -> {
            if(event.getRegistry().key().equals(Registries.BLOCK)) {
                ModBlocks.register();
                WeatheringBlocks.registerWeatheringBlocks();
                FlammableBlocks.registerFlammableBlocks();
            }
            if(event.getRegistry().key().equals(Registries.CREATIVE_MODE_TAB)) {
                NeoForgeCreativeTabRegistration.registerTabs();
            }
        });

        bus.addListener(NeoForgeCreativeTabRegistration::addItemsInExistingTabs);
    }
}