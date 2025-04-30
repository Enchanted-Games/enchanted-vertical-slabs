package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.block.WeatheringCopperUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(WeatheringCopper.class)
public interface WeatheringCopperMixin {
    @Inject(
        at = @At("HEAD"),
        method = "getPrevious(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;",
        cancellable = true
    )
    private static void evs$getPrevious(Block block, CallbackInfoReturnable<Optional<Block>> ci){
        Block previous = WeatheringCopperUtil.WEATHERING_PAIRS_INVERSE.get().get(block);
        if(previous != null) {
            ci.setReturnValue(Optional.of(previous));
        }
    }

    @Inject(
        at = @At("HEAD"),
        method = "getFirst(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/Block;",
        cancellable = true
    )
    private static void evs$getFirst(Block block, CallbackInfoReturnable<Block> ci){
        Block first = block;
        Block previous;
        while((previous = WeatheringCopperUtil.WEATHERING_PAIRS_INVERSE.get().get(first)) != null) {
            first = previous;
        }
        if(first != block) {
            ci.setReturnValue(first);
        }
    }

    @Inject(
        at = @At("HEAD"),
        method = "getNext(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;",
        cancellable = true
    )
    private static void evs$getNext(Block block, CallbackInfoReturnable<Optional<Block>> ci){
        Block next = WeatheringCopperUtil.WEATHERING_PAIRS.get(block);
        if(next != null) {
            ci.setReturnValue(Optional.of(next));
        }

    }
}
