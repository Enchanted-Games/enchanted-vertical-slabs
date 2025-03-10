package games.enchanted.verticalslabs.registry;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.block.BlockAndItemContainer;
import games.enchanted.verticalslabs.block.vertical_slab.BaseVerticalSlabBlock;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.block.vertical_slab.WeatheringCopperVerticalSlabBlock;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

public class RegistryHelpers {
    public static BlockAndItemContainer registerVerticalSlab(String id, BlockBehaviour.Properties blockProperties) {
        return registerVerticalSlab(id, blockProperties, null);
    }
    public static BlockAndItemContainer registerVerticalSlab(ResourceLocation id, BlockBehaviour.Properties blockProperties) {
        return registerVerticalSlab(id, blockProperties, null);
    }
    public static BlockAndItemContainer registerVerticalSlab(String id, BlockBehaviour.Properties blockProperties, @Nullable WeatheringCopper.WeatherState oxidationLevel) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, id);
        return registerVerticalSlab(location, blockProperties, oxidationLevel);
    }

    public static BlockAndItemContainer registerVerticalSlab(ResourceLocation id, BlockBehaviour.Properties blockProperties, @Nullable WeatheringCopper.WeatherState oxidationLevel) {
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, id);
        blockProperties.setId(blockResourceKey);

        final Block registeredBlock = oxidationLevel == null ? registerVerticalSlabBlock(id, blockProperties) : registerVerticalSlabBlock(id, blockProperties, oxidationLevel);
        final BlockItem registeredBlockItem = registerBlockItem(id, registeredBlock);
        return new BlockAndItemContainer(registeredBlock, registeredBlockItem);
    }


    private static BlockItem registerBlockItem(ResourceLocation location, Block block) {
        Item.Properties settings = new Item.Properties();
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, location);
        settings.useBlockDescriptionPrefix().setId(itemResourceKey);
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.ITEM.key(), () -> new BlockItem(block, settings), location);
    }
    private static Block registerVerticalSlabBlock(ResourceLocation location, BlockBehaviour.Properties blockSettings) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new BaseVerticalSlabBlock(blockSettings), location);
    }
    private static Block registerVerticalSlabBlock(ResourceLocation location, BlockBehaviour.Properties blockSettings, WeatheringCopper.WeatherState oxidationLevel) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new WeatheringCopperVerticalSlabBlock(oxidationLevel, blockSettings), location);
    }


    public static BlockAndItemContainer registerDynamicVerticalSlab(ResourceLocation id, BlockBehaviour.Properties blockProperties, DynamicSlab dynamicSlab) {
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, id);
        blockProperties.setId(blockResourceKey);

        final Block registeredBlock = registerDynamicVerticalSlabBlock(id, blockProperties, dynamicSlab);
        final BlockItem registeredBlockItem = registerBlockItem(id, registeredBlock);
        return new BlockAndItemContainer(registeredBlock, registeredBlockItem);
    }
    private static Block registerDynamicVerticalSlabBlock(ResourceLocation location, BlockBehaviour.Properties blockSettings, DynamicSlab dynamicSlab) {
        return EnchantedVerticalSlabsMod.register(BuiltInRegistries.BLOCK.key(), () -> new DynamicVerticalSlabBlock(blockSettings, dynamicSlab), location);
    }


    public static Block getBlockFromLocation(ResourceLocation location) {
        return BuiltInRegistries.BLOCK.getValue(location);
    }
    public static ResourceLocation getLocationFromBlock(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
