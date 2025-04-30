package games.enchanted.verticalslabs.dynamic;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import games.enchanted.verticalslabs.block.WeatheringCopperUtil;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabInsertionPosition;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifier;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifierEntry;
import games.enchanted.verticalslabs.item.creative_tab.modifier.CreativeTabModifiers;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import games.enchanted.verticalslabs.util.FilesystemUtil;
import games.enchanted.verticalslabs.util.ListUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicVerticalSlabs {
    public static final Codec<List<ResourceLocation>> DYNAMIC_SLABS_LIST_CODEC = Codec.list(ResourceLocation.CODEC);
    private static final String KNOWN_SLABS_FILENAME = "known_slabs.json";
    @Nullable private static Boolean newSlabsSinceLastLaunch = null;

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

            Item regularSlabItem = regularSlabBlock.asItem();
            if(regularSlabItem == Items.AIR) regularSlabItem = RegistryHelpers.getItemFromLocation(slab.getOriginalSlabLocation());
            if(regularSlabItem != Items.AIR) {
                addSlabToCreativeInventory(new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.AFTER, regularSlabItem.getDefaultInstance()));
            } else {
                addSlabToCreativeInventory(new CreativeTabModifierEntry(registeredBlock.blockItem(), CreativeTabInsertionPosition.LAST, null));
            }
        }
        NORMAL_TO_VERTICAL_SLAB_MAP = VERTICAL_TO_NORMAL_SLAB_MAP.inverse();
        Services.PLATFORM.buildCreativeTabs();

        WeatheringCopperUtil.addDynamicWeatheringPairs(DYNAMIC_SLAB_BLOCKS);
//        WeatheringCopperUtil.addWeatheringPair(RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "vertical_cut_copper_slab")), RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "vertical_weathered_cut_copper_slab")));
    }

    private static void addSlabToCreativeInventory(CreativeTabModifierEntry modifierEntry) {
        List<CreativeModeTab> tabs = CreativeModeTabs.allTabs();
        tabs.forEach((creativeModeTab -> CreativeTabModifiers.addModifier(
            new CreativeTabModifier(
                ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), RegistryHelpers.getLocationFromCreativeTab(creativeModeTab)),
                new ArrayList<>(List.of(modifierEntry))
            )
        )));
    }

    public static boolean newSlabsSinceLastLaunch() {
        if(newSlabsSinceLastLaunch != null) {
            return newSlabsSinceLastLaunch;
        }
        try {
            byte[] bytesFromFile;
            try {
                bytesFromFile = Files.readAllBytes(EnchantedVerticalSlabsMod.getEVSModDirectory().resolve(KNOWN_SLABS_FILENAME));
            } catch (NoSuchFileException e) {
                writeSlabsListForThisLaunch();
                newSlabsSinceLastLaunch = true;
                return true;
            }
            Gson parser = new Gson();
            JsonElement jsonFromFile = parser.fromJson(new String(bytesFromFile, StandardCharsets.UTF_8), JsonElement.class);
            DataResult<Pair<List<ResourceLocation>, JsonElement>> decodedSlabsList = DYNAMIC_SLABS_LIST_CODEC.decode(JsonOps.INSTANCE, jsonFromFile);
            boolean newSlabs = !ListUtil.listsEqualOrderIndependent(decodedSlabsList.getOrThrow().getFirst(), DYNAMIC_SLAB_BLOCKS.stream().map(DynamicSlab::getOriginalSlabLocation).toList());
            if(!newSlabs) {
                newSlabsSinceLastLaunch = false;
                return false;
            }
            writeSlabsListForThisLaunch();
            newSlabsSinceLastLaunch = true;
            return true;
        } catch (Exception e) {
            EnchantedVerticalSlabsLogging.error("Error while trying to read list of known slabs, please report this if it persists.\n" + e);
        }
        writeSlabsListForThisLaunch();
        newSlabsSinceLastLaunch = true;
        return true;
    }

    private static void writeSlabsListForThisLaunch() {
        DataResult<JsonElement> encodedSlabsList = DYNAMIC_SLABS_LIST_CODEC.encodeStart(JsonOps.INSTANCE, DYNAMIC_SLAB_BLOCKS.stream().map(DynamicSlab::getOriginalSlabLocation).toList());
        try {
            FilesystemUtil.writeToFile(
                EnchantedVerticalSlabsMod.getEVSModDirectory().resolve(KNOWN_SLABS_FILENAME),
                encodedSlabsList.getOrThrow().toString().getBytes(StandardCharsets.UTF_8),
                true
            );
        } catch (Exception e) {
            EnchantedVerticalSlabsLogging.error("Error while trying to save list of known slabs, please report this if it persists.\n" + e);
        }
    }
}
