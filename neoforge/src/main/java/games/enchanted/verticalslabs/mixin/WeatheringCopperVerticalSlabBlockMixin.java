package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.VerticalSlabsConstants;
import games.enchanted.verticalslabs.block.ModBlocks;
import games.enchanted.verticalslabs.block.SpecialBlockMaps;
import games.enchanted.verticalslabs.block.VerticalSlabBlock;
import games.enchanted.verticalslabs.block.WeatheringCopperVerticalSlabBlock;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Optional;

@Mixin(WeatheringCopperVerticalSlabBlock.class)
public class WeatheringCopperVerticalSlabBlockMixin extends VerticalSlabBlock implements WeatheringCopper, IBlockExtension {
    private WeatheringCopperVerticalSlabBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    public WeatherState getAge() {
        return null;
    }

    @Override
    public BlockState getToolModifiedState(@NotNull BlockState state, UseOnContext context, @NotNull ToolAction toolAction, boolean simulate) {
        if (!context.getItemInHand().canPerformAction(toolAction)) {
            return null;
        }
        else if (ToolActions.AXE_WAX_OFF == toolAction && SpecialBlockMaps.WAXABLE_BLOCKS != null) {
            return Optional.ofNullable(
                SpecialBlockMaps.WAXABLE_BLOCKS.get(state.getBlock())
            ).map((blockx) ->
                blockx.withPropertiesOf(state)
            ).orElse(null);
        }
        else if (ToolActions.AXE_SCRAPE == toolAction) {
            return WeatheringCopper.getPrevious(state).orElse(null);
        }

        return null;
    }
}
