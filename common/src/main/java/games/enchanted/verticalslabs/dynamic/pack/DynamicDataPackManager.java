package games.enchanted.verticalslabs.dynamic.pack;

import games.enchanted.verticalslabs.dynamic.datagen.DynamicDataGenerator;
import games.enchanted.verticalslabs.dynamic.datagen.provider.DynamicBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class DynamicDataPackManager {
    private static boolean hasBeenInitialised = false;
    private static final DynamicDataGenerator dataGenerator = new DynamicDataGenerator();

    public static void initialise() {
        if(hasBeenInitialised) return;
        dataGenerator.addProvider(DynamicBlockLoot.getProvider(new PackOutput(Path.of("")), CompletableFuture.completedFuture(VanillaRegistries.createLookup())));

        try {
            dataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        hasBeenInitialised = true;
    }
}
