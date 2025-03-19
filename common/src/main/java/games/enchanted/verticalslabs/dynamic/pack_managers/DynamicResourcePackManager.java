package games.enchanted.verticalslabs.dynamic.pack_managers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.datagen.DynamicDataGenerator;
import games.enchanted.verticalslabs.dynamic.datagen.provider.DynamicItemDefinitionProvider;
import games.enchanted.verticalslabs.dynamic.pack.EVSDynamicResources;
import games.enchanted.verticalslabs.dynamic.resources.BlockStateFile;
import games.enchanted.verticalslabs.util.DirectionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class DynamicResourcePackManager extends PackManager {
    public static DynamicResourcePackManager INSTANCE = new DynamicResourcePackManager();

    private static final FileToIdConverter BLOCKSTATE_CONVERTER = new FileToIdConverter("blockstates", ".json");
    private static final FileToIdConverter MODEL_CONVERTER = new FileToIdConverter("models", ".json");

    private static final HashMap<ResourceLocation, VerticalSlabModelLocation> verticalSlabToBlockModel = new HashMap<>();

    private static final DynamicDataGenerator dataGenerator = new DynamicDataGenerator();

    private DynamicResourcePackManager() {}

    @Override
    public void initialiseResources() {
        EnchantedVerticalSlabsLogging.info("Initialising Dynamic Resource Pack");

        addBlockstatesAndModels();
        addItemDefinitions();

        CompletableFuture<?> asyncTasks;
        try {
            asyncTasks = dataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        asyncTasks.thenRun(() -> {
            complete(true, () -> {
                EnchantedVerticalSlabsLogging.info("[Dynamic Resourcepack]: Async datagenerators completed successfully");
            });
        })
        .exceptionally((exception) -> {
            EnchantedVerticalSlabsLogging.info("[Dynamic Resourcepack]: Errors occurred while running datagenerators");
            exception.printStackTrace();
            throw new RuntimeException(exception);
        });
    }

    private static void addItemDefinitions() {
        dataGenerator.addProvider(new DynamicItemDefinitionProvider(new PackOutput(Path.of("")), verticalSlabToBlockModel));
    }

    private static void addBlockstatesAndModels() {
        for (DynamicSlab slab : DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS) {
            String blockStateFile = generateBlockStateFileForSlab(slab);
            if(blockStateFile == null) {
                EnchantedVerticalSlabsLogging.error("Failed to generate blockstate definition file for: {}", slab.getVerticalSlabLocation());
                continue;
            };
            EVSDynamicResources.INSTANCE.addBlockstate(slab.getVerticalSlabLocation(), blockStateFile);
        }
    }

    private static @Nullable String generateBlockStateFileForSlab(DynamicSlab slab) {
        Optional<Resource> originalSlabResource = Minecraft.getInstance().getResourceManager().getResource(BLOCKSTATE_CONVERTER.idToFile(slab.getOriginalSlabLocation()));
        if(originalSlabResource.isEmpty()) return null;
        byte[] bytes;
        try {
            bytes = originalSlabResource.get().open().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String originalSlabBlockStateString = new String(bytes, StandardCharsets.UTF_8);
        JsonObject originalSlabBlockStateDecoded = JsonParser.parseString(originalSlabBlockStateString).getAsJsonObject();

        DataResult<BlockStateFile> slabBlockStateFileResult = BlockStateFile.CODEC.parse(JsonOps.INSTANCE, originalSlabBlockStateDecoded);
        if(!slabBlockStateFileResult.isSuccess()) return null;

        BlockStateFile slabBlockStateFile = slabBlockStateFileResult.getOrThrow();
        if(slabBlockStateFile.variants().isPresent()) {
            // file uses variants
            Map<List<BlockStateFile. PropertyValue>, List<BlockStateFile. Model>> variants = slabBlockStateFile.variants().get().variants();

            // get models for double slab and bottom slab
            List<BlockStateFile.Model> fullBlockModels = new ArrayList<>();
            List<BlockStateFile.Model> bottomHalfModels = new ArrayList<>();
            for (Map. Entry<List<BlockStateFile.PropertyValue>, List<BlockStateFile.Model>> entry : variants. entrySet()) {
                boolean isDoubleProperty = entry.getKey().contains(BlockStateFile.PropertyValue.create("type", "double"));
                if(isDoubleProperty) {
                    fullBlockModels.addAll(entry.getValue());
                    continue;
                }
                boolean isBottomProperty = entry.getKey().contains(BlockStateFile.PropertyValue.create("type", "bottom"));
                if(!isBottomProperty) continue;
                bottomHalfModels.addAll(entry.getValue());
            }

            // create new vertical slab blockstate file
            BlockStateFile.Variants verticalSlabVariants = new BlockStateFile.Variants(new HashMap<>());
            // add direction and double slab variants
            Direction[] directions = Direction.values();
            for (Direction direction : directions) {
                if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) continue;
                BlockStateFile.PropertyValue facingProperty = BlockStateFile.PropertyValue.create("facing", direction.getName());
                // add double slab variants
                verticalSlabVariants.addVariant(
                    List.of(facingProperty, BlockStateFile.PropertyValue.create("single_slab", "false")),
                    fullBlockModels.toArray(new BlockStateFile.Model[0])
                );
                // add single slab variants
                verticalSlabVariants.addVariant(
                    List.of(facingProperty, BlockStateFile.PropertyValue.create("single_slab", "true")),
                    attemptToGenerateVerticalSlabModels(slab, bottomHalfModels.stream().map((model -> {
                        int yRot = DirectionUtil.getBlockStateYRotationForDirection(direction);
                        int xRot = model.x + 90;
                        boolean uvLock = slab.shouldUvLockModel();
                        return new BlockStateFile.Model(model.model, xRot, yRot, uvLock, model.weight);
                    })).toList().toArray(new BlockStateFile.Model[0]))
                );
            }

            // encode and return blockstate file
            BlockStateFile verticalSlabFile = new BlockStateFile(Optional.of(verticalSlabVariants), Optional.empty());
            DataResult<JsonElement> verticalSlabBlockStateJSON = BlockStateFile.CODEC.encode(verticalSlabFile, JsonOps.INSTANCE, new JsonObject());
            return verticalSlabBlockStateJSON.getOrThrow().toString();

        } else if(slabBlockStateFile.multipart().isPresent()) {
            // file uses multipart
            return null;
        }
        return null;
    }

    private static BlockStateFile.Model[] attemptToGenerateVerticalSlabModels(DynamicSlab slab, BlockStateFile.Model[] models) {
        if(slab.shouldAttemptToGenerateRealVerticalSlabModel()) {
            return Arrays.stream(models).peek((model) -> updateModel(model, slab.getVerticalSlabLocation())).toList().toArray(new BlockStateFile.Model[0]);
        }
        // add model to item definitions map
        verticalSlabToBlockModel.putIfAbsent(slab.getVerticalSlabLocation(), new VerticalSlabModelLocation(models[0].model, true));
        return models;
    }

    private static void updateModel(BlockStateFile.Model model, ResourceLocation verticalSlabLocation) {
//        model.model = ResourceLocation.withDefaultNamespace("stone");
        Optional<Resource> modelFileResource = Minecraft.getInstance().getResourceManager().getResource(MODEL_CONVERTER.idToFile(model.model));
        if(modelFileResource.isEmpty()) {
            EnchantedVerticalSlabsLogging.debug("MODEL FILE: {} IS EMPTY", model.model);
            return;
        };
        byte[] bytes;
        try {
            bytes = modelFileResource.get().open().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String modelFileString = new String(bytes, StandardCharsets.UTF_8);
        JsonObject parsedModelFile = JsonParser.parseString(modelFileString).getAsJsonObject();

        JsonElement parentModelJson = parsedModelFile.get("parent");
        // if the model doesn't have parent, return and add model to item definitions map
        if(parentModelJson == null) {
            verticalSlabToBlockModel.putIfAbsent(verticalSlabLocation, new VerticalSlabModelLocation(model.model, false));
            EnchantedVerticalSlabsLogging.debug("MODEL FILE: {} DOESNT HAVE PARENT", model.model);
            return;
        }
        String parentModel = parentModelJson.getAsString();
        ResourceLocation parentLocation = ResourceLocation.parse(parentModel);
        // if the parent model isn't minecraft:block/slab, don't modify the model and add model to item definitions map
        if(!parentLocation.equals(ResourceLocation.withDefaultNamespace("block/slab"))) {
            verticalSlabToBlockModel.putIfAbsent(verticalSlabLocation, new VerticalSlabModelLocation(model.model, false));
            EnchantedVerticalSlabsLogging.debug("MODEL FILE: {} DOESNT HAVE VALID PARENT", model.model);
            return;
        }

        ResourceLocation newParent = ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "block/vertical_slab_side");
        parsedModelFile.add("parent", new JsonPrimitive(newParent.toString()));

        // add new vertical slab model to dynamic resource pack
        ResourceLocation newModelLocation = ResourceLocation.fromNamespaceAndPath(
            EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION,
            "block/" + model.model.getNamespace() + "/" + model.model.getPath().replaceFirst("^block\\/", "")
        );
        EnchantedVerticalSlabsLogging.debug("ADDED MODEL: {}", newModelLocation);
        EVSDynamicResources.INSTANCE.addModel(newModelLocation, parsedModelFile.toString());
        // add model to item definitions map
        verticalSlabToBlockModel.putIfAbsent(verticalSlabLocation, new VerticalSlabModelLocation(newModelLocation, false));

        // set blockstate model location to new model location and reset x rotation
        model.model = newModelLocation;
        model.x -= 90;
    }

    public record VerticalSlabModelLocation(ResourceLocation location, boolean usesRegularSlabModel) {};
}
