package games.enchanted.verticalslabs.dynamic.pack;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.util.ArrayUtil;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.FileUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
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

public abstract class AbstractDynamicPack implements PackResources {
    private final ArrayList<ResourceType> RESOURCE_TYPES = new ArrayList<>();
    private final HashMap<String, Integer> RESOURCE_TYPE_NAME_TO_ID = new HashMap<>();

    public final String PACK_ID;
    public final PackType PACK_TYPE;
    private final PackLocationInfo PACK_INFO;
    private final Set<String> PROVIDED_NAMESPACES;

    public AbstractDynamicPack(PackType packType, String packId, Component packName, Set<String> providedNamespaces) {
        PACK_TYPE = packType;
        PACK_ID = packId;
        PACK_INFO = new PackLocationInfo(PACK_ID, packName, PackSource.BUILT_IN, Optional.empty());
        PROVIDED_NAMESPACES = providedNamespaces;
        registerResourceTypes();
    }

    public abstract AbstractDynamicPack getInstance();

    public Pack.ResourcesSupplier getResourcesSupplier() {
        return new Pack.ResourcesSupplier() {
            @Override
            public @NotNull PackResources openPrimary(@NotNull PackLocationInfo location) {
                return getInstance();
            }

            @Override
            public @NotNull PackResources openFull(@NotNull PackLocationInfo location, Pack.@NotNull Metadata metadata) {
                return getInstance();
            }
        };
    }

    protected abstract void registerResourceTypes();

    protected void registerResourceType(String resourceTypeName, ResourceType resourceType) {
        RESOURCE_TYPE_NAME_TO_ID.put(resourceTypeName, RESOURCE_TYPES.size());
        RESOURCE_TYPES.add(resourceType);
    }

    public void addResource(String resourceTypeName, ResourceLocation location, String stringifiedModelJSON) {
        int resourceTypeIndex = RESOURCE_TYPE_NAME_TO_ID.get(resourceTypeName);
        final int maxIndex = RESOURCE_TYPES.size() - 1;
        if(resourceTypeIndex > maxIndex) throw new IllegalStateException("RESOURCE_TYPE_NAME_TO_ID returned an index (" + resourceTypeIndex + ") greater than maximum allowed value (" + maxIndex + ")");
        ResourceType resourceType = RESOURCE_TYPES.get(resourceTypeIndex);
        ResourceLocation fileLocation = resourceType.fileToIdConverter().idToFile(location);
        resourceType.locationToStringifiedModel().put(fileLocation, stringifiedModelJSON);
    }
    
    @Override
    public @Nullable IoSupplier<InputStream> getResource(@NotNull PackType packType, @NotNull ResourceLocation location) {
        if (packType != PackType.CLIENT_RESOURCES) {
            return null;
        }
        for (ResourceType type : RESOURCE_TYPES) {
            if (type.locationToStringifiedModel().containsKey(location)) {
                return IoSupplierUtil.stringToIoSupplier(type.locationToStringifiedModel().get(location));
            }
        }
        return null;
    }

    @Override
    public void listResources(@NotNull PackType packType, @NotNull String namespace, @NotNull String path, @NotNull ResourceOutput resourceOutput) {
        if (!namespace.equals(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION)) return;
        if (packType != PackType.CLIENT_RESOURCES) return;

        for (ResourceType type : RESOURCE_TYPES) {
            if (path.equals(type.packDirectory())) {
                for (var entry : type.locationToStringifiedModel().entrySet()) {
                    resourceOutput.accept(entry.getKey(), IoSupplierUtil.stringToIoSupplier(entry.getValue()));
                }
            }
        }
    }

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String @NotNull ... strings) {
        FileUtil.validatePath(strings);
        Path resourcePath = Services.PLATFORM.getResourcePathFromModJar(ArrayUtil.appendToBeginningOfArray(strings, PACK_ID));
        return resourcePath == null ? null : IoSupplier.create(resourcePath);
    }

    @Override
    public @NotNull Set<String> getNamespaces(@NotNull PackType packType) {
        if (packType == PACK_TYPE) {
            return PROVIDED_NAMESPACES;
        }
        return Set.of();
    }

    @Override
    public @Nullable <T> T getMetadataSection(@NotNull MetadataSectionType<T> metadataSectionType) throws IOException {
        return AbstractPackResources.getMetadataFromStream(metadataSectionType, Objects.requireNonNull(getRootResource("pack.mcmeta")).get());
    }

    @Override
    public @NotNull PackLocationInfo location() { return PACK_INFO; }

    @Override
    public void close() {}
}
