package games.enchanted.verticalslabs.datagen;

import games.enchanted.verticalslabs.block.ModBlocks;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends RecipeProvider {
    public ModRecipesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        // Register your recipes here.
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.PETRIFIED_OAK_SLAB)
            .pattern("a")
            .define('a', ModBlocks.VERTICAL_OAK_SLAB.getBlockItem())
            .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.VERTICAL_OAK_SLAB.getBlock()) )
            .save(output);
    }
}