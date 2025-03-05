package games.enchanted.verticalslabs.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Debug(export = true)
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow public abstract ResourceManager getResourceManager();

    @Inject(
        at = @At(value = "TAIL"),
        method = "reloadResources(Ljava/util/Collection;)Ljava/util/concurrent/CompletableFuture;"
    )
    private void initialiseDynamicDatapack(Collection<String> selectedIds, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        Optional<Resource> resource = getResourceManager().getResource(ResourceLocation.fromNamespaceAndPath("minecraft", "tags/block/mineable/pickaxe.json"));
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