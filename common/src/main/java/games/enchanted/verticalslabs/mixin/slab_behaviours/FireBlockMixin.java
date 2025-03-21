package games.enchanted.verticalslabs.mixin.slab_behaviours;

import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = FireBlock.class, priority = 800)
public abstract class FireBlockMixin {
    @ModifyVariable(
        at = @At("HEAD"),
        method = {"getBurnOdds", "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I"},
        argsOnly = true,
        ordinal = 0
    )
    public BlockState evs$redirectBurnAndIgniteOddsCallsForVerticalSlabs(BlockState originalState) {
        Block regularSlabBlock = evs$tryGetFromDynamicSlabBlock(originalState);
        if(regularSlabBlock == null) {
            return originalState;
        }
        return regularSlabBlock.defaultBlockState();
    }

    @Unique private @Nullable Block evs$tryGetFromDynamicSlabBlock(BlockState state) {
        if (!(state.getBlock() instanceof DynamicVerticalSlabBlock dynamicSlabBlock)) return null;
        return dynamicSlabBlock.getRegularSlabBlock();
    }
}
