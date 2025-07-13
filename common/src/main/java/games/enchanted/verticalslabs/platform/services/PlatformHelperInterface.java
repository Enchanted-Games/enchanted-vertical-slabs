package games.enchanted.verticalslabs.platform.services;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PlatformHelperInterface {
    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /**
     * Gets the base minecraft client or server directory
     */
    Path getMinecraftDirectory();

    /**
     * Returns a Path to a resource in the current mod JAR
     *
     * @param strings elements of the path
     */
    @Nullable Path getResourcePathFromModJar(String... strings);

    /**
     * Registers a pair of blocks waxable with honeycomb
     *
     * @param unwaxed the unwaxed Block
     * @param waxed   the waxed block
     */
    void addWaxableBlockPair(Block unwaxed, Block waxed);

    /**
     * Registers a block that can weather into another block
     *
     * @param less the less weathered block
     * @param more the more weathered block
     */
    void addWeatheringBlockPair(Block less, Block more);

    default @NotNull List<DataProvider> getPlatformSpecificClientDataproviders(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return List.of();
    }
}