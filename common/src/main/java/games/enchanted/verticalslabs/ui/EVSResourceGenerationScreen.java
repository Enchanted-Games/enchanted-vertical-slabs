package games.enchanted.verticalslabs.ui;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import games.enchanted.verticalslabs.dynamic.resources.ResourceGenerationException;
import games.enchanted.verticalslabs.ui.widget.TextBoxWidget;
import games.enchanted.verticalslabs.util.ResourcepackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
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
    private static final String WAITING_KEY = "gui.enchanted-vertical-slabs.resource_gen.waiting";
    private static final String GENERATING_RESOURCEPACK = "gui.enchanted-vertical-slabs.resource_gen.resourcepack";
    private static final String GENERATING_DATAPACK = "gui.enchanted-vertical-slabs.resource_gen.datapack";
    private static final String DISABLING_PACKS_KEY = "gui.enchanted-vertical-slabs.resource_gen.disabling_packs";
    private static final String ENABLING_PACKS_KEY = "gui.enchanted-vertical-slabs.resource_gen.enabling_packs";
    private static final String RESOURCE_GEN_ERROR = "gui.enchanted-vertical-slabs.resource_gen.error";
    private static final String QUIT = "menu.quit";
    private static final String CONTINUE_WITH_ERRORS = "gui.enchanted-vertical-slabs.resource_gen.button.continue_with_errors";
    private static final String REPORT_ISSUE = "gui.enchanted-vertical-slabs.resource_gen.button.report_issue";

    private static final ResourceLocation LOADING_ICON = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "loading");
    private static final int LOADING_ICON_SIZE = 32;
    private static final ResourceLocation PROGRESS_BAR_BORDER_SPRITE = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE, "resource_generation_screen/progress_bar_border");
    private static final ResourceLocation PROGRESS_BAR_FILL_SPRITE = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_NAMESPACE,  "resource_generation_screen/progress_bar_fill");
    private static final int PROGRESS_BAR_WIDTH = 300;
    private static final int PROGRESS_BAR_HEIGHT = 6;
    private static final int PROGRESS_BAR_BORDER_SIZE = 2;

    private boolean initResourcesCalled = false;

    private final Runnable onCloseCallback;
    private int age;

    private static final float GENERATION_STEP_PROGRESS_INCREASE = 0.25f;
    private int generationStep = 0;
    private int closeAtTicks = -1;
    private boolean loadFinished = false;
    private float currentProgress = 0;

    @Nullable private Collection<String> currentlySelectedPacks = null;
    private Component statusText = Component.translatable(WAITING_KEY);
    @Nullable private Component errorStatusText = null;

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
            generateResourcesStep(7);
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

            this.currentProgress += GENERATION_STEP_PROGRESS_INCREASE;
            this.generationStep = 1;
        }
        if(generationStep == 1 && this.age > startAge + waitBetweenSteps) {
            EnchantedVerticalSlabsLogging.info("[Resource Generation]: Generating resources...");
            initResources(currentlySelectedPacks == null ? Collections.emptyList() : currentlySelectedPacks);

            generationStep = 2;
        }
    }

    private synchronized void setStatusText(Component text) {
        statusText = text;
    }
    private synchronized void setErrorStatusText(Component text) {
        errorStatusText = text;
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
        int y = (this.height / 2) + 13;
        int fillWidth = Math.round((float) PROGRESS_BAR_WIDTH * this.getProgress(partialTicks));
        int height = PROGRESS_BAR_HEIGHT;

        if(errorStatusText != null) y -= 35;

        guiGraphics.drawCenteredString(
            this.font,
            statusText,
            this.width / 2,
            y + 3 + PROGRESS_BAR_HEIGHT + PROGRESS_BAR_BORDER_SIZE,
            0xffffff
        );

        if(errorStatusText != null) {
            guiGraphics.drawWordWrap(
                this.font,
                errorStatusText,
                (this.width / 2) - 200,
                y + 3 + PROGRESS_BAR_HEIGHT + PROGRESS_BAR_BORDER_SIZE + 12,
                400,
                0xffbbbb
            );
            guiGraphics.drawWordWrap(
                this.font,
                Component.translatable(RESOURCE_GEN_ERROR),
                (this.width / 2) - 200,
                y + 3 + PROGRESS_BAR_HEIGHT + PROGRESS_BAR_BORDER_SIZE + 50,
                400,
                0xffffff
            );

            return;
        }

        guiGraphics.blitSprite(
            RenderType::guiTextured,
            LOADING_ICON,
            (this.width / 2) - (LOADING_ICON_SIZE  / 2) - 3,
            y - LOADING_ICON_SIZE,
            LOADING_ICON_SIZE,
            LOADING_ICON_SIZE
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
            DynamicDataPackManager.INSTANCE.addExceptionCallback((e) -> finishedWithErrors(e, packsToReEnable));
            DynamicResourcePackManager.INSTANCE.addExceptionCallback((e) -> finishedWithErrors(e, packsToReEnable));

            DynamicDataPackManager.INSTANCE.addCompletionCallback(() -> {
                DynamicResourcePackManager.INSTANCE.addCompletionCallback(() -> resourceLoadFinished(packsToReEnable));

                setStatusText(Component.translatableWithFallback(GENERATING_RESOURCEPACK, "generating resourcepack [%s]", "Starting thread"));

                class ResourcepackThread extends Thread {
                    @Override
                    public void run() {
                        DynamicResourcePackManager.INSTANCE.initialiseInternal((name, percentageStep) -> {
                            setStatusText(Component.translatableWithFallback(GENERATING_RESOURCEPACK, "generating resourcepack [%s]", name));
                            currentProgress += percentageStep * GENERATION_STEP_PROGRESS_INCREASE;
                        });
                    }
                }
                Thread thread = new ResourcepackThread();
                thread.start();
            });

            setStatusText(Component.translatableWithFallback(GENERATING_DATAPACK, "generating datapack [%s]", "Starting thread"));

            class DatapackThread extends Thread {
                @Override
                public void run() {
                    DynamicDataPackManager.INSTANCE.initialiseInternal((name, percentageStep) -> {
                        setStatusText(Component.translatableWithFallback(GENERATING_DATAPACK, "generating datapack [%s]", name));
                        currentProgress += percentageStep * GENERATION_STEP_PROGRESS_INCREASE;
                    });
                }
            }
            Thread thread = new DatapackThread();
            thread.start();

        } else {
            this.currentProgress += 0.5f;
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

        this.currentProgress += GENERATION_STEP_PROGRESS_INCREASE;
        this.loadFinished = true;
        this.closeAtTicks = this.age + 12;
    }

    protected void finishedWithErrors(ResourceGenerationException exception, Collection<String> packsToReEnable) {
        EnchantedVerticalSlabsLogging.info("[Resource Generation]: Generation did not finish correctly. Re-enabling resourcepacks and showing error screen.");
        ResourcepackUtil.reEnableResourcepacks(packsToReEnable);

        setStatusText(Component.literal(exception.getCauseMessage()));
        setErrorStatusText(Component.literal(exception.getCause().toString()));

        LinearLayout horizontalButtonLayout = this.headerAndFooterLayout.addToFooter(LinearLayout.horizontal().spacing(8));
        Button quitButton = horizontalButtonLayout.addChild(
            Button.builder(Component.translatable(QUIT), (button) -> {
                assert this.minecraft != null;
                this.minecraft.stop();
            }).build()
        );
        this.addRenderableWidget(quitButton);

        Button continueButton = horizontalButtonLayout.addChild(
            Button.builder(Component.translatable(CONTINUE_WITH_ERRORS), (button) -> this.onClose()).build()
        );
        this.addRenderableWidget(continueButton);

        Button reportButton = horizontalButtonLayout.addChild(
            Button.builder(Component.translatable(REPORT_ISSUE), ConfirmLinkScreen.confirmLink(this, EnchantedVerticalSlabsConstants.ISSUE_TRACKER)).build()
        );
        this.addRenderableWidget(reportButton);

        repositionElements();

        this.loadFinished = true;
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
