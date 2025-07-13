package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.block.vertical_slab.BaseVerticalSlabBlock;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Block.class)
public class BlockMixin {
    @ModifyVariable(
        at = @At("HEAD"),
        method = "shouldRenderFace",
        ordinal = 0,
        argsOnly = true
    )
    private static BlockState evs$modifyCurrentState(BlockState value) {
        if(value.getBlock() instanceof DynamicVerticalSlabBlock dynamicVerticalSlabBlock && !value.getValue(BaseVerticalSlabBlock.SINGLE)) {
            return dynamicVerticalSlabBlock.getRegularSlabDoubleState();
        }
        return value;
    }

    @ModifyVariable(
        at = @At("HEAD"),
        method = "shouldRenderFace",
        ordinal = 1,
        argsOnly = true
    )
    private static BlockState evs$modifyNeighbouringState(BlockState value) {
        if(value.getBlock() instanceof DynamicVerticalSlabBlock dynamicVerticalSlabBlock && !value.getValue(BaseVerticalSlabBlock.SINGLE)) {
            return dynamicVerticalSlabBlock.getRegularSlabDoubleState();
        }
        return value;
    }
}
