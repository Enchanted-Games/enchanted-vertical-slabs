package games.enchanted.verticalslabs.mixin.client;

import games.enchanted.verticalslabs.ui.EVSResourceGenerationScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class DebugTitleScreenMixin extends Screen {
    protected DebugTitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(
        at = @At("TAIL"),
        method = "init"
    )
    public void appendButton(CallbackInfo ci) {
        this.addRenderableWidget(Button.builder(Component.literal("evs debug open screen"), (p_344156_) -> this.minecraft.setScreen(EVSResourceGenerationScreen.create(() -> this.minecraft.setScreen(this)))).bounds(this.width / 2 - 100, 0, 140, 20).build());
    }

}
