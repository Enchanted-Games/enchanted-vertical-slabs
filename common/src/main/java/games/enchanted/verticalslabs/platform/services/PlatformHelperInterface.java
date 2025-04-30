package games.enchanted.verticalslabs.platform.services;

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
     * This is used to get Enchanted Vertical Slabs' creative tabs to register after other mods on Fabric. No-op on NeoForge
     */
    void buildCreativeTabs();
}