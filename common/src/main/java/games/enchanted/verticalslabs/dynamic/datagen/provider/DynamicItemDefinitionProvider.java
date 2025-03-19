package games.enchanted.verticalslabs.dynamic.datagen.provider;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import games.enchanted.verticalslabs.dynamic.pack_managers.DynamicResourcePackManager;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DynamicItemDefinitionProvider implements DataProvider {
    static final String modelTemplateString = """
    {
        "display": {
            "thirdperson_righthand": {
                "rotation": [-15.65, -0.34, 45.21],
                "translation": [0, 2.5, 0],
                "scale": [0.375, 0.375, 0.375]
            },
            "firstperson_righthand": {
                "rotation": [-90.03, -2.03, 46.84],
                "scale": [0.4, 0.4, 0.4]
            },
            "ground": {
                "rotation": [90, 0, 0],
                "translation": [0, 3, 1],
                "scale": [0.25, 0.25, 0.25]
            },
            "gui": {
                "rotation": [30, -45, -90],
                "translation": [1.75, -0.75, 0],
                "scale": [0.625, 0.625, 0.625]
            },
            "head": {
                "rotation": [-167, 0, 0],
                "translation": [0, 0.25, 0]
            },
            "fixed": {
                "rotation": [-90, 0, 0],
                "translation": [0, 0, -0.5],
                "scale": [0.5, 0.5, 0.5]
            }
        }
    }
    """;

    final HashMap<ResourceLocation, ClientItem> generatedItemDefinitions = new HashMap<>();
    final HashMap<ResourceLocation, JsonObject> generatedItemModels = new HashMap<>();

    final HashMap<ResourceLocation, DynamicResourcePackManager.VerticalSlabModelLocation> verticalSlabToBlockModel;

    final PackOutput.PathProvider itemDefinitionPathProvider;
    final PackOutput.PathProvider modelPathProvider;

    final JsonObject itemModelTemplate;

    public DynamicItemDefinitionProvider(PackOutput packOutput, HashMap<ResourceLocation, DynamicResourcePackManager.VerticalSlabModelLocation> verticalSlabToBlockModel) {
        this.verticalSlabToBlockModel = verticalSlabToBlockModel;
        this.itemDefinitionPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
        this.modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");

        this.itemModelTemplate = JsonParser.parseString(modelTemplateString).getAsJsonObject();
    }

    @Override
    public @NotNull String getName() {
        return "evs:item_definition_provider";
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        generateDynamicItemDefinitionsAndModels();
        return CompletableFuture.allOf(saveItemModels(output, this.modelPathProvider), saveItemDefinitions(output, this.itemDefinitionPathProvider));
    }

    private void generateDynamicItemDefinitionsAndModels() {
        for (Map.Entry<ResourceLocation, DynamicResourcePackManager.VerticalSlabModelLocation> entry : verticalSlabToBlockModel.entrySet()) {
            ClientItem itemDefinition;
            if(entry.getValue().usesRegularSlabModel()) {
                // handling for vertical slabs that use the normal slab model but rotated
                ResourceLocation model = createRotatedSlabItemModel(entry.getKey(), entry.getValue().location());
                ItemModel.Unbaked unbakedModel = ItemModelUtils.plainModel(model);
                itemDefinition = new ClientItem(unbakedModel, ClientItem.Properties.DEFAULT);
            } else {
                // handling for vertical slabs that have a proper vertical slab model
                itemDefinition = new ClientItem(ItemModelUtils.plainModel(entry.getValue().location()), ClientItem.Properties.DEFAULT);
            }
            generatedItemDefinitions.put(entry.getKey(), itemDefinition);
        }
    }

    private ResourceLocation createRotatedSlabItemModel(ResourceLocation verticalSlabLocation, ResourceLocation verticalSlabBlockModelLocation) {
        JsonObject modelJson = this.itemModelTemplate.deepCopy();
        modelJson.add("parent", new JsonPrimitive(verticalSlabBlockModelLocation.toString()));
        ResourceLocation newModelLocation = ResourceLocation.fromNamespaceAndPath(verticalSlabLocation.getNamespace(), "item/" + verticalSlabLocation.getPath());
        this.generatedItemModels.put(
            newModelLocation,
            modelJson
        );
        return newModelLocation;
    }

    public CompletableFuture<?> saveItemDefinitions(CachedOutput output, PackOutput.PathProvider pathProvider) {
        return DataProvider.saveAll(output, ClientItem.CODEC, pathProvider::json, this.generatedItemDefinitions);
    }

    public CompletableFuture<?> saveItemModels(CachedOutput output, PackOutput.PathProvider pathProvider) {
        return CompletableFuture.allOf(generatedItemModels.entrySet().stream().map((entry) -> {
            Path path = pathProvider.json(entry.getKey());
            return DataProvider.saveStable(output, entry.getValue(), path);
        }).toArray((x$0) -> new CompletableFuture[0]));
    }
}
