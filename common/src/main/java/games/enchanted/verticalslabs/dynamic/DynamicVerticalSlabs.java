package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public class DynamicVerticalSlabs {
    private static final ArrayList<ResourceLocation> registeredSlabBlocks = new ArrayList<>();

    public static void addSlabLocation(ResourceLocation location) {
        registeredSlabBlocks.add(location);
    }

    public static void registerDynamicSlabs() {
        for (ResourceLocation slabLocation : registeredSlabBlocks) {
            String newPath = slabLocation.getNamespace() + "/vertical_" + slabLocation.getPath();
            RegistryHelpers.registerVerticalSlab(newPath, RegistryHelpers.getBlockFromLocation(slabLocation).properties());
        }
    }
}
