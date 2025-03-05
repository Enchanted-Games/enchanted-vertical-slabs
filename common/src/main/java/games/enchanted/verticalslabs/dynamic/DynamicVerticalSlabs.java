package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public class DynamicVerticalSlabs {
    public static final ArrayList<DynamicSlab> DYNAMIC_SLAB_BLOCKS = new ArrayList<>();

    public static void addDynamicSlab(ResourceLocation regularSlabLocation) {
        DYNAMIC_SLAB_BLOCKS.add(new DynamicSlab(regularSlabLocation));
    }

    public static void registerDynamicSlabs() {
        for (DynamicSlab slab : DYNAMIC_SLAB_BLOCKS) {
            RegistryHelpers.registerVerticalSlab(slab.getVerticalSlabLocation(), RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation()).properties());
        }
    }
}
