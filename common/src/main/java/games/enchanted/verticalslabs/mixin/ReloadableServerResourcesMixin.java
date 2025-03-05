package games.enchanted.verticalslabs.mixin;

import net.minecraft.commands.Commands;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.flag.FeatureFlagSet;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Debug(export = true)
@Mixin(ReloadableServerResources.class)
public class ReloadableServerResourcesMixin {

    @Inject(
        at = @At(value = "TAIL"),
        method = "loadResources(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/core/LayeredRegistryAccess;Ljava/util/List;Lnet/minecraft/world/flag/FeatureFlagSet;Lnet/minecraft/commands/Commands$CommandSelection;ILjava/util/concurrent/Executor;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"
    )
    private static void initialiseDynamicDatapack(ResourceManager resourceManager, LayeredRegistryAccess<RegistryLayer> p_335667_, List<Registry.PendingTags<?>> p_363739_, FeatureFlagSet p_250212_, Commands.CommandSelection p_249301_, int p_251126_, Executor p_249136_, Executor p_249601_, CallbackInfoReturnable<CompletableFuture<ReloadableServerResources>> cir) {
        Optional<Resource> resource = resourceManager.getResource(ResourceLocation.fromNamespaceAndPath("minecraft", "tags/block/mineable/pickaxe.json"));
        if(resource.isPresent()) {
            try {
                final String[] file;
                try (BufferedReader stream = resource.get().openAsReader()) {
                    file = new String[]{""};
                    stream.lines().forEach((str) -> file[0] += (str+ "\n"));
                }
                System.out.println(file[0]);
//                for (int i = 0; i < DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.size(); i++) {
//                    String newPath = DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.get(i).getNamespace() + "/vertical_" + DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.get(i).getPath();
//                    DynamicResourcePack.INSTANCE.addBlockstate(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, newPath), file[0]);
//                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("no tag");
        }
    }
}
