package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.block.WeatheringCopperMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

// this is needed because you cant modify WeatheringCopper.NEXT_BY_BLOCK at all for whatever reason
@Mixin(WeatheringCopper.class)
@SuppressWarnings("unused")
public interface WeatheringCopperMixin {

    @Inject(
        at = @At("HEAD"),
        method = "getPrevious(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;",
        cancellable = true
    )
    private static void getPrevious(Block block, CallbackInfoReturnable<Optional<Block>> ci){
        if(WeatheringCopperMap.MAP != null) {
            Block previous = WeatheringCopperMap.MAP.inverse().get(block);
            if(previous != null) {
                ci.setReturnValue(Optional.of(previous));
            }
        }
    }

    @Inject(
        at = @At("HEAD"),
        method = "getFirst(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/Block;",
        cancellable = true
    )
    private static void getFirst(Block block, CallbackInfoReturnable<Block> ci){
        if(WeatheringCopperMap.MAP != null) {
            Block first = block;
            Block previous;
            while((previous = WeatheringCopperMap.MAP.inverse().get(first)) != null) {
                first = previous;
            }
            if(first != block) {
                ci.setReturnValue(first);
            }
        }
    }

    @Inject(
        at = @At("HEAD"),
        method = "getNext(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;",
        cancellable = true
    )
    private static void getNext(Block block, CallbackInfoReturnable<Optional<Block>> ci){
        if(WeatheringCopperMap.MAP != null) {
            Block next = WeatheringCopperMap.MAP.get(block);
            if(next != null) {
                ci.setReturnValue(Optional.of(next));
            }
        }
    }
}