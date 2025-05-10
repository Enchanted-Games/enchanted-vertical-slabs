package games.enchanted.verticalslabs.dynamic.pack;

import com.google.common.base.Joiner;
import com.google.common.base.Supplier;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.resources.ResourceGenerationException;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.util.ArrayUtil;
import games.enchanted.verticalslabs.util.FilesystemUtil;
import net.minecraft.FileUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public abstract class AbstractDynamicPack implements PackResources {
    private static final Joiner PATH_JOINER = Joiner.on("/");
    private final Pattern RAW_RESOURCE_PATH_PATTERN = Pattern.compile("^(assets|data)\\/([a-z0-9_.-]+)\\/([a-z0-9/._-]+)");

    private final ArrayList<ResourceType> CLIENT_RESOURCE_TYPES;
    private final HashMap<String, Integer> CLIENT_RESOURCE_TYPE_NAME_TO_ID;
    private final ArrayList<ResourceType> SERVER_RESOURCE_TYPES;
    private final HashMap<String, Integer> SERVER_RESOURCE_TYPE_NAME_TO_ID;
    @Nullable private final VirtualFiles<Supplier<IoSupplier<InputStream>>> RAW_RESOURCES;

    public final String PACK_ID;
    private final PackLocationInfo PACK_INFO;
    private final Set<String> PROVIDED_CLIENT_NAMESPACES;
    private final Set<String> PROVIDED_SERVER_NAMESPACES;

    @Nullable private final Path PARENT_DIRECTORY;
    @Nullable private Path ROOT_DIRECTORY;
    private final boolean inMemoryMode;

    public abstract AbstractDynamicPack getInstance();

    public AbstractDynamicPack(String packId, Component packName, Set<String> clientNamespaces, Set<String> serverNamespaces, @Nullable Path parentDirectory) {
        this(packId, packName, clientNamespaces, serverNamespaces, parentDirectory, false);
    }

    public AbstractDynamicPack(String packId, Component packName, Set<String> clientNamespaces, Set<String> serverNamespaces, @Nullable Path parentDirectory, boolean inMemoryMode) {
        if(!inMemoryMode && parentDirectory == null) {
            throw new IllegalArgumentException("DynamicPack '" + packId + "': parentDirectory cannot be null if inMemoryMode is set to true.");
        }
        if(parentDirectory != null && !Files.isDirectory(parentDirectory)) {
            throw new IllegalArgumentException("parentDirectory cannot be a file");
        }

        PACK_ID = packId;
        PACK_INFO = new PackLocationInfo(PACK_ID, packName, PackSource.BUILT_IN, Optional.empty());
        PROVIDED_CLIENT_NAMESPACES = clientNamespaces;
        PROVIDED_SERVER_NAMESPACES = serverNamespaces;
        this.inMemoryMode = inMemoryMode;

        CLIENT_RESOURCE_TYPES = new ArrayList<>();
        CLIENT_RESOURCE_TYPE_NAME_TO_ID = new HashMap<>();
        SERVER_RESOURCE_TYPES = new ArrayList<>();
        SERVER_RESOURCE_TYPE_NAME_TO_ID = new HashMap<>();
        registerResourceTypes();

        if(inMemoryMode) {
            RAW_RESOURCES = new VirtualFiles<>();

            PARENT_DIRECTORY = null;
        } else {
            RAW_RESOURCES = null;

            PARENT_DIRECTORY = parentDirectory;
            createRootDirectory();
        }
    }

    private String getLogPrefix() {
        return "Dynamic Pack '" + PACK_ID + "': ";
    }

    public void createRootDirectory() {
        if(PARENT_DIRECTORY == null) throw new IllegalStateException("PARENT_DIRECTORY should not be null if createRootDirectory is called.");
        Path packRootDirectory = PARENT_DIRECTORY.resolve(getDirectoryName());
        try {
            FileUtil.createDirectoriesSafe(packRootDirectory);
            ROOT_DIRECTORY = packRootDirectory;
        } catch (IOException e) {
            throw new IllegalStateException("Cannot create folder for '" + PACK_ID + "'\n" + e);
        }
    }

    public void clearDirectory(PackType packType) {
        if(inMemoryMode) {
            throw new IllegalStateException(getLogPrefix() + "Cannot call clearDirectory if inMemoryMode is true.");
        }
        if(ROOT_DIRECTORY == null) {
            throw new IllegalStateException(getLogPrefix() + "ROOT_DIRECTORY is null.");
        }
        Path directoryToRemove = ROOT_DIRECTORY.resolve(packType.getDirectory());
        try (Stream<Path> paths = Files.walk(directoryToRemove)) {
            paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }
        catch (NoSuchFileException ignored) {}
        catch (IOException e) {
            throw new ResourceGenerationException("Clearing" + packType.getDirectory() + " directory", e);
        }
    }

    private Path getDirectoryName() {
        return Path.of(PACK_ID);
    }

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

    public synchronized void addResource(String resourceTypeName, ResourceLocation location, Supplier<IoSupplier<InputStream>> resourceData, PackType packType) {
        boolean isServerResources = packType == PackType.SERVER_DATA;
        int resourceTypeIndex = (isServerResources ? SERVER_RESOURCE_TYPE_NAME_TO_ID : CLIENT_RESOURCE_TYPE_NAME_TO_ID).get(resourceTypeName);
        final int maxIndex = (isServerResources ? SERVER_RESOURCE_TYPES : CLIENT_RESOURCE_TYPES).size() - 1;
        if(resourceTypeIndex > maxIndex) throw new IllegalStateException("RESOURCE_TYPE_NAME_TO_ID returned an index (" + resourceTypeIndex + ") greater than maximum allowed value (" + maxIndex + ")");

        ResourceType resourceType = (isServerResources ? SERVER_RESOURCE_TYPES : CLIENT_RESOURCE_TYPES).get(resourceTypeIndex);

        if(!inMemoryMode) {
            try {
                ResourceLocation assetsRelativeLocation = resourceType.fileToIdConverter.idToFile(location);
                Path rootRelativePath = Path.of(packType.getDirectory(), assetsRelativeLocation.getNamespace(), assetsRelativeLocation.getPath());
                writeDataToRootDirectory(rootRelativePath, resourceData.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }

        ResourceLocation fileLocation = resourceType.fileToIdConverter.idToFile(location);
        resourceType.locationToResourceMap.put(fileLocation, resourceData);
    }

    public synchronized void addRawResource(String path, Supplier<IoSupplier<InputStream>> resourceData) {
        if(!inMemoryMode) {
            try {
                writeDataToRootDirectory(Paths.get(path), resourceData.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if(RAW_RESOURCES == null) {
            throw new IllegalStateException(getLogPrefix() + "Raw resources is null");
        }

        Matcher matcher = RAW_RESOURCE_PATH_PATTERN.matcher(path);
        matcher.matches();

        String packTypeFolder;
        try {
            packTypeFolder = matcher.group(1);
            if(!(Objects.equals(packTypeFolder, PackType.CLIENT_RESOURCES.getDirectory()) || Objects.equals(packTypeFolder, PackType.SERVER_DATA.getDirectory()))) {
                throw new IllegalArgumentException("Invalid root folder");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("path is invalid, the root folder must be either `assets` or `data`");
        }

        String pathNamespace;
        try {
            pathNamespace = matcher.group(2);
        } catch (Exception e) {
            throw new IllegalArgumentException("path is invalid, the namespace folder is invalid (must be [a-z0-9_.-])");
        }

        String pathResource;
        try {
            pathResource = matcher.group(3);
        } catch (Exception e) {
            throw new IllegalArgumentException("path is invalid, the resource path is invalid (must be [a-z0-9/._-])");
        }

        RAW_RESOURCES.add(PATH_JOINER.join(packTypeFolder, pathNamespace, pathResource), resourceData);
    }

    private void writeDataToRootDirectory(Path pathRelativeToRoot, IoSupplier<InputStream> data) throws IOException {
        if(ROOT_DIRECTORY == null) throw new IOException("Root directory is null for dynamic pack: " + PACK_ID);
        Path fullSystemPath = ROOT_DIRECTORY.resolve(pathRelativeToRoot);
        FilesystemUtil.createDirectories(fullSystemPath);
        Files.write(fullSystemPath, data.get().readAllBytes());
    }

    private @Nullable IoSupplier<InputStream> getDataFromRootDirectory(Path pathRelativeToRoot) throws IOException {
        if(ROOT_DIRECTORY == null) throw new IOException("Root directory is null for dynamic pack: " + PACK_ID);
        Path fullSystemPath = ROOT_DIRECTORY.resolve(pathRelativeToRoot);
        if(!Files.exists(fullSystemPath)) return null;
        return IoSupplier.create(fullSystemPath);
    }

    @Override
    public @Nullable IoSupplier<InputStream> getResource(@NotNull PackType packType, @NotNull ResourceLocation location) {
        if(!inMemoryMode) {
            try {
                Path rootRelativePath = Path.of(packType.getDirectory(), location.getNamespace(), location.getPath());
                return getDataFromRootDirectory(rootRelativePath);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if(RAW_RESOURCES == null) {
            throw new IllegalStateException(getLogPrefix() + "Raw resources is null");
        }

        // try to get resource from a resource type
        ArrayList<ResourceType> resourcesList = packType == PackType.CLIENT_RESOURCES ? CLIENT_RESOURCE_TYPES : SERVER_RESOURCE_TYPES;
        for (ResourceType type : resourcesList) {
            if (type.locationToResourceMap.containsKey(location)) {
                return type.locationToResourceMap.get(location).get();
            }
        }
        // otherwise try and get resource from raw resources
        VirtualFiles<Supplier<IoSupplier<InputStream>>> rawResource;
        try {
            String[] rawPath = PATH_JOINER.join(packType.getDirectory(), location.getNamespace(), location.getPath()).split("/");
            rawResource = RAW_RESOURCES.getFile(rawPath);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return rawResource.content != null ? rawResource.content.get() : null;
    }

    @Override
    public void listResources(@NotNull PackType packType, @NotNull String namespace, @NotNull String path, @NotNull ResourceOutput resourceOutput) {
        if (!(PROVIDED_SERVER_NAMESPACES.contains(namespace) || PROVIDED_CLIENT_NAMESPACES.contains(namespace))) return;

        if(!inMemoryMode) {
            FileUtil.decomposePath(path).ifSuccess(pathToResource -> {
                if(ROOT_DIRECTORY == null) {
                    throw new IllegalStateException("ROOT_DIRECTORY is null");
                }
                Path pathToNamespace = ROOT_DIRECTORY.resolve(packType.getDirectory()).resolve(namespace);
                PathPackResources.listPath(namespace, pathToNamespace, pathToResource, resourceOutput);
            }).ifError(error -> EnchantedVerticalSlabsLogging.error("Error listing resources for path {}: {}", path, error.message()));
            return;
        }

        if(RAW_RESOURCES == null) {
            throw new IllegalStateException(getLogPrefix() + "Raw resources is null");
        }

        // list resource types
        ArrayList<ResourceType> resourcesList = packType == PackType.CLIENT_RESOURCES ? CLIENT_RESOURCE_TYPES : SERVER_RESOURCE_TYPES;
        for (ResourceType type : resourcesList) {
            if (path.equals(type.directoryInPack)) {
                for (var entry : type.locationToResourceMap.entrySet()) {
                    resourceOutput.accept(entry.getKey(), entry.getValue().get());
                }
            }
        }

        // list raw resources
        VirtualFiles<Supplier<IoSupplier<InputStream>>> rawResourcesDirectory;
        String rawResourcesDirectoryPath = PATH_JOINER.join(packType.getDirectory(), namespace, path);
        try {
            rawResourcesDirectory = RAW_RESOURCES.getDirectory(rawResourcesDirectoryPath.split("/"));
        } catch (IllegalArgumentException ignored) {
            rawResourcesDirectory = null;
        }
        if(rawResourcesDirectory != null) {
            rawResourcesDirectory.iterateOnFiles(((VirtualFiles<?> file, String filePath, String fileExtension) -> {
                ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, PATH_JOINER.join(path, filePath + "." + fileExtension));
                if (file.content != null) {
                    resourceOutput.accept(location, getResource(packType, location));
                }
            }));
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
