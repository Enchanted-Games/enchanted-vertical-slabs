package games.enchanted.verticalslabs.dynamic.pack;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import net.minecraft.resources.ResourceLocation;

public class DynamicDataPackManager {
    public static void initialise() {
        addTags();
    }

    public static void addTags() {
        String tag = """
            {
                "values": [
                    "#enchanted-vertical-slabs:slabs"
                ]
            }
            """;
        EVSDynamicResources.INSTANCE.addTag(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "test_tag"), tag);
    }
}
