package games.enchanted.verticalslabs.mixin.resource;

import com.mojang.datafixers.DataFixer;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicDataPackManager;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerInterface;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.Proxy;

@Mixin({IntegratedServer.class})
public abstract class ClientServersMixin extends MinecraftServer implements ServerInterface {
    public ClientServersMixin(Thread serverThread, LevelStorageSource.LevelStorageAccess storageSource, PackRepository packRepository, WorldStem worldStem, Proxy proxy, DataFixer fixerUpper, Services services, ChunkProgressListenerFactory progressListenerFactory) {
        super(serverThread, storageSource, packRepository, worldStem, proxy, fixerUpper, services, progressListenerFactory);
    }

    @Inject(
        at = @At("TAIL"),
        method = "initServer"
    )
    public void evs$checkAndReloadIfDynamicDataPackRequiresIt(CallbackInfoReturnable<Boolean> cir) {
        if(DynamicDataPackManager.INSTANCE.requiresReloadToApply()) {
            reloadResources(getPackRepository().getSelectedIds());
            DynamicDataPackManager.INSTANCE.triggeredReload();
        }
    }
}
