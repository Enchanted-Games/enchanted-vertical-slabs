package games.enchanted.verticalslabs.util;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class BlockRenderTypeUtil {
    public static Map<ResourceLocation, ResourceLocation> BLOCK_ALIAS_MAP = new HashMap<>();

    public static void copyRenderTypeFromBlock(ResourceLocation newBlockLocation, ResourceLocation blockToCopyFrom) {
        BLOCK_ALIAS_MAP.put(newBlockLocation, blockToCopyFrom);
    }
}
