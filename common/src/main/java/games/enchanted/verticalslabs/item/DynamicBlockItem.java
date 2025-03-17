package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DynamicBlockItem extends BlockItem {
    @Nullable
    private final ResourceLocation ORIGINAL_ITEM_LOCATION;

    public DynamicBlockItem(Block block, Properties properties, DynamicSlab dynamicSlab) {
        super(block, properties);
        ORIGINAL_ITEM_LOCATION = dynamicSlab.getOriginalSlabLocation();
    }

    public Optional<ResourceLocation> getOriginalItemLocation() {
        return ORIGINAL_ITEM_LOCATION == null ? Optional.empty() : Optional.of(ORIGINAL_ITEM_LOCATION);
    }
}
