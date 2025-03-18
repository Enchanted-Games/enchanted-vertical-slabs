package games.enchanted.verticalslabs.mixin.resources;

import games.enchanted.verticalslabs.dynamic.pack.EVSDynamicResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.repository.Pack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(BuiltInPackSource.class)
public abstract class BuiltinPackSourceMixin {
    @Inject(
        at = @At("TAIL"),
        method = "listBundledPacks"
    )
    private void evs$listDynamicPacks(Consumer<Pack> packConsumer, CallbackInfo ci) {
        packConsumer.accept(evs$createDynamicPack(PackType.CLIENT_RESOURCES));
        packConsumer.accept(evs$createDynamicPack(PackType.SERVER_DATA));
    }

    @Unique
    @Nullable
    private Pack evs$createDynamicPack(PackType type) {
        return Pack.readMetaAndCreate(EVSDynamicResources.INSTANCE.location(), EVSDynamicResources.INSTANCE.getResourcesSupplier(), type, EVSDynamicResources.PACK_SELECTION_CONFIG);
    }
}
