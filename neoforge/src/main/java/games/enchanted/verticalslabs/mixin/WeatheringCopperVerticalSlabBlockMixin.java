package games.enchanted.verticalslabs.mixin;

import games.enchanted.verticalslabs.block.SpecialBlockMaps;
import games.enchanted.verticalslabs.block.vertical_slab.BaseVerticalSlabBlock;
import games.enchanted.verticalslabs.block.vertical_slab.WeatheringCopperVerticalSlabBlock;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(WeatheringCopperVerticalSlabBlock.class)
public class WeatheringCopperVerticalSlabBlockMixin extends BaseVerticalSlabBlock implements WeatheringCopper, IBlockExtension {
    @Final @Shadow
    private WeatherState weatherState;

    private WeatheringCopperVerticalSlabBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull WeatherState getAge() {
        return weatherState;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state, UseOnContext context, @NotNull ItemAbility itemAbility, boolean simulate) {
        if (!context.getItemInHand().canPerformAction(itemAbility)) {
            return null;
        }
        else if (ItemAbilities.AXE_WAX_OFF == itemAbility && SpecialBlockMaps.WAXABLE_BLOCKS != null) {
            return Optional.ofNullable(
                SpecialBlockMaps.WAXABLE_BLOCKS.get(state.getBlock())
            ).map((blockx) ->
                blockx.withPropertiesOf(state)
            ).orElse(null);
        }
        else if (ItemAbilities.AXE_SCRAPE == itemAbility) {
            return WeatheringCopper.getPrevious(state).orElse(null);
        }

        return null;
    }
}
