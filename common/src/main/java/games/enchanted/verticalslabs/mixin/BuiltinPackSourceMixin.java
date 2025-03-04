package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.DynamicResourcePack;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
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
    private void listBundledPacks(Consumer<Pack> packConsumer, CallbackInfo ci) {
        packConsumer.accept(Pack.readMetaAndCreate(DynamicResourcePack.INSTANCE.location(), DynamicResourcePack.INSTANCE.getResourcesSupplier(), PackType.CLIENT_RESOURCES, DynamicResourcePack.PACK_SELECTION_CONFIG));
    }
}
