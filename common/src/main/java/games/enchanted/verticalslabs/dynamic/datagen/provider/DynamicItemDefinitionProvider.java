package games.enchanted.verticalslabs.dynamic.datagen.provider;

import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DynamicItemDefinitionProvider implements DataProvider {
    final HashMap<ResourceLocation, ClientItem> generatedItemDefinitions = new HashMap<>();
    final HashMap<ResourceLocation, DynamicResourcePackManager.VerticalSlabModelLocation> verticalSlabToBlockModel;
    final PackOutput.PathProvider itemDefinitionPathProvider;

    public DynamicItemDefinitionProvider(PackOutput packOutput, HashMap<ResourceLocation, DynamicResourcePackManager.VerticalSlabModelLocation> verticalSlabToBlockModel) {
        this.verticalSlabToBlockModel = verticalSlabToBlockModel;
        this.itemDefinitionPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    public @NotNull String getName() {
        return "evs:item_definition_provider";
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        generateDynamicItemDefinitions();
        return save(output, this.itemDefinitionPathProvider);
    }

    private void generateDynamicItemDefinitions() {
        for (Map.Entry<ResourceLocation, DynamicResourcePackManager.VerticalSlabModelLocation> entry : verticalSlabToBlockModel.entrySet()) {
            ClientItem itemDefinition = new ClientItem(ItemModelUtils.plainModel(entry.getValue().location()), ClientItem.Properties.DEFAULT);
            generatedItemDefinitions.put(entry.getKey(), itemDefinition);
        }
    }

    public CompletableFuture<?> save(CachedOutput output, PackOutput.PathProvider pathProvider) {
        return DataProvider.saveAll(output, ClientItem.CODEC, itemLocation -> pathProvider.json(itemLocation), this.generatedItemDefinitions);
    }
}
