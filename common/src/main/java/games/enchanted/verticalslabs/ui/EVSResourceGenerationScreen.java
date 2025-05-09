package games.enchanted.verticalslabs.ui;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import games.enchanted.verticalslabs.ui.widget.TextBoxWidget;
import games.enchanted.verticalslabs.util.ResourcepackUtil;
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
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public class EVSResourceGenerationScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui.enchanted-vertical-slabs.resource_gen.title").withStyle(Style.EMPTY.withBold(true));
    private static final Component MESSAGE = Component.translatable("gui.enchanted-vertical-slabs.resource_gen.message");
    private static final Component INFO = Component.translatable("gui.enchanted-vertical-slabs.resource_gen.info").withColor(0xB0ADB4);
    private static final String WAITING_KEY = "gui.enchanted-vertical-slabs.resource_gen.gui.enchanted-vertical-slabs.resource_gen.waiting";
    private static final String CREATED_RESOURCE_KEY = "gui.enchanted-vertical-slabs.resource_gen.created_resource";
    private static final String DISABLING_PACKS_KEY = "gui.enchanted-vertical-slabs.resource_gen.disabling_packs";
    private static final String ENABLING_PACKS_KEY = "gui.enchanted-vertical-slabs.resource_gen.enabling_packs";
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

    private int generationStep = 0;
    private int closeAtTicks = -1;
    private boolean loadFinished = false;
    private float currentProgress = 0;

    @Nullable private Collection<String> currentlySelectedPacks = null;
    private Component statusText = Component.translatable(WAITING_KEY);

    private final HeaderAndFooterLayout headerAndFooterLayout = new HeaderAndFooterLayout(this, 50, 50);
    private final LinearLayout contentsFlow = LinearLayout.vertical().spacing(8);

    EVSResourceGenerationScreen(Component message, Runnable onClose) {
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
    public void tick() {
        super.tick();
        Overlay activeOverlay = Minecraft.getInstance().getOverlay();
        if(activeOverlay == null || !activeOverlay.isPauseScreen()) {
            this.age++;
            generateResourcesStep(15);
        }

        if(this.age == 1) {
            repositionElements();
        }

        if(this.closeAtTicks > -1 && this.age == this.closeAtTicks) {
            onClose();
        }
    }

    private void generateResourcesStep(int startAge) {
        final int waitBetweenSteps = 10;

        if(this.age < startAge) return;

        if(generationStep == 0) {
            EnchantedVerticalSlabsLogging.info("[Resource Generation]: Temporarily disabling resourcepacks...");
            setStatusText(Component.translatable(DISABLING_PACKS_KEY));
            currentlySelectedPacks = ResourcepackUtil.removeAllResourcepacks();

            this.currentProgress += 0.3334f;
            this.generationStep = 1;
        }
        if(generationStep == 1 && this.age > startAge + waitBetweenSteps) {
            EnchantedVerticalSlabsLogging.info("[Resource Generation]: Generating resources...");
            setStatusText(Component.translatableWithFallback(CREATED_RESOURCE_KEY, "created: %s", "asset/will/be/here.json"));
            initResources(currentlySelectedPacks == null ? Collections.emptyList() : currentlySelectedPacks);

            this.currentProgress += 0.3333f;
            generationStep = 2;
        }
    }

    private synchronized void setStatusText(Component text) {
        statusText = text;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderProgressBar(guiGraphics, partialTicks);
    }

    protected float getProgress(float partialTicks) {
        return currentProgress;
    }

    protected void renderProgressBar(GuiGraphics guiGraphics, float partialTicks) {
        int x = (this.width / 2) - (PROGRESS_BAR_WIDTH / 2);
        int y = (this.height / 2) + 40;
        int fillWidth = Math.round((float) PROGRESS_BAR_WIDTH * this.getProgress(partialTicks));
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
            statusText,
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

    protected void initResources(Collection<String> packsToReEnable) {
        if(DynamicVerticalSlabs.newSlabsSinceLastLaunch() && !initResourcesCalled) {
            DynamicResourcePackManager.INSTANCE.addCompletionCallback(() -> {
                DynamicDataPackManager.INSTANCE.addCompletionCallback(() -> resourceLoadFinished(packsToReEnable));
                DynamicDataPackManager.INSTANCE.initialiseInternal();
            });
            DynamicResourcePackManager.INSTANCE.initialiseInternal();
        } else {
            this.currentProgress += 0.3333f;
            resourceLoadFinished(packsToReEnable);
        }
        initResourcesCalled = true;
    }

    protected void resourceLoadFinished(Collection<String> packsToReEnable) {
        EnchantedVerticalSlabsLogging.info("[Resource Generation]: Generation finished! Re-enabling resourcepacks and applying changes");
        setStatusText(Component.translatable(ENABLING_PACKS_KEY));

        LinearLayout horizontalButtonLayout = this.headerAndFooterLayout.addToFooter(LinearLayout.horizontal().spacing(8));
        Button buttonWidget = horizontalButtonLayout.addChild(
            Button.builder(CommonComponents.GUI_PROCEED, (button) -> this.onClose()).build()
        );
        this.addRenderableWidget(buttonWidget);

        ResourcepackUtil.reEnableResourcepacks(packsToReEnable);

        repositionElements();

        this.loadFinished = true;
        this.closeAtTicks = this.age + 12;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return this.loadFinished || !DynamicVerticalSlabs.newSlabsSinceLastLaunch();
    }

    @Override
    public void onClose() {
        super.onClose();
        onCloseCallback.run();
    }

    public static boolean shouldDisplayOnGameLoad() {
        return DynamicVerticalSlabs.newSlabsSinceLastLaunch();
    }

    public static Screen create(Runnable onCloseCallback) {
        return new EVSResourceGenerationScreen(Component.literal("message"), onCloseCallback);
    }
}
