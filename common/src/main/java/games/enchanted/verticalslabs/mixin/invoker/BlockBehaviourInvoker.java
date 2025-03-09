package games.enchanted.verticalslabs.mixin.invoker;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(net.minecraft.world.level.block.state.BlockBehaviour.class)
public interface BlockBehaviourInvoker {
    @Invoker("skipRendering")
    boolean evs$invoke$skipRendering(BlockState state, BlockState adjacentState, Direction direction);
}