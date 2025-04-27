package games.enchanted.verticalslabs.ui;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class EVSWelcomeScreen extends GenericMessageScreen {
    private static boolean hasInitialisedResources = false;

    private final Runnable onCloseCallback;
    private int age;

    EVSWelcomeScreen(Component message, Runnable onClose) {
        super(message);
        onCloseCallback = onClose;
    }

    @Override
    protected void init() {
        super.init();
        EnchantedVerticalSlabsLogging.info("Welcome screen initialised");
    }

    protected void initResources() {
        if(DynamicVerticalSlabs.newSlabsSinceLastLaunch() && !hasInitialisedResources) {
            DynamicResourcePackManager.INSTANCE.addReloadCallback(() -> Minecraft.getInstance().reloadResourcePacks());
            DynamicResourcePackManager.INSTANCE.initialiseInternal();

            DynamicDataPackManager.INSTANCE.initialiseInternal();
            hasInitialisedResources = true;
        }
    }

    @Override
    public void tick() {
        super.tick();
        age++;
        if(!hasInitialisedResources && age >= 200) {
            initResources();
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return hasInitialisedResources || !DynamicVerticalSlabs.newSlabsSinceLastLaunch();
    }

    @Override
    public void onClose() {
        super.onClose();
        onCloseCallback.run();
    }

    public static boolean shouldDisplayWelcomeScreen() {
        return DynamicVerticalSlabs.newSlabsSinceLastLaunch();
    }

    public static Screen create(Runnable onCloseCallback) {
        return new EVSWelcomeScreen(Component.literal("message"), onCloseCallback);
    }
}
