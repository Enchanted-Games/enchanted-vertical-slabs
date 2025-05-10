package games.enchanted.verticalslabs.dynamic.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DynamicDataGenerator {
    final ArrayList<DataProvider> providersToRun = new ArrayList<>();

    public DynamicDataGenerator() {
    }

    public void addProvider(DataProvider provider) {
        providersToRun.add(provider);
    }

    public CompletableFuture<?> run() throws IOException {
        return run(null);
    }

    /**
     * Runs all registered data providers asynchronously.
     *
     * @param taskCompletionCallback Ran once per data provider once it has completed
     * @return tasks to run
     * @throws IOException IOException if a data provider fails to write files
     */
    public CompletableFuture<?> run(@Nullable BiConsumer<String, Float> taskCompletionCallback) throws IOException {
        CachedOutput cachedOutput = new DynamicDataOutput();
        List<CompletableFuture<?>> tasks = new ArrayList<>();
        this.providersToRun.forEach((provider) -> tasks.add(
            provider.run(cachedOutput)
            .whenCompleteAsync((result, throwable) -> {
                if(taskCompletionCallback == null) return;
                taskCompletionCallback.accept(provider.getName(), 1f / providersToRun.size());
            })
        ));
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
    }
}
