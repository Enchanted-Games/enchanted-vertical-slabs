package games.enchanted.verticalslabs.dynamic.datagen.provider;

import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabsManager;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class DynamicBlockRecipeProvider extends RecipeProvider {
    public DynamicBlockRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        for (DynamicSlab slab : DynamicVerticalSlabsManager.DYNAMIC_SLAB_BLOCKS) {
            Block regularSlab = RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation());
            Block verticalSlab = RegistryHelpers.getBlockFromLocation(slab.getVerticalSlabLocation());
            Optional<ResourceLocation> regularBlockLocation = slab.getRegularBlockLocation();

            @Nullable Block regularBlock;
            regularBlock = regularBlockLocation.map(RegistryHelpers::getBlockFromLocation).orElse(null);

            itemIntoOtherItem(regularSlab, verticalSlab, 1, regularBlock == null ? regularSlab : regularBlock);
        }
    }

    protected void itemIntoOtherItem(@NotNull ItemLike input, @NotNull ItemLike result, int amount, @NotNull ItemLike unlockedBy) {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, result, amount)
            .requires(input)
            .group(RegistryHelpers.getLocationFromItem(result.asItem()).toString())
            .unlockedBy(RecipeProvider.getHasName(unlockedBy), this.has(unlockedBy))
        .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(packOutput, completableFuture);
        }

        protected @NotNull RecipeProvider createRecipeProvider(@NotNull HolderLookup.Provider lookup, @NotNull RecipeOutput recipeOutput) {
            return new DynamicBlockRecipeProvider(lookup, recipeOutput);
        }

        public @NotNull String getName() {
            return "Enchanted Vertical Slabs Dynamic Recipes";
        }
    }
}
