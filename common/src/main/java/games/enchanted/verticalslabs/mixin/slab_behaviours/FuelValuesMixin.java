package games.enchanted.verticalslabs.mixin.slab_behaviours;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import games.enchanted.verticalslabs.item.DynamicBlockItem;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import it.unimi.dsi.fastutil.objects.Object2IntSortedMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FuelValues.class)
public class FuelValuesMixin {
    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntSortedMap;containsKey(Ljava/lang/Object;)Z", remap = false),
        method = "isFuel"
    )
    public boolean evs$redirectIsFuelCallForVerticalSlabItems(Object2IntSortedMap<Item> instance, Object object, Operation<Boolean> original) {
        ResourceLocation regularSlabLocation = evs$tryGetLocationFromDynamicSlabItem(object);
        if(regularSlabLocation == null) return original.call(instance, object);

        return original.call(instance, RegistryHelpers.getItemFromLocation(regularSlabLocation));
    }

    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntSortedMap;getInt(Ljava/lang/Object;)I", remap = false),
        method = "burnDuration"
    )
    public int evs$redirectBurnDurationCallForVerticalSlabItems(Object2IntSortedMap<Item> instance, Object object, Operation<Integer> original) {
        ResourceLocation regularSlabLocation = evs$tryGetLocationFromDynamicSlabItem(object);
        if(regularSlabLocation == null) return original.call(instance, object);

        return original.call(instance, RegistryHelpers.getItemFromLocation(regularSlabLocation));
    }

    @Unique private @Nullable ResourceLocation evs$tryGetLocationFromDynamicSlabItem(Object object) {
        if (!(object instanceof Item)) return null;
        if (!(object instanceof DynamicBlockItem dynamicBlockItem)) return null;
        if (dynamicBlockItem.getOriginalItemLocation().isEmpty()) return null;
        return dynamicBlockItem.getOriginalItemLocation().get();
    }
}