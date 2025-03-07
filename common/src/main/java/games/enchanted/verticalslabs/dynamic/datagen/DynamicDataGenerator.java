package games.enchanted.verticalslabs.dynamic.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

import java.io.IOException;
import java.util.ArrayList;

public class DynamicDataGenerator {
    final ArrayList<DataProvider> providersToRun = new ArrayList<>();

    public DynamicDataGenerator() {
    }

    public void addProvider(DataProvider provider) {
        providersToRun.add(provider);
    }

    public void run() throws IOException {
//        CachedOutput cachedOutput = new DynamicDataOutput();
//        this.providersToRun.forEach((provider) -> {
//            System.out.println(provider.run(cachedOutput));
//        });
    }
}
