package games.enchanted.verticalslabs.mixin.resources;

import com.llamalad7.mixinextras.sugar.Local;
import games.enchanted.verticalslabs.block.vertical_slab.DynamicVerticalSlabBlock;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.mixin.invoker.SingleItemRecipeInvoker;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SelectableRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.block.SlabBlock;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Debug(export = true)
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Inject(
        at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"),
        method = {"lambda$finalizeRecipeLoading$6", "method_64989"}
    )
    private static void evs$appendStonecutterRecipesForVerticalSlabs(List<RecipeManager.IngredientCollector> ingredientCollectors, FeatureFlagSet enabledFeatures, List<SelectableRecipe.SingleInputEntry<StonecutterRecipe>> stonecutterRecipes, RecipeHolder<?> recipeHolder, CallbackInfo ci, @Local StonecutterRecipe stonecutterRecipe) {
        if(!(((SingleItemRecipeInvoker) stonecutterRecipe).evs$getResult().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof SlabBlock slabBlock)) return;

        DynamicVerticalSlabBlock verticalSlabBlock = DynamicVerticalSlabs.NORMAL_TO_VERTICAL_SLAB_MAP.get(slabBlock);
        if(verticalSlabBlock == null) return;

        ResourceLocation verticalSlabLocation = RegistryHelpers.getLocationFromBlock(verticalSlabBlock);
        StonecutterRecipe verticalSlabRecipe = new StonecutterRecipe(verticalSlabLocation.toString(), ((SingleItemRecipeInvoker) stonecutterRecipe).evs$getInput(), new ItemStack(verticalSlabBlock, 2));
        stonecutterRecipes.add(new SelectableRecipe.SingleInputEntry(
            verticalSlabRecipe.input(),
            new SelectableRecipe(
                verticalSlabRecipe.resultDisplay(),
                Optional.of(new RecipeHolder(ResourceKey.create(Registries.RECIPE, verticalSlabLocation.withSuffix("_stonecutting_recipe")), verticalSlabRecipe))
            )
        ));
    }
}
