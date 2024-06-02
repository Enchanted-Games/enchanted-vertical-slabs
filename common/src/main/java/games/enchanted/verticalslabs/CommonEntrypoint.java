package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.RegisterInterface;
import games.enchanted.verticalslabs.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonEntrypoint {
    public static RegisterInterface platformRegister;
    public static void init(RegisterInterface platformRegisterI) {
        platformRegister = platformRegisterI;

        VerticalSlabsConstants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        VerticalSlabsConstants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));
    }
}