package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.registry.VerticalSlabBlocks;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;

public class FuelItems {
    public static void registerFuelItems() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            ResolvableInt woodTicks = new ResolvableInt.Constant(300);
            ResolvableInt bambooTicks = new ResolvableInt.Constant(150);
            ResolvableFloat standardSpeed = new ResolvableFloat.Constant(1.0f);

            CookingFuel woodFuel = new CookingFuel(woodTicks, standardSpeed);
            CookingFuel bambooFuel = new CookingFuel(bambooTicks, standardSpeed);

            modifyFuel(context, VerticalSlabBlocks.VERTICAL_OAK_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_SPRUCE_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_BIRCH_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_JUNGLE_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_ACACIA_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_DARK_OAK_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_MANGROVE_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_CHERRY_SLAB.blockItem(), woodFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_PALE_OAK_SLAB.blockItem(), woodFuel);

            modifyFuel(context, VerticalSlabBlocks.VERTICAL_BAMBOO_SLAB.blockItem(), bambooFuel);
            modifyFuel(context, VerticalSlabBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.blockItem(), bambooFuel);
        });
    }

    private static void modifyFuel(DefaultItemComponentEvents.ModifyContext context, Item item, CookingFuel fuel) {
        context.modify(item, builder -> builder.set(DataComponents.COOKING_FUEL, fuel));
    }
}