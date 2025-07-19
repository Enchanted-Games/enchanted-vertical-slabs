package games.enchanted.verticalslabs.dynamic;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import games.enchanted.verticalslabs.block.WeatheringCopperUtil;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.config.dynamic.DynamicResourcesSettingsFile;
import games.enchanted.verticalslabs.config.dynamic.KnownSlabsFile;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabInsertionPosition;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifier;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierEntry;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifiers;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import games.enchanted.verticalslabs.util.ListUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicVerticalSlabsManager {
    public static final Codec<List<ResourceLocation>> DYNAMIC_SLABS_LIST_CODEC = Codec.list(ResourceLocation.CODEC);
    private static boolean newSlabsSinceLastLaunch;

    private static final ArrayList<ResourceLocation> VANILLA_SLABS = new ArrayList<>();
    public static final ArrayList<DynamicSlab> DYNAMIC_SLAB_BLOCKS = new ArrayList<>();
    public static BiMap<DynamicVerticalSlabBlock, Block> VERTICAL_TO_NORMAL_SLAB_MAP = HashBiMap.create();
    public static Map<Block, DynamicVerticalSlabBlock> NORMAL_TO_VERTICAL_SLAB_MAP = new HashMap<>();

    public static void addDynamicSlab(ResourceLocation regularSlabLocation) {
        DYNAMIC_SLAB_BLOCKS.add(new DynamicSlab(regularSlabLocation));
    }

    public static void addDynamicSlabForVanilla(ResourceLocation regularSlabLocation) {
        VANILLA_SLABS.add(regularSlabLocation);
    }

    public static void registerDynamicSlabs() {
        DynamicResourcesSettingsFile.INSTANCE.readOrCreateSettingsFile();
        KnownSlabsFile.INSTANCE.readOrCreateSettingsFile();

        for (ResourceLocation location : VANILLA_SLABS) {
            DYNAMIC_SLAB_BLOCKS.add(new DynamicSlab(location));
        }
        for (DynamicSlab slab : DYNAMIC_SLAB_BLOCKS) {
            Block regularSlabBlock = RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation());
            WeatheringCopper.WeatherState weatherState = null;
            if(regularSlabBlock instanceof WeatheringCopper weatheringCopperSlabBlock) {
                weatherState = weatheringCopperSlabBlock.getAge();
            }
            BlockAndItemContainer registeredBlock = RegistryHelpers.registerDynamicVerticalSlab(slab.getVerticalSlabLocation(), BlockBehaviour.Properties.ofFullCopy(regularSlabBlock), slab, weatherState);
            VERTICAL_TO_NORMAL_SLAB_MAP.put((DynamicVerticalSlabBlock) registeredBlock.block(), regularSlabBlock);

            boolean slabIsVanilla = slab.getOriginalSlabLocation().getNamespace().equals("minecraft");
            if(!(slabIsVanilla && slab.getOriginalSlabLocation().getPath().equals("petrified_oak_slab"))) {
                (slabIsVanilla ? CreativeTabModifiers.ADD_TO_VERTICAL_SLABS_TAB : CreativeTabModifiers.ADD_TO_MODDED_VERTICAL_SLABS_TAB).addModifierEntry(
                    new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.LAST, null)
                );
            }

//            Item regularSlabItem = regularSlabBlock.asItem();
//            if(regularSlabItem == Items.AIR) regularSlabItem = RegistryHelpers.getItemFromLocation(slab.getOriginalSlabLocation());
//            if(regularSlabItem != Items.AIR) {
//                addSlabToCreativeInventory(new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.AFTER, regularSlabItem.getDefaultInstance()));
//            } else {
//                addSlabToCreativeInventory(new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.LAST, null));
//            }
        }
        NORMAL_TO_VERTICAL_SLAB_MAP = VERTICAL_TO_NORMAL_SLAB_MAP.inverse();

        WeatheringCopperUtil.addDynamicCopperPairs(DYNAMIC_SLAB_BLOCKS);

        setNewSlabsDetectedOnStartup();
    }

    private static void addSlabToCreativeInventory(CreativeTabModifierEntry modifierEntry) {
        // TODO: reimplement this to run after other mods
        List<CreativeModeTab> tabs = CreativeModeTabs.allTabs();
        tabs.forEach((creativeModeTab -> CreativeTabModifiers.addModifier(
            new CreativeTabModifier(
                ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), RegistryHelpers.getLocationFromCreativeTab(creativeModeTab)),
                new ArrayList<>(List.of(modifierEntry))
            )
        )));
    }

    private static void setNewSlabsDetectedOnStartup() {
        newSlabsSinceLastLaunch = !ListUtil.listsEqualOrderIndependent(KnownSlabsFile.INSTANCE.getKnownSlabs(), getDynamicVerticalSlabLocations());
        KnownSlabsFile.INSTANCE.setKnownSlabs(getDynamicVerticalSlabLocations());
        KnownSlabsFile.INSTANCE.writeSettingsFile();
    }

    public static List<ResourceLocation> getDynamicVerticalSlabLocations() {
        return DYNAMIC_SLAB_BLOCKS.stream().map(DynamicSlab::getOriginalSlabLocation).toList();
    }

    public static boolean resourcesNeedRegenerating() {
        return newSlabsSinceLastLaunch || DynamicResourcesSettingsFile.INSTANCE.getForceRegenerate();
    }
}
