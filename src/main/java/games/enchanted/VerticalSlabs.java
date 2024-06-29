package games.enchanted;

import games.enchanted.registry.ModFabricRegistries;
import games.enchanted.registry.ModItems;
import games.enchanted.registry.ModItemGroups;
import games.enchanted.registry.ModBlocks;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerticalSlabs implements ModInitializer {

    // mod id
    public static final String MOD_ID = "enchanted-vertical-slabs";
    // logger
    public static final Logger LOGGER = LogManager.getLogger("Enchanted Vertical Slabs");

    @Override
    public void onInitialize() {
        // calls block and item classes to register blocks and items
        ModItems.registerItems();
        ModBlocks.registerBlocks();

        // registers special properties for blocks
        ModFabricRegistries.registerFlammables();
        ModFabricRegistries.registerOxidisables();

        // creates item groups and adds items to vanilla groups
        ModItemGroups.createItemGroups();

        // logs to console that the mod is ready
        LOGGER.info("[evs] Enchanted Vertical Slabs initialized!");
    }
}