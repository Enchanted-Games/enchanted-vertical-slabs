package games.enchanted.verticalslabs.mixin.slab_behaviours;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;getInt(Ljava/lang/Object;)I", remap = false),
        method = {"getBurnOdds", "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I"}
    )
    public int evs$redirectBurnAndIgniteOddsCallsForVerticalSlabs(Object2IntMap<Block> instance, Object object, Operation<Integer> original) {
        Block regularSlabBlock = evs$tryGetFromDynamicSlabBlock(object);
        if(regularSlabBlock == null) return original.call(instance, object);
        return original.call(instance, regularSlabBlock);
    }

    @Unique private @Nullable Block evs$tryGetFromDynamicSlabBlock(Object object) {
        if (!(object instanceof Block)) return null;
        if (!(object instanceof DynamicVerticalSlabBlock dynamicBlockItem)) return null;
        return dynamicBlockItem.getRegularSlabBlock();
    }
}
