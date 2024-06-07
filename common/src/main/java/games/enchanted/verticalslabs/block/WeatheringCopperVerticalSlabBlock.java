package games.enchanted.verticalslabs.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WeatheringCopperVerticalSlabBlock extends VerticalSlabBlock implements WeatheringCopper {
    public static final MapCodec<WeatheringCopperVerticalSlabBlock> CODEC = RecordCodecBuilder.mapCodec((recordCodecBuilderInstance) ->
        recordCodecBuilderInstance.group(WeatherState.CODEC.fieldOf("weathering_state").forGetter(ChangeOverTimeBlock::getAge), propertiesCodec()).apply(recordCodecBuilderInstance, WeatheringCopperVerticalSlabBlock::new)
    );
    private final WeatherState weatherState;

    public @NotNull MapCodec<WeatheringCopperVerticalSlabBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull WeatherState getAge() {
        return weatherState;
    }

    public WeatheringCopperVerticalSlabBlock(WeatherState weatherState, BlockBehaviour.Properties settings) {
        super(settings);
        this.weatherState = weatherState;
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.changeOverTime(state, world, pos, random);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }
}
