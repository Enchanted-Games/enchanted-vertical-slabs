package games.enchanted.verticalslabs.dynamic.pack_managers;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.datagen.DynamicDataGenerator;
import games.enchanted.verticalslabs.dynamic.datagen.provider.DynamicBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class DynamicDataPackManager extends PackManager {
    public static DynamicDataPackManager INSTANCE = new DynamicDataPackManager();

    private static final DynamicDataGenerator dataGenerator = new DynamicDataGenerator();

    private DynamicDataPackManager() {}

    @Override
    void initialiseResources() {
        EnchantedVerticalSlabsLogging.info("Initialising Dynamic Data Pack");
        dataGenerator.addProvider(DynamicBlockLoot.getProvider(new PackOutput(Path.of("")), CompletableFuture.completedFuture(VanillaRegistries.createLookup())));

        CompletableFuture<?> asyncTasks;
        try {
            asyncTasks = dataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        asyncTasks.thenRun(() -> {
            complete(true, () -> {
                EnchantedVerticalSlabsLogging.info("[Dynamic Datapack]: Async datagenerators completed successfully");
                EnchantedVerticalSlabsLogging.info("[Dynamic Datapack]: Reloading datapacks to apply changes");
            });
        })
        .exceptionally((exception) -> {
            EnchantedVerticalSlabsLogging.info("[Dynamic Datapack]: Errors occurred while running datagenerators");
            exception.printStackTrace();
            throw new RuntimeException(exception);
        });
    }
}
