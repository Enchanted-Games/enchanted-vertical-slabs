package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.pack.DynamicDataPackManager;
import net.minecraft.commands.Commands;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.flag.FeatureFlagSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ReloadableServerResources.class)
public class MinecraftServerMixin {
    @Inject(
        at = @At("HEAD"),
        method = "loadResources(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/core/LayeredRegistryAccess;Ljava/util/List;Lnet/minecraft/world/flag/FeatureFlagSet;Lnet/minecraft/commands/Commands$CommandSelection;ILjava/util/concurrent/Executor;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"
    )
    private static void initialiseDynamicDatapack(ResourceManager p_248588_, LayeredRegistryAccess<RegistryLayer> p_335667_, List<Registry.PendingTags<?>> p_363739_, FeatureFlagSet p_250212_, Commands.CommandSelection p_249301_, int p_251126_, Executor p_249136_, Executor p_249601_, CallbackInfoReturnable<CompletableFuture<ReloadableServerResources>> cir) {
        DynamicDataPackManager.initialise();
    }
}
