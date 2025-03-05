package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.pack.DynamicDataPackManager;
import net.minecraft.server.WorldLoader;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Debug(export = true)
@Mixin(WorldLoader.class)
public class WorldLoaderMixin {

    @Inject(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/WorldLoader$PackConfig;createResourceManager()Lcom/mojang/datafixers/util/Pair;"),
        method = "load"
    )
    private static void initialiseDynamicData(WorldLoader.InitConfig initConfig, WorldLoader.WorldDataSupplier<?> worldDataSupplier, WorldLoader.ResultFactory<?, ?> resultFactory, Executor backgroundExecutor, Executor gameExecutor, CallbackInfoReturnable<CompletableFuture<?>> cir) {
        DynamicDataPackManager.initialise();
    }
}
