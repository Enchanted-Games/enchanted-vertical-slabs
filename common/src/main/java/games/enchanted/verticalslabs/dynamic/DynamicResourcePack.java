package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.util.ArrayUtil;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.FileUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;

public class DynamicResourcePack implements PackResources {
    public static final DynamicResourcePack INSTANCE = new DynamicResourcePack();

    public static final String PACK_ID = EnchantedVerticalSlabsConstants.MOD_ID + "_dynamic_resources";
    private static final PackLocationInfo PACK_INFO = new PackLocationInfo(PACK_ID, Component.literal("EVS Dynamic Resources"), PackSource.BUILT_IN, Optional.empty());
    public static final PackSelectionConfig PACK_SELECTION_CONFIG = new PackSelectionConfig(true, Pack.Position.TOP, false);

    private static final Map<ResourceLocation, String> BLOCKSTATES = new HashMap<>();
    private static final FileToIdConverter BLOCKSTATES_LISTER = new FileToIdConverter("blockstates", ".json");

    public static final Pack.ResourcesSupplier RESOURCES_SUPPLIER = new Pack.ResourcesSupplier() {
        @Override
        public @NotNull PackResources openPrimary(@NotNull PackLocationInfo location) {
            return INSTANCE;
        }

        @Override
        public @NotNull PackResources openFull(@NotNull PackLocationInfo location, Pack.@NotNull Metadata metadata) {
            return INSTANCE;
        }
    };

    public void addBlockstate(ResourceLocation location, String stringifiedModelJSON) {
        System.out.println("Added state model: " + location.toString());
        BLOCKSTATES.put(BLOCKSTATES_LISTER.idToFile(location), stringifiedModelJSON);
    }

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String @NotNull ... strings) {
        FileUtil.validatePath(strings);

        Path resourcePath = Services.PLATFORM.getResourcePathFromModJar(ArrayUtil.appendToBeginningOfArray(strings, PACK_ID));
        return resourcePath == null ? null : IoSupplier.create(resourcePath);
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

    @Override
    public @NotNull Set<String> getNamespaces(@NotNull PackType packType) {
        if (packType == PackType.CLIENT_RESOURCES) {
            return Set.of(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION);
        }
        return Set.of();
    }

    @Override
    public @Nullable <T> T getMetadataSection(@NotNull MetadataSectionType<T> metadataSectionType) throws IOException {
        return AbstractPackResources.getMetadataFromStream(metadataSectionType, Objects.requireNonNull(getRootResource("pack.mcmeta")).get());
    }

    @Override
    public @NotNull PackLocationInfo location() {
        return PACK_INFO;
    }

    @Override
    public void close() {}
}
