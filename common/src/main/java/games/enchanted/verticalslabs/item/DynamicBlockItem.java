package games.enchanted.verticalslabs.item;

import games.enchanted.verticalslabs.config.dynamic.SlabBehaviourFile;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.mixin.accessor.ItemPropertiesAccessor;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import net.minecraft.Util;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DynamicBlockItem extends BlockItem {
    @Nullable private final ResourceLocation ORIGINAL_ITEM_LOCATION;
    private final ResourceLocation REGISTERED_LOCATION;

    public DynamicBlockItem(Block block, Properties properties, DynamicSlab dynamicSlab) {
        super(block, properties);
        ORIGINAL_ITEM_LOCATION = dynamicSlab.getOriginalSlabLocation();
        REGISTERED_LOCATION = ((ItemPropertiesAccessor) properties).evs$getId().location();
    }

    public Optional<ResourceLocation> getOriginalItemLocation() {
        return ORIGINAL_ITEM_LOCATION == null ? Optional.empty() : Optional.of(ORIGINAL_ITEM_LOCATION);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Language lang = Language.getInstance();
        String stackTranslationKey = Util.makeDescriptionId("block", REGISTERED_LOCATION);
        boolean stackHasTranslation = lang.has(stackTranslationKey);
        if(stackHasTranslation) return Component.translatable(stackTranslationKey);

        if(ORIGINAL_ITEM_LOCATION == null) return super.getName(stack);

        return Component.translatableWithFallback(
            "block.enchanted-vertical-slabs.dynamic_slab",
            "block.enchanted-vertical-slabs.dynamic_slab : %s",
            RegistryHelpers.getItemFromLocation(ORIGINAL_ITEM_LOCATION).getName()
        );
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return SlabBehaviourFile.INSTANCE.getUseSeparateVerticalSlabItems();
    }
}
