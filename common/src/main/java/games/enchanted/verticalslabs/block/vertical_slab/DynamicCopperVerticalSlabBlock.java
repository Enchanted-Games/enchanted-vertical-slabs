package games.enchanted.verticalslabs.block.vertical_slab;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DynamicCopperVerticalSlabBlock extends DynamicVerticalSlabBlock implements WeatheringCopper {
    public static final MapCodec<DynamicCopperVerticalSlabBlock> CODEC = RecordCodecBuilder.mapCodec((recordCodecBuilderInstance) ->
        recordCodecBuilderInstance.group(
            WeatherState.CODEC.fieldOf("weathering_state").forGetter(ChangeOverTimeBlock::getAge),
            propertiesCodec(),
            ResourceLocation.CODEC.fieldOf("regular_slab").forGetter((instance) -> RegistryHelpers.getLocationFromBlock(instance.getRegularSlabBlock()))
        ).apply(recordCodecBuilderInstance, (weatherState, properties, orginalSlabLocation) -> new DynamicCopperVerticalSlabBlock(weatherState, properties, new DynamicSlab(orginalSlabLocation)))
    );
    private final WeatherState weatherState;

    public DynamicCopperVerticalSlabBlock(WeatherState weatherState, BlockBehaviour.Properties settings, DynamicSlab dynamicSlab) {
        super(settings, dynamicSlab);
        this.weatherState = weatherState;
    }

    public @NotNull MapCodec<DynamicCopperVerticalSlabBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull WeatherState getAge() {
        return weatherState;
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.changeOverTime(state, world, pos, random);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }
}
