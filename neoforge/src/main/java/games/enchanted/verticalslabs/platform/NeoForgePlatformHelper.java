package games.enchanted.verticalslabs.platform;

import games.enchanted.verticalslabs.NeoForgeEntrypoint;
import games.enchanted.verticalslabs.dynamic.datagen.provider.CopperDatamapProvider;
import games.enchanted.verticalslabs.platform.services.PlatformHelperInterface;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NeoForgePlatformHelper implements PlatformHelperInterface {
    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Path getMinecraftDirectory() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public @Nullable Path getResourcePathFromModJar(String... strings) {
        return NeoForgeEntrypoint.CONTAINER.getModInfo().getOwningFile().getFile().findResource(strings);
    }

    @Override
    public void addWaxableBlockPair(Block unwaxed, Block waxed) {
        CopperDatamapProvider.WAXABLE_PAIRS.put(unwaxed, waxed);
    }

    @Override
    public void addWeatheringBlockPair(Block less, Block more) {
        CopperDatamapProvider.WEATHERING_PAIRS.put(less, more);
    }

    @Override
    public @NotNull List<DataProvider> getPlatformSpecificClientDataproviders(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return List.of(new CopperDatamapProvider(output, lookupProvider));
    }
}