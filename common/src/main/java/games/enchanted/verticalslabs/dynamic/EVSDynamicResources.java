package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.dynamic.pack.AbstractDynamicPack;
import games.enchanted.verticalslabs.dynamic.pack.ResourceType;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;

import java.util.HashMap;
import java.util.Set;

public class EVSDynamicResources extends AbstractDynamicPack {
    public static final EVSDynamicResources INSTANCE = new EVSDynamicResources(
        EnchantedVerticalSlabsConstants.MOD_ID + "_dynamic_resources",
        Component.literal("EVS Dynamic Resources"),
        Set.of(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION),
        Set.of(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION)
    );
    public static final PackSelectionConfig PACK_SELECTION_CONFIG = new PackSelectionConfig(true, Pack.Position.TOP, false);

    public EVSDynamicResources(String packId, Component packName, Set<String> clientNamespaces, Set<String> serverNamespaces) {
        super(packId, packName, clientNamespaces, serverNamespaces);
    }

    @Override
    public AbstractDynamicPack getInstance() {
        return INSTANCE;
    }

    @Override
    protected void registerResourceTypes() {
        registerResourceType(
            "blockstate",
            new ResourceType(
                "blockstates",
                new HashMap<>(),
                new FileToIdConverter("blockstates", ".json")
            ),
            PackType.CLIENT_RESOURCES
        );
    }

    public void addBlockstate(ResourceLocation location, String stringifiedModelJSON) {
        addResource("blockstate", location, IoSupplierUtil.stringToIoSupplier(stringifiedModelJSON), PackType.CLIENT_RESOURCES);
    }
}
