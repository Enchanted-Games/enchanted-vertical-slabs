package games.enchanted.verticalslabs.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Tool.class)
public abstract class ToolMixin {
    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/core/HolderSet;)Z"),
        method = {"isCorrectForDrops", "getMiningSpeed"}
    )
    public boolean evs$overrideBlockCheckForVerticalSlabs(BlockState instance, HolderSet<Block> holderSet, Operation<Boolean> original) {
        if(instance.getBlock() instanceof DynamicVerticalSlabBlock dynamicSlabBlock) {
            return dynamicSlabBlock.getRegularSlabBlock().defaultBlockState().is(holderSet);
        }
        return original.call(instance, holderSet);
    }
}
