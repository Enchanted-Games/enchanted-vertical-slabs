package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DynamicVerticalSlabs {
    public static final ArrayList<DynamicSlab> DYNAMIC_SLAB_BLOCKS = new ArrayList<>();
    public static Map<Block, Block> VERTICAL_TO_NORMAL_SLAB_MAP = new HashMap<>();

    public static void addDynamicSlab(ResourceLocation regularSlabLocation) {
        DYNAMIC_SLAB_BLOCKS.add(new DynamicSlab(regularSlabLocation));
    }

    public static void registerDynamicSlabs() {
        for (DynamicSlab slab : DYNAMIC_SLAB_BLOCKS) {
            Block regularSlabBlock = RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation());
            BlockAndItemContainer registeredBlock = RegistryHelpers.registerDynamicVerticalSlab(slab.getVerticalSlabLocation(), RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation()).properties(), regularSlabBlock);
            VERTICAL_TO_NORMAL_SLAB_MAP.put(registeredBlock.block(), regularSlabBlock);
        }
    }
}
