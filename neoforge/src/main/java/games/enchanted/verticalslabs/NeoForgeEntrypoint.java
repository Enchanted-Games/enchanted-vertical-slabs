package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTab;
import games.enchanted.verticalslabs.item.creative_tab.ModCreativeTabs;
import games.enchanted.verticalslabs.item.creative_tab.NeoForgeCreativeTabModifierRunner;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifier;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierRunner;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
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
            if(event.getRegistry().key().equals(Registries.CREATIVE_MODE_TAB)) {
                ModCreativeTabs.buildTabs(NeoForgeEntrypoint::tabBuilder);
            }
        });

        CONTAINER = container;

        bus.addListener(NeoForgeEntrypoint::registerCreativeTabModifiers);
    }

    private static ModCreativeTab.FinalisedTab tabBuilder(ModCreativeTab modCreativeTab) {
        modCreativeTab.setFinalised();
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

    private static void registerCreativeTabModifiers(BuildCreativeModeTabContentsEvent creativeTabEvent) {
        if(CreativeTabModifiers.CREATIVE_TAB_TO_MODIFIER_LIST_MAP.get(creativeTabEvent.getTabKey()) == null) return;

        CreativeTabModifierRunner runner = new NeoForgeCreativeTabModifierRunner(creativeTabEvent);
        for(CreativeTabModifier modifier : CreativeTabModifiers.CREATIVE_TAB_TO_MODIFIER_LIST_MAP.get(creativeTabEvent.getTabKey())) {
            modifier.run(runner);
        }
    }
}