package games.enchanted.verticalslabs.mixin.invoker;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface BlockInvoker {
    @Invoker("popExperience")
    void evs$invoke$popExperience(ServerLevel level, BlockPos pos, int amount);
}
