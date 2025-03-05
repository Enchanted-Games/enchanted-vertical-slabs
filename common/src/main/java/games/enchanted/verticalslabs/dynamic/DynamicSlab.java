package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import net.minecraft.resources.ResourceLocation;

public class DynamicSlab {
    private final ResourceLocation ORIGINAL_SLAB_LOCATION;
    private final ResourceLocation VERTICAL_SLAB_LOCATION;

    DynamicSlab(ResourceLocation originalSlabLocation) {
        ORIGINAL_SLAB_LOCATION = originalSlabLocation;
        VERTICAL_SLAB_LOCATION = ResourceLocation.fromNamespaceAndPath(
            EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION,
            originalSlabLocation.getNamespace() + "/vertical_" + originalSlabLocation.getPath()
        );
    }

    public ResourceLocation getOriginalSlabLocation() {
        return ORIGINAL_SLAB_LOCATION;
    }

    public ResourceLocation getVerticalSlabLocation() {
        return VERTICAL_SLAB_LOCATION;
    }

}
