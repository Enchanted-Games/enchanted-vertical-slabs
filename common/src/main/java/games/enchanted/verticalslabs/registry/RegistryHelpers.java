package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import games.enchanted.verticalslabs.block.VerticalSlabBlock;
import games.enchanted.verticalslabs.block.WeatheringCopperVerticalSlabBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegistryHelpers {
    private static BlockItem registerBlockItem(BlockItemId itemId, Block block) {
        Item.Properties settings = new Item.Properties();
        settings.useBlockDescriptionPrefix().setId(itemId.item());
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.ITEM.key(), () -> new BlockItem(block, settings), itemId.item().identifier());
    }
    private static Block registerVerticalSlabBlock(ResourceKey<Block> id, BlockBehaviour.Properties blockSettings) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new VerticalSlabBlock(blockSettings), id.identifier());
    }
    private static Block registerVerticalSlabBlock(ResourceKey<Block> id, BlockBehaviour.Properties blockSettings, WeatheringCopper.WeatherState oxidationLevel) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new WeatheringCopperVerticalSlabBlock(oxidationLevel, blockSettings), id.identifier());
    }

    public static BlockAndItemContainer registerVerticalSlab(BlockItemId id, BlockBehaviour.Properties blockProperties) {
        return registerVerticalSlab(id, blockProperties, null);
    }

    public static BlockAndItemContainer registerVerticalSlab(BlockItemId id, BlockBehaviour.Properties blockProperties, WeatheringCopper.WeatherState oxidationLevel) {
        blockProperties.setId(id.block());
        final Block registeredBlock = oxidationLevel == null ? registerVerticalSlabBlock(id.block(), blockProperties) : registerVerticalSlabBlock(id.block(), blockProperties, oxidationLevel);

        final BlockItem registeredBlockItem = registerBlockItem(id, registeredBlock);

        return new BlockAndItemContainer(registeredBlock, registeredBlockItem);
    }
}
