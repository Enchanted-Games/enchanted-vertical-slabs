package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.block.VerticalSlabBlock;
import games.enchanted.verticalslabs.block.WeatheringCopperVerticalSlabBlock;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegistryHelpers {
    private static BlockItem registerBlockItem(ResourceLocation location, Block block) {
        Item.Properties settings = new Item.Properties();
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, location);
        settings.useBlockDescriptionPrefix().setId(itemResourceKey);
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.ITEM.key(), () -> new BlockItem(block, settings), location);
    }
    private static Block registerVerticalSlabBlock(ResourceLocation location, BlockBehaviour.Properties blockSettings) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new VerticalSlabBlock(blockSettings), location);
    }
    private static Block registerVerticalSlabBlock(ResourceLocation location, BlockBehaviour.Properties blockSettings, WeatheringCopper.WeatherState oxidationLevel) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new WeatheringCopperVerticalSlabBlock(oxidationLevel, blockSettings), location);
    }

    public static BlockAndItemContainer registerVerticalSlab(String id, BlockBehaviour.Properties blockProperties) {
        return registerVerticalSlab(id, blockProperties, null);
    }

    public static BlockAndItemContainer registerVerticalSlab(String id, BlockBehaviour.Properties blockProperties, WeatheringCopper.WeatherState oxidationLevel) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.MOD_ID, id);
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, location);
        blockProperties.setId(blockResourceKey);

        final Block registeredBlock = oxidationLevel == null ? registerVerticalSlabBlock(location, blockProperties) : registerVerticalSlabBlock(location, blockProperties, oxidationLevel);
        final BlockItem registeredBlockItem = registerBlockItem(location, registeredBlock);
        return new BlockAndItemContainer(registeredBlock, registeredBlockItem);
    }
}
