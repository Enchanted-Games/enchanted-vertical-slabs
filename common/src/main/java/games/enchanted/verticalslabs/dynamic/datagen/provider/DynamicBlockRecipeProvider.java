package games.enchanted.verticalslabs.dynamic.datagen.provider;

import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DynamicBlockRecipeProvider extends RecipeProvider {
    public DynamicBlockRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        for (DynamicSlab slab : DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS) {
            itemIntoOtherItem(RegistryHelpers.getBlockFromLocation(slab.getOriginalSlabLocation()), RegistryHelpers.getBlockFromLocation(slab.getVerticalSlabLocation()), 1);
        }
    }

    protected void itemIntoOtherItem(@NotNull ItemLike input, @NotNull ItemLike result, int amount) {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, result, amount)
            .requires(input)
            .group(RegistryHelpers.getLocationFromItem(result.asItem()).toString())
            .unlockedBy(RecipeProvider.getHasName(input), this.has(input))
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
