package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTab;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTabs;
import games.enchanted.verticalslabs.platform.NeoForgeCreativeTabRegistration;
import games.enchanted.verticalslabs.registry.FlammableBlocks;
import games.enchanted.verticalslabs.registry.WeatheringBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(EnchantedVerticalSlabsConstants.MOD_ID)
public class NeoForgeEntrypoint {
    public final IEventBus eventBus;
    public static ModContainer CONTAINER = null;

    public NeoForgeEntrypoint(IEventBus bus, ModContainer container) {
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
                ModCreativeTabs.buildTabs(NeoForgeEntrypoint::tabBuilder);
            }
        });

        CONTAINER = container;

        bus.addListener(NeoForgeCreativeTabRegistration::addItemsInExistingTabs);
    }

    private static ModCreativeTab.FinalisedTab tabBuilder(ModCreativeTab modCreativeTab) {
        return new ModCreativeTab.FinalisedTab(
            CreativeModeTab.builder()
                .title(modCreativeTab.getTitle())
                .icon(() -> new ItemStack(modCreativeTab.getIcon(), 1))
                .displayItems((params, output) -> {
                    modCreativeTab.forAllItems(output::accept);
                })
                .build(),
            modCreativeTab.getLocation()
        );
    }
}