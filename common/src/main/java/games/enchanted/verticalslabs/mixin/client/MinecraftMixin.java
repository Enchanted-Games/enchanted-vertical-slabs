package games.enchanted.verticalslabs.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.ui.EVSResourceGenerationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.function.Function;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;reverse(Ljava/util/List;)Ljava/util/List;", remap = false),
        method = "buildInitialScreens"
    )
    private List<Function<Runnable, Screen>> evs$addWelcomeScreen(List<Function<Runnable, Screen>> list, Operation<List<Function<Runnable, Screen>>> original) {
        if(EVSResourceGenerationScreen.shouldDisplayOnGameLoad()) {
            list.add(EVSResourceGenerationScreen::create);
        }
        return original.call(list);
    }
}