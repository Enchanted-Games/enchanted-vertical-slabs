package games.enchanted.verticalslabs;

import games.enchanted.verticalslabs.platform.RegisterInterface;

public class CommonEntrypoint {
    public static RegisterInterface platformRegister;

    public static void initBeforeRegistration(RegisterInterface platformRegisterI) {
        platformRegister = platformRegisterI;
    }
}