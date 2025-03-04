package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.util.ArrayUtil;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class DynamicResourcePack implements PackResources {
    public static final DynamicResourcePack INSTANCE = new DynamicResourcePack();
    public static final String PACK_ID = EnchantedVerticalSlabsConstants.MOD_ID + "_dynamic_resources";
    private static final PackLocationInfo PACK_INFO = new PackLocationInfo(PACK_ID, Component.literal("EVS Dynamic Resources"), PackSource.BUILT_IN, Optional.empty());
    public static final PackSelectionConfig PACK_SELECTION_CONFIG = new PackSelectionConfig(true, Pack.Position.TOP, false);

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

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String @NotNull ... strings) {
        FileUtil.validatePath(strings);

        Path resourcePath = Services.PLATFORM.getResourcePathFromModJar(ArrayUtil.appendToBeginningOfArray(strings, PACK_ID));
        return resourcePath == null ? null : IoSupplier.create(resourcePath);
    }

    @Override
    public @Nullable IoSupplier<InputStream> getResource(@NotNull PackType packType, @NotNull ResourceLocation resourceLocation) {
        return null;
    }

    @Override
    public void listResources(@NotNull PackType packType, @NotNull String namespace, @NotNull String path, @NotNull ResourceOutput resourceOutput) {

    }

    @Override
    public @NotNull Set<String> getNamespaces(@NotNull PackType packType) {
        if (packType == PackType.CLIENT_RESOURCES || packType == PackType.SERVER_DATA) {
            return Set.of(EnchantedVerticalSlabsConstants.MOD_ID);
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
