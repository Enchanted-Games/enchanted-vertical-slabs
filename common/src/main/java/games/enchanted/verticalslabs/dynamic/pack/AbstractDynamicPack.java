package games.enchanted.verticalslabs.dynamic.pack;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.util.ArrayUtil;
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
    private final ArrayList<ResourceType> CLIENT_RESOURCE_TYPES = new ArrayList<>();
    private final HashMap<String, Integer> CLIENT_RESOURCE_TYPE_NAME_TO_ID = new HashMap<>();
    private final ArrayList<ResourceType> SERVER_RESOURCE_TYPES = new ArrayList<>();
    private final HashMap<String, Integer> SERVER_RESOURCE_TYPE_NAME_TO_ID = new HashMap<>();

    public final String PACK_ID;
    private final PackLocationInfo PACK_INFO;
    private final Set<String> PROVIDED_CLIENT_NAMESPACES;
    private final Set<String> PROVIDED_SERVER_NAMESPACES;

    public AbstractDynamicPack(String packId, Component packName, Set<String> clientNamespaces, Set<String> serverNamespaces) {
        PACK_ID = packId;
        PACK_INFO = new PackLocationInfo(PACK_ID, packName, PackSource.BUILT_IN, Optional.empty());
        PROVIDED_CLIENT_NAMESPACES = clientNamespaces;
        PROVIDED_SERVER_NAMESPACES = serverNamespaces;
        registerResourceTypes();
    }

    public abstract AbstractDynamicPack getInstance();

    public Pack.ResourcesSupplier getResourcesSupplier() {
        return new Pack.ResourcesSupplier() {
            @Override
            public @NotNull PackResources openPrimary(@NotNull PackLocationInfo packLocationInfo) {
                return getInstance();
            }

            @Override
            public @NotNull PackResources openFull(@NotNull PackLocationInfo packLocationInfo, @NotNull Pack.Metadata metadata) {
                return getInstance();
            }
        };
    }

    protected abstract void registerResourceTypes();

    protected void registerResourceType(String resourceTypeName, ResourceType resourceType, PackType packType) {
        if(packType == PackType.CLIENT_RESOURCES) {
            CLIENT_RESOURCE_TYPE_NAME_TO_ID.put(resourceTypeName, CLIENT_RESOURCE_TYPES.size());
            CLIENT_RESOURCE_TYPES.add(resourceType);
            return;
        }
        SERVER_RESOURCE_TYPE_NAME_TO_ID.put(resourceTypeName, SERVER_RESOURCE_TYPES.size());
        SERVER_RESOURCE_TYPES.add(resourceType);
    }

    public void addResource(String resourceTypeName, ResourceLocation location, IoSupplier<InputStream> resourceData, PackType packType) {
        boolean isServerResources = packType == PackType.SERVER_DATA;
        int resourceTypeIndex = (isServerResources ? SERVER_RESOURCE_TYPE_NAME_TO_ID : CLIENT_RESOURCE_TYPE_NAME_TO_ID).get(resourceTypeName);
        final int maxIndex = (isServerResources ? SERVER_RESOURCE_TYPES : CLIENT_RESOURCE_TYPES).size() - 1;
        if(resourceTypeIndex > maxIndex) throw new IllegalStateException("RESOURCE_TYPE_NAME_TO_ID returned an index (" + resourceTypeIndex + ") greater than maximum allowed value (" + maxIndex + ")");

        ResourceType resourceType = (isServerResources ? SERVER_RESOURCE_TYPES : CLIENT_RESOURCE_TYPES).get(resourceTypeIndex);
        ResourceLocation fileLocation = resourceType.fileToIdConverter.idToFile(location);
        resourceType.locationToResourceMap.put(fileLocation, resourceData);
    }
    
    @Override
    public @Nullable IoSupplier<InputStream> getResource(@NotNull PackType packType, @NotNull ResourceLocation location) {
        ArrayList<ResourceType> resourcesList = packType == PackType.CLIENT_RESOURCES ? CLIENT_RESOURCE_TYPES : SERVER_RESOURCE_TYPES;
        for (ResourceType type : resourcesList) {
            if (type.locationToResourceMap.containsKey(location)) {
                return type.locationToResourceMap.get(location);
            }
        }
        return null;
    }

    @Override
    public void listResources(@NotNull PackType packType, @NotNull String namespace, @NotNull String path, @NotNull ResourceOutput resourceOutput) {
        if (!(PROVIDED_SERVER_NAMESPACES.contains(namespace) && PROVIDED_CLIENT_NAMESPACES.contains(namespace))) return;
        ArrayList<ResourceType> resourcesList = packType == PackType.CLIENT_RESOURCES ? CLIENT_RESOURCE_TYPES : SERVER_RESOURCE_TYPES;

        for (ResourceType type : resourcesList) {
            if (path.equals(type.directoryInPack)) {
                for (var entry : type.locationToResourceMap.entrySet()) {
                    resourceOutput.accept(entry.getKey(), entry.getValue());
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
        if (packType == PackType.CLIENT_RESOURCES) {
            return PROVIDED_CLIENT_NAMESPACES;
        } else if (packType == PackType.SERVER_DATA) {
            return PROVIDED_SERVER_NAMESPACES;
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
