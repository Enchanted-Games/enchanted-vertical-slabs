package games.enchanted.verticalslabs.mixin.resources;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModelManager.class)
public abstract class ModelManagerMixin {
    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V", remap = false),
        method = {"method_65752", "lambda$createBlockStateToModelDispatch$19"}
    )
    // suppresses the "Missing model for variant" warnings if the enchanted vertical slabs dynamic resourcepack hasn't been initialised yet
    private static void evs$suppressMissingModelWarningsConditionally(Logger instance, String logMessage, Object object, Operation<Void> original) {
        if(!(object instanceof BlockState state)) {
            original.call(instance, logMessage, object);
            return;
        }
        if(!DynamicResourcePackManager.INSTANCE.hasBeenInitialised() && state.getBlock() instanceof DynamicVerticalSlabBlock) {
            return;
        }
        original.call(instance, logMessage, object);
    }
}
