package games.enchanted.verticalslabs.dynamic.pack;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.dynamic.datagen.DynamicDataGenerator;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class DynamicDataPackManager {
    private static boolean hasBeenInitialised = false;
    public static void initialise() {
        if(hasBeenInitialised) return;
        addTags();
        EVSDynamicResources.INSTANCE.addRawResource("data/valid/tags/item/custom.json", () -> IoSupplierUtil.stringToIoSupplier("""
            {
                "values": [
                    "minecraft:orange_dye"
                ]
            }
            """));
        EVSDynamicResources.INSTANCE.addRawResource("data/enchanted-vertical-slabs/tags/item/custom.json", () -> IoSupplierUtil.stringToIoSupplier("""
            {
                "values": [
                    "minecraft:orange_dye"
                ]
            }
            """));
        hasBeenInitialised = true;
    }

    public static void addTags() {
        DynamicDataGenerator dataGenerator = new DynamicDataGenerator();
        dataGenerator.addProvider(new VanillaBlockTagsProvider(new PackOutput(Path.of("")), CompletableFuture.completedFuture(VanillaRegistries.createLookup())));
        try {
            dataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String tag = """
            {
                "values": [
                    "blue_dye"
                ]
            }
            """;
        EVSDynamicResources.INSTANCE.addItemTag(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "test_tag"), tag);
    }
}
