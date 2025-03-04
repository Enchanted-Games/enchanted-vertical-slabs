package games.enchanted.verticalslabs.dynamic.pack;

import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.IoSupplier;

import java.io.InputStream;
import java.util.Map;

public class ResourceType {
    public final String directoryInPack;
    public final Map<ResourceLocation, IoSupplier<InputStream>> locationToResourceMap;
    public final FileToIdConverter fileToIdConverter;

    public ResourceType(String directoryInPack, Map<ResourceLocation, IoSupplier<InputStream>> locationToResourceMap, FileToIdConverter fileToIdConverter) {
        this.directoryInPack = directoryInPack;
        this.locationToResourceMap = locationToResourceMap;
        this.fileToIdConverter = fileToIdConverter;
    }
}
