package games.enchanted.verticalslabs.dynamic.datagen.provider;

import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CopperDatamapProvider extends DataMapProvider {
    public static Map<Block, Block> WEATHERING_PAIRS = new HashMap<>();
    public static Map<Block, Block> WAXABLE_PAIRS = new HashMap<>();

    public CopperDatamapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        Builder<Oxidizable, Block> oxidisableBuilder = this.builder(NeoForgeDataMaps.OXIDIZABLES);
        for (Map.Entry<Block, Block> entry : WEATHERING_PAIRS.entrySet()) {
            oxidisableBuilder.add(
                RegistryHelpers.getLocationFromBlock(entry.getKey()),
                new Oxidizable(entry.getValue()),
                false
            );
        }
        oxidisableBuilder.build();

        Builder<Waxable, Block> waxableBuilder = this.builder(NeoForgeDataMaps.WAXABLES);
        for (Map.Entry<Block, Block> entry : WAXABLE_PAIRS.entrySet()) {
            waxableBuilder.add(
                RegistryHelpers.getLocationFromBlock(entry.getKey()),
                new Waxable(entry.getValue()),
                false
            );
        }
        waxableBuilder.build();
    }
}
