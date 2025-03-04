package games.enchanted.verticalslabs.dynamic.pack;

import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.util.ArrayUtil;
import net.minecraft.FileUtil;
import net.minecraft.network.chat.Component;
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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractDynamicPack implements PackResources {
    public final String PACK_ID;
    public final PackType PACK_TYPE;
    private final PackLocationInfo PACK_INFO;
    private final Set<String> PROVIDED_NAMESPACES;

    public AbstractDynamicPack(PackType packType, String packId, Component packName, Set<String> providedNamespaces) {
        PACK_TYPE = packType;
        PACK_ID = packId;
        PACK_INFO = new PackLocationInfo(PACK_ID, packName, PackSource.BUILT_IN, Optional.empty());
        PROVIDED_NAMESPACES = providedNamespaces;
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
