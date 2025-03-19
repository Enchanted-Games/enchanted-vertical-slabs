package games.enchanted.verticalslabs.mixin.resources;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import net.minecraft.server.WorldLoader;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.concurrent.CompletableFuture;

@Debug(export = true)
@Mixin(WorldLoader.class)
public abstract class WorldLoaderMixin {
    @ModifyReturnValue(
        at = @At(value = "RETURN", ordinal = 0),
        method = "load"
    )
    private static CompletableFuture<?> evs$initialiseDynamicDatapack(CompletableFuture<?> original) {
        original.thenRun(() -> DynamicDataPackManager.INSTANCE.initialiseInternal());
        return original;
    }
}
