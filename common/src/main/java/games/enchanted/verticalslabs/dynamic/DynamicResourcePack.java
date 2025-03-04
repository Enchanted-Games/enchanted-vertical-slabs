package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.dynamic.pack.AbstractDynamicPack;
import games.enchanted.verticalslabs.dynamic.pack.ResourceType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;

import java.util.HashMap;
import java.util.Set;

public class DynamicResourcePack extends AbstractDynamicPack {
    public static final DynamicResourcePack INSTANCE = new DynamicResourcePack(
        PackType.CLIENT_RESOURCES,
        EnchantedVerticalSlabsConstants.MOD_ID + "_dynamic_resources",
        Component.literal("EVS Dynamic Resources"),
        Set.of(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION)
    );
    public static final PackSelectionConfig PACK_SELECTION_CONFIG = new PackSelectionConfig(true, Pack.Position.TOP, false);

    public DynamicResourcePack(PackType packType, String packId, Component packName, Set<String> providedNamespaces) {
        super(packType, packId, packName, providedNamespaces);
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
            )
        );
    }

    public void addBlockstate(ResourceLocation location, String stringifiedModelJSON) {
        addResource("blockstate", location, stringifiedModelJSON);
    }
}
