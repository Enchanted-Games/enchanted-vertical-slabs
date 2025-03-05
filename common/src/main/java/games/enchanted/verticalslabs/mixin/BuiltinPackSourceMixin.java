package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.dynamic.EVSDynamicResources;
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
        packConsumer.accept(Pack.readMetaAndCreate(EVSDynamicResources.INSTANCE.location(), EVSDynamicResources.INSTANCE.getResourcesSupplier(), PackType.SERVER_DATA, EVSDynamicResources.PACK_SELECTION_CONFIG));
    }
}
