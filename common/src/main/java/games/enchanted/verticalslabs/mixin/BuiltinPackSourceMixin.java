package games.enchanted.verticalslabs.mixin;

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
    private void listBundledPacks(Consumer<Pack> packConsumer, CallbackInfo ci) {
        packConsumer.accept(enchanted_vertical_slabs$createDynamicPack(PackType.CLIENT_RESOURCES));
        packConsumer.accept(enchanted_vertical_slabs$createDynamicPack(PackType.SERVER_DATA));
    }

    @Unique
    @Nullable
    private Pack enchanted_vertical_slabs$createDynamicPack(PackType type) {
        return Pack.readMetaAndCreate(EVSDynamicResources.INSTANCE.location(), EVSDynamicResources.INSTANCE.getResourcesSupplier(), type, EVSDynamicResources.PACK_SELECTION_CONFIG);
    }
}
