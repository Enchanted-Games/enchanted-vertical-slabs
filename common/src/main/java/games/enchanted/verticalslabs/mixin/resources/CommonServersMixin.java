package games.enchanted.verticalslabs.mixin.resources;

import com.mojang.datafixers.DataFixer;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import net.minecraft.gametest.framework.GameTestServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerInterface;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.Proxy;

@Mixin({DedicatedServer.class, GameTestServer.class})
public abstract class CommonServersMixin extends MinecraftServer implements ServerInterface {
    public CommonServersMixin(Thread serverThread, LevelStorageSource.LevelStorageAccess storageSource, PackRepository packRepository, WorldStem worldStem, Proxy proxy, DataFixer fixerUpper, Services services, ChunkProgressListenerFactory progressListenerFactory) {
        super(serverThread, storageSource, packRepository, worldStem, proxy, fixerUpper, services, progressListenerFactory);
    }

    @Inject(
        at = @At("TAIL"),
        method = "initServer"
    )
    public void evs$checkAndReloadIfDynamicDataPackRequiresIt(CallbackInfoReturnable<Boolean> cir) {
        if(DynamicVerticalSlabs.newSlabsSinceLastLaunch()) {
            DynamicDataPackManager.INSTANCE.addReloadCallback(() -> reloadResources(getPackRepository().getSelectedIds()));
            DynamicDataPackManager.INSTANCE.initialiseInternal();
        }
    }
}
