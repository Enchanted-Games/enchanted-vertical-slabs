package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.dynamic.pack.AbstractDynamicPack;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamicResourcePack extends AbstractDynamicPack {
    private static final Map<ResourceLocation, String> BLOCKSTATES = new HashMap<>();
    private static final FileToIdConverter BLOCKSTATES_LISTER = new FileToIdConverter("blockstates", ".json");

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

    public void addBlockstate(ResourceLocation location, String stringifiedModelJSON) {
        System.out.println("Added state model: " + location.toString());
        BLOCKSTATES.put(BLOCKSTATES_LISTER.idToFile(location), stringifiedModelJSON);
    }

    @Override
    public @Nullable IoSupplier<InputStream> getResource(@NotNull PackType packType, @NotNull ResourceLocation location) {
        if (packType != PackType.CLIENT_RESOURCES) {
            return null;
        }
        if (BLOCKSTATES.containsKey(location)) {
            return IoSupplierUtil.stringToIoSupplier(BLOCKSTATES.get(location));
        }
        return null;
    }

    @Override
    public void listResources(@NotNull PackType packType, @NotNull String namespace, @NotNull String path, @NotNull ResourceOutput resourceOutput) {
        if (!namespace.equals(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION)) return;
        if (packType != PackType.CLIENT_RESOURCES) return;

        if (path.equals("blockstates")) {
            for (var entry : BLOCKSTATES.entrySet()) {
                resourceOutput.accept(entry.getKey(), IoSupplierUtil.stringToIoSupplier(entry.getValue()));
            }
        }
    }
}
