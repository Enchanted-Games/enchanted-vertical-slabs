package games.enchanted.verticalslabs.platform.services;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

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

    /**
     * Makes a block flammable
     *
     * @param block the block to make flammable
     * @param burnTime the time in ticks for the block to burn
     * @param spread the change the block will catch fire
     */
    void addFlammableBlock(Block block, int burnTime, int spread);

    /**
     * Returns a Path to a resource in the current mod JAR
     *
     * @param strings elements of the path
     */
    @Nullable Path getResourcePathFromModJar(String... strings);

    /**
     * This is used to get Enchanted Vertical Slabs' creative tabs to register after other mods on Fabric. No-op on NeoForge
     */
    void buildCreativeTabs();
}