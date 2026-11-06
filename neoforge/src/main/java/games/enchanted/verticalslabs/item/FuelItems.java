package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.registry.VerticalSlabBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class FuelItems {
    public static void onModifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        ResolvableInt woodTicks = new ResolvableInt.Constant(300);
        ResolvableInt bambooTicks = new ResolvableInt.Constant(150);
        ResolvableFloat standardSpeed = new ResolvableFloat.Constant(1.0f);

        CookingFuel woodFuel = new CookingFuel(woodTicks, standardSpeed);
        CookingFuel bambooFuel = new CookingFuel(bambooTicks, standardSpeed);

        modifyFuel(event, VerticalSlabBlocks.VERTICAL_OAK_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_SPRUCE_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_BIRCH_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_JUNGLE_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_ACACIA_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_DARK_OAK_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_MANGROVE_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_CHERRY_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_PALE_OAK_SLAB.blockItem(), woodFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_POPLAR_SLAB.blockItem(), woodFuel);

        modifyFuel(event, VerticalSlabBlocks.VERTICAL_BAMBOO_SLAB.blockItem(), bambooFuel);
        modifyFuel(event, VerticalSlabBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB.blockItem(), bambooFuel);
    }

    private static void modifyFuel(ModifyDefaultComponentsEvent event, Item item, CookingFuel fuel) {
        event.modify(item, (builder, context, item2) -> builder.set(DataComponents.COOKING_FUEL, fuel));
    }
}
