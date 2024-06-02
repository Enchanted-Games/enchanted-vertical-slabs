package games.enchanted.verticalslabs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class OxidizableVerticalSlab extends CombinableVerticalSlabBlock implements WeatheringCopper {
    private final WeatherState oxidizationLevel;

    public OxidizableVerticalSlab(WeatherState oxidizationLevel, BlockBehaviour.Properties settings) {
        super(settings);
        this.oxidizationLevel = oxidizationLevel;
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.tick(state, world, pos, random);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }

    @Override
    public @NotNull WeatherState getAge() {
        return this.oxidizationLevel;
    }
}
