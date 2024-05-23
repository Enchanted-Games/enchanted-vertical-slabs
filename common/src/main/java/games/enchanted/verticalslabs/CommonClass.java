package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.RegisterInterface;
import games.enchanted.verticalslabs.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CommonClass {
    public static void init(RegisterInterface platformRegister) {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        platformRegister.register(BuiltInRegistries.BLOCK.key(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)), new ResourceLocation(Constants.MOD_ID, "testblock"));
    }
}