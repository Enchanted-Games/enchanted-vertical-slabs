package games.enchanted.verticalslabs.mixin.invoker;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SingleItemRecipe.class)
public interface SingleItemRecipeInvoker {
    @Invoker("result")
    ItemStack evs$getResult();

    @Invoker("input")
    Ingredient evs$getInput();
}
