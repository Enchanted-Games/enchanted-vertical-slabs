package games.enchanted.verticalslabs.dynamic.pack;

import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public record ResourceType(String packDirectory, Map<ResourceLocation, String> locationToStringifiedModel, FileToIdConverter fileToIdConverter) {
}
