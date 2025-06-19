package games.enchanted.verticalslabs.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import games.enchanted.verticalslabs.block.VerticalSlabBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FenceBlock.class)
public abstract class FenceBlockMixin extends CrossCollisionBlock {
    protected FenceBlockMixin(float nodeWidth, float extensionWidth, float nodeHeight, float extensionHeight, float collisionHeight, Properties properties) {
        super(nodeWidth, extensionWidth, nodeHeight, extensionHeight, collisionHeight, properties);
    }

    @ModifyReturnValue(
        at = @At("RETURN"),
        method = "connectsTo(Lnet/minecraft/world/level/block/state/BlockState;ZLnet/minecraft/core/Direction;)Z"
    )
    // if side block is a vertical slab, connect to it unless vertical slab is "away" from the wall
    private boolean evs$connectFenceToVerticalSlabs(boolean original, BlockState state, boolean sideSolid, Direction direction) {
        if(state.getBlock() instanceof VerticalSlabBlock) {
            boolean slabAwayFromWall = state.getValue(VerticalSlabBlock.FACING) == direction.getOpposite();
            return original || !(state.getValue(VerticalSlabBlock.SINGLE) && slabAwayFromWall);
        }
        return original;
    }
}
