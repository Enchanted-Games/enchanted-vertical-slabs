package games.enchanted.verticalslabs.mixin;

import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(HoneycombItem.class)
public class HoneyCombItemMixin {
//    @Inject(
//        at = @At("HEAD"),
//        method = "getWaxed(Lnet/minecraft/world/level/block/state/BlockState;)Ljava/util/Optional;",
//        cancellable = true
//    )
//    // make copper blocks waxable with honey comb items
//    private static void getWaxed(BlockState blockState, CallbackInfoReturnable<Optional<BlockState>> cir) {
//        if(SpecialBlockMaps.WAXABLE_BLOCKS != null) {
//            Block waxedBlock = SpecialBlockMaps.WAXABLE_BLOCKS.inverse().get(blockState.getBlock());
//            if(waxedBlock != null) {
//                cir.setReturnValue(Optional.of(waxedBlock.withPropertiesOf(blockState)));
//            }
//        }
//    }
}
