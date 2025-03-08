package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DynamicSlab {
    private final ResourceLocation ORIGINAL_SLAB_LOCATION;
    private final ResourceLocation VERTICAL_SLAB_LOCATION;
    @Nullable private final ResourceLocation REGULAR_BLOCK_LOCATION;

    DynamicSlab(ResourceLocation originalSlabLocation) {
        ORIGINAL_SLAB_LOCATION = originalSlabLocation;
        VERTICAL_SLAB_LOCATION = ResourceLocation.fromNamespaceAndPath(
            EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION,
            originalSlabLocation.getNamespace() + "/vertical_" + originalSlabLocation.getPath()
        );
        REGULAR_BLOCK_LOCATION = tryFindRegularBlock();
    }

    private @Nullable ResourceLocation tryFindRegularBlock() {
        String probableBlockLocation = this.getOriginalSlabLocation().getPath().replace("_slab", "");
        String originalNamespace = this.getOriginalSlabLocation().getNamespace();
        Block probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(originalNamespace, probableBlockLocation));
        if(probableBlock.defaultBlockState().isAir()) {
            probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.withDefaultNamespace(probableBlockLocation));
        }
        if(probableBlock.defaultBlockState().isAir()) {
            return null;
        }
        return RegistryHelpers.getLocationFromBlock(probableBlock);
    }

    public ResourceLocation getOriginalSlabLocation() {
        return ORIGINAL_SLAB_LOCATION;
    }

    public ResourceLocation getVerticalSlabLocation() {
        return VERTICAL_SLAB_LOCATION;
    }

    public Optional<ResourceLocation> getRegularBlockLocation() {
        return REGULAR_BLOCK_LOCATION == null ? Optional.empty() : Optional.of(REGULAR_BLOCK_LOCATION);
    }
}
