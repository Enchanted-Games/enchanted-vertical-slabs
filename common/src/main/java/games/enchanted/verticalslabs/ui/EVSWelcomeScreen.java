package games.enchanted.verticalslabs.ui;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import games.enchanted.verticalslabs.ui.widget.TextBoxWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class EVSWelcomeScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui.enchanted-vertical-slabs.welcome.title");
    private static final Component MESSAGE = Component.translatable("gui.enchanted-vertical-slabs.welcome.message");
    private static final Component INFO = Component.translatable("gui.enchanted-vertical-slabs.welcome.info").withColor(0xB0ADB4);
    private static final String CREATED_RESOURCE_KEY = "gui.enchanted-vertical-slabs.welcome.created_resource";
    private static final ResourceLocation LOADING_ICON = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "loading");
    private static final int LOADING_ICON_SIZE = 32;
    private static final ResourceLocation PROGRESS_BAR_BORDER_SPRITE = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "welcome_screen/progress_bar_border");
    private static final ResourceLocation PROGRESS_BAR_FILL_SPRITE = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE,  "welcome_screen/progress_bar_fill");
    private static final int PROGRESS_BAR_WIDTH = 300;
    private static final int PROGRESS_BAR_HEIGHT = 6;
    private static final int PROGRESS_BAR_BORDER_SIZE = 2;

    private boolean initResourcesCalled = false;

    private final Runnable onCloseCallback;
    private int age;
    private int closeAtTicks = -1;

    private final HeaderAndFooterLayout headerAndFooterLayout = new HeaderAndFooterLayout(this, 50, 50);
    private final LinearLayout contentsFlow = LinearLayout.vertical().spacing(8);

    EVSWelcomeScreen(Component message, Runnable onClose) {
        super(message);
        onCloseCallback = onClose;
    }

    @Override
    protected void init() {
        super.init();
        EnchantedVerticalSlabsLogging.info("Welcome screen initialised");

        this.headerAndFooterLayout.addTitleHeader(TITLE, this.font);

        this.contentsFlow.addChild(
            new TextBoxWidget(300, MutableComponent.create(MESSAGE.getContents()).append(Component.literal("\n")).append(INFO), this.font)
        );

        this.headerAndFooterLayout.visitWidgets(this::addRenderableWidget);
        this.contentsFlow.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    @Override
    protected void repositionElements() {
        this.headerAndFooterLayout.arrangeElements();

        this.contentsFlow.setY(64);
        this.contentsFlow.setX((this.width / 2) - (contentsFlow.getWidth() / 2));
        this.contentsFlow.arrangeElements();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderProgressBar(guiGraphics, partialTicks);
    }

    protected float getProgress(float partialTicks) {
        return Mth.lerp(partialTicks, (float) this.age / 100, (float) (this.age + 1) / 100);
    }

    protected void renderProgressBar(GuiGraphics guiGraphics, float partialTicks) {
        int x = (this.width / 2) - (PROGRESS_BAR_WIDTH / 2);
        int y = (this.height / 2) + 40;
        int fillWidth = Mth.ceil((float) PROGRESS_BAR_WIDTH * Math.min(this.getProgress(partialTicks), 1f));
        int height = PROGRESS_BAR_HEIGHT;

        guiGraphics.blitSprite(
            RenderType::guiTextured,
            LOADING_ICON,
            (this.width / 2) - (LOADING_ICON_SIZE  / 2) - 3,
            y - LOADING_ICON_SIZE,
            LOADING_ICON_SIZE,
            LOADING_ICON_SIZE
        );

        guiGraphics.drawCenteredString(
            this.font,
            Component.translatableWithFallback(CREATED_RESOURCE_KEY, "created: ", "enchanted-vertical-slabs/models/item/acacia_slab.json"),
            this.width / 2,
            y + 3 + PROGRESS_BAR_HEIGHT + PROGRESS_BAR_BORDER_SIZE,
            0xffffff
        );

        guiGraphics.blitSprite(
            RenderType::guiTextured,
            PROGRESS_BAR_FILL_SPRITE,
            x - PROGRESS_BAR_BORDER_SIZE,
            y - PROGRESS_BAR_BORDER_SIZE,
            fillWidth + (PROGRESS_BAR_BORDER_SIZE * 2),
            height + (PROGRESS_BAR_BORDER_SIZE * 2)
        );
        guiGraphics.blitSprite(
            RenderType::guiTextured,
            PROGRESS_BAR_BORDER_SPRITE,
            x - PROGRESS_BAR_BORDER_SIZE,
            y - PROGRESS_BAR_BORDER_SIZE,
            PROGRESS_BAR_WIDTH + (PROGRESS_BAR_BORDER_SIZE * 2),
            height + (PROGRESS_BAR_BORDER_SIZE * 2)
        );
    }

    protected void initResources(Runnable completionFunction) {
        if(DynamicVerticalSlabs.newSlabsSinceLastLaunch() && !initResourcesCalled) {
            DynamicResourcePackManager.INSTANCE.addReloadCallback(() -> Minecraft.getInstance().reloadResourcePacks());
            DynamicResourcePackManager.INSTANCE.addCompletionCallback(() -> {
                DynamicDataPackManager.INSTANCE.addCompletionCallback(completionFunction);
                DynamicDataPackManager.INSTANCE.initialiseInternal();
            });
            DynamicResourcePackManager.INSTANCE.initialiseInternal();
        } else {
            resourceLoadFinished();
        }
        initResourcesCalled = true;
    }

    @Override
    public void tick() {
        super.tick();
        Overlay activeOverlay = Minecraft.getInstance().getOverlay();
        if(activeOverlay == null || !activeOverlay.isPauseScreen()) {
            this.age++;
        }
        if(this.age == 1) {
            repositionElements();
        }
        if(!initResourcesCalled && this.age == 40) {
            initResources(this::resourceLoadFinished);
        }
        if(this.closeAtTicks > -1 && this.age == this.closeAtTicks) {
            onClose();
        }
    }

    protected void resourceLoadFinished() {
        LinearLayout horizontalButtonLayout = this.headerAndFooterLayout.addToFooter(LinearLayout.horizontal().spacing(8));
        Button buttonWidget = horizontalButtonLayout.addChild(
            Button.builder(CommonComponents.GUI_PROCEED, (button) -> this.onClose()).build()
        );
        this.addRenderableWidget(buttonWidget);

        repositionElements();

        this.closeAtTicks = this.age + 12;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return initResourcesCalled || !DynamicVerticalSlabs.newSlabsSinceLastLaunch();
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
