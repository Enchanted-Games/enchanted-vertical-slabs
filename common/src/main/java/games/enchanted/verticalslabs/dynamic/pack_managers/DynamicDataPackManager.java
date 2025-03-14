package games.enchanted.verticalslabs.dynamic.pack_managers;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.datagen.DynamicDataGenerator;
import games.enchanted.verticalslabs.dynamic.datagen.provider.DynamicBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class DynamicDataPackManager implements PackManager {
    public static DynamicDataPackManager INSTANCE = new DynamicDataPackManager();
    private static boolean hasBeenInitialised = false;
    private static boolean needsReloadToApply = true;

    private static final DynamicDataGenerator dataGenerator = new DynamicDataGenerator();

    private DynamicDataPackManager() {}

    public void initialise() {
        if(hasBeenInitialised) return;
        EnchantedVerticalSlabsLogging.info("Initialising Dynamic Data Pack");
        dataGenerator.addProvider(DynamicBlockLoot.getProvider(new PackOutput(Path.of("")), CompletableFuture.completedFuture(VanillaRegistries.createLookup())));

        try {
            dataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        hasBeenInitialised = true;
    }

    @Override
    public boolean requiresReloadToApply() {
        // TODO: only return true here if not loading pack from disk
        return needsReloadToApply;
    }

    @Override
    public void triggeredReload() {
        needsReloadToApply = false;
        // TODO: add proper message
        EnchantedVerticalSlabsLogging.info("Reloaded Data <add better message here>");
    }
}
