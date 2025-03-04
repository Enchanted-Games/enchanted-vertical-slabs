package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public class DynamicVerticalSlabs {
    // TODO: dont store just the original slab location here
    public static final ArrayList<ResourceLocation> DYNAMIC_SLAB_BLOCKS = new ArrayList<>();

    public static void addDynamicSlab(ResourceLocation regularSlabLocation) {
        DYNAMIC_SLAB_BLOCKS.add(regularSlabLocation);
    }

    public static void registerDynamicSlabs() {
        for (ResourceLocation slabLocation : DYNAMIC_SLAB_BLOCKS) {
            String newPath = slabLocation.getNamespace() + "/vertical_" + slabLocation.getPath();
            RegistryHelpers.registerVerticalSlab(newPath, RegistryHelpers.getBlockFromLocation(slabLocation).properties());
        }
    }
}
