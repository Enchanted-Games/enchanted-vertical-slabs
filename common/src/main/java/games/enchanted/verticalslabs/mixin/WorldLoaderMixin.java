package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.pack.DynamicDataPackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.WorldDataConfiguration;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(MinecraftServer.class)
public class WorldLoaderMixin {

    @Inject(
        at = @At("TAIL"),
        method = "configurePackRepository"
    )
    private static void initialiseDynamicData(PackRepository packRepository, WorldDataConfiguration initialDataConfig, boolean initMode, boolean safeMode, CallbackInfoReturnable<WorldDataConfiguration> cir) {
        DynamicDataPackManager.initialise();
    }
}
