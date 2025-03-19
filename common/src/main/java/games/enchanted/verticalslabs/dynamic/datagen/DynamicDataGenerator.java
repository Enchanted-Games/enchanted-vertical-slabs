package games.enchanted.verticalslabs.dynamic.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DynamicDataGenerator {
    final ArrayList<DataProvider> providersToRun = new ArrayList<>();

    public DynamicDataGenerator() {
    }

    public void addProvider(DataProvider provider) {
        providersToRun.add(provider);
    }

    public CompletableFuture<?> run() throws IOException {
        CachedOutput cachedOutput = new DynamicDataOutput();
        List<CompletableFuture<?>> tasks = new ArrayList<>();
        this.providersToRun.forEach((provider) -> tasks.add(provider.run(cachedOutput)));
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
    }
}
