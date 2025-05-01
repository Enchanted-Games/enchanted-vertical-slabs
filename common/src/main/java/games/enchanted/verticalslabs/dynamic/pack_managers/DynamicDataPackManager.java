package games.enchanted.verticalslabs.dynamic.pack_managers;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.datagen.DynamicDataGenerator;
import games.enchanted.verticalslabs.dynamic.datagen.provider.DynamicBlockLoot;
import games.enchanted.verticalslabs.dynamic.datagen.provider.DynamicBlockRecipeProvider;
import games.enchanted.verticalslabs.dynamic.pack.EVSDynamicResources;
import games.enchanted.verticalslabs.platform.Services;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.server.packs.PackType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DynamicDataPackManager extends PackManager {
    public static DynamicDataPackManager INSTANCE = new DynamicDataPackManager();

    private static final DynamicDataGenerator dataGenerator = new DynamicDataGenerator();

    private DynamicDataPackManager() {}

    @Override
    void initialiseResources() {
        if(DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.isEmpty()) {
            complete(false);
            return;
        }
        EnchantedVerticalSlabsLogging.info("Initialising Dynamic Data Pack");
        EVSDynamicResources.INSTANCE.clearDirectory(PackType.SERVER_DATA);

        HolderLookup.Provider lookup = VanillaRegistries.createLookup();
        Path outputFolder = Path.of("");
        dataGenerator.addProvider(DynamicBlockLoot.getProvider(new PackOutput(outputFolder), CompletableFuture.completedFuture(lookup)));
        dataGenerator.addProvider(new DynamicBlockRecipeProvider.Runner(new PackOutput(outputFolder), CompletableFuture.completedFuture(lookup)));

        List<DataProvider> platformProviders = Services.PLATFORM.getPlatformSpecificClientDataproviders(new PackOutput(outputFolder), CompletableFuture.completedFuture(lookup));
        platformProviders.forEach(dataGenerator::addProvider);

        CompletableFuture<?> asyncTasks;
        try {
            asyncTasks = dataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        asyncTasks.thenRun(() -> {
            complete(true, () -> {
                EnchantedVerticalSlabsLogging.info("[Dynamic Datapack]: Async datagenerators completed successfully");
                if(this.hasReloadCallbacks()) {
                    EnchantedVerticalSlabsLogging.info("[Dynamic Datapack]: Reloading datapacks to apply changes");
                }
            });
        })
        .exceptionally((exception) -> {
            EnchantedVerticalSlabsLogging.info("[Dynamic Datapack]: Errors occurred while running datagenerators");
            exception.printStackTrace();
            throw new RuntimeException(exception);
        });
    }
}
