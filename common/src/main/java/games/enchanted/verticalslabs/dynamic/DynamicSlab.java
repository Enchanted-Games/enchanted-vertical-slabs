package games.enchanted.verticalslabs.dynamic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DynamicSlab {
    public static final Codec<DynamicSlab> CODEC = Codec.STRING.comapFlatMap(DynamicSlab::read, (dynamicSlab) -> dynamicSlab.ORIGINAL_SLAB_LOCATION.toString()).stable();

    public static DataResult<DynamicSlab> read(String slabLocation) {
        try {
            ResourceLocation slabResourceLocation = ResourceLocation.parse(slabLocation);
            return DataResult.success(new DynamicSlab(slabResourceLocation));
        } catch (ResourceLocationException resourcelocationexception) {
            return DataResult.error(() -> "Not a valid resource location: " + slabLocation + " " + resourcelocationexception.getMessage());
        }
    }

    private final ResourceLocation ORIGINAL_SLAB_LOCATION;
    private final ResourceLocation VERTICAL_SLAB_LOCATION;
    @Nullable private final ResourceLocation REGULAR_BLOCK_LOCATION;
    @Nullable private final ResourceLocation REGULAR_ITEM_LOCATION;

    public DynamicSlab(ResourceLocation originalSlabLocation) {
        ORIGINAL_SLAB_LOCATION = originalSlabLocation;
        VERTICAL_SLAB_LOCATION = ResourceLocation.fromNamespaceAndPath(
            EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE,
            (originalSlabLocation.getNamespace().equals("minecraft") ? "" : originalSlabLocation.getNamespace() + "/") + "vertical_" + originalSlabLocation.getPath()
        );
        REGULAR_BLOCK_LOCATION = tryFindRegularBlock();

        Item regularItem = RegistryHelpers.getItemFromLocation(ORIGINAL_SLAB_LOCATION);
        REGULAR_ITEM_LOCATION = RegistryHelpers.getLocationFromItem(regularItem);
    }

    private @Nullable ResourceLocation tryFindRegularBlock() {
        ResourceLocation originalLocation = this.getOriginalSlabLocation();
        String possibleRegularBlockLocation = originalLocation.getPath().replace("_slab", "");
        String possibleRegularBlockPlanksLocation = originalLocation.getPath().replace("_slab", "_planks");
        String possibleRegularBlockBrickLocation = originalLocation.getPath().replace("_slab", "_brick");
        String originalNamespace = originalLocation.getNamespace();

        // check regular block (no _slab suffix)
        Block probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(originalNamespace, possibleRegularBlockLocation));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(originalNamespace, possibleRegularBlockLocation + "s"));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.withDefaultNamespace(possibleRegularBlockLocation));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.withDefaultNamespace(possibleRegularBlockLocation + "s"));

        // check plank blocks (replace _slab with _planks)
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(originalNamespace, possibleRegularBlockPlanksLocation));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.withDefaultNamespace(possibleRegularBlockPlanksLocation));

        // check brick blocks (replace _slab with _brick, and check _bricks)
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(originalNamespace, possibleRegularBlockBrickLocation));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(originalNamespace, possibleRegularBlockBrickLocation + "s"));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.withDefaultNamespace(possibleRegularBlockBrickLocation));
        if(probableBlock == Blocks.AIR) probableBlock = RegistryHelpers.getBlockFromLocation(ResourceLocation.withDefaultNamespace(possibleRegularBlockBrickLocation + "s"));

        if(probableBlock == Blocks.AIR) return null;

        return RegistryHelpers.getLocationFromBlock(probableBlock);
    }

    public boolean shouldUvLockModel() {
        // TODO: convert these to config options
        boolean glass = VERTICAL_SLAB_LOCATION.getPath().contains("glass");
        return !glass;
    }

    public boolean shouldAttemptToGenerateRealVerticalSlabModel() {
        // TODO: convert these to config options
        boolean glass = VERTICAL_SLAB_LOCATION.getPath().contains("glass");
        return !glass;
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

    public Optional<ResourceLocation> getRegularItemLocation() {
        return REGULAR_ITEM_LOCATION == null ? Optional.empty() : Optional.of(REGULAR_ITEM_LOCATION);
    }
}
