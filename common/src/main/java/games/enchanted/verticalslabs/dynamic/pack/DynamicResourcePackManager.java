package games.enchanted.verticalslabs.dynamic.pack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.dynamic.DynamicSlab;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.dynamic.resources.BlockStateFile;
import games.enchanted.verticalslabs.util.DirectionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.server.packs.resources.Resource;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DynamicResourcePackManager {
    private static final FileToIdConverter BLOCKSTATE_CONVERTER = new FileToIdConverter("blockstates", ".json");

    private static boolean hasBeenInitialised = false;
    public static void initialise() {
        if(hasBeenInitialised) return;
        addBlockstates();
        hasBeenInitialised = true;
    }

    private static void addBlockstates() {
        for (DynamicSlab slab : DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS) {
            String blockStateFile = generateBlockStateFileForSlab(slab);
            if(blockStateFile == null) {
                EnchantedVerticalSlabsConstants.LOG.error("Failed to generate blockstate definition file for: {}", slab.getVerticalSlabLocation());
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
                    bottomHalfModels.stream().map((model -> {
                        int yRot = DirectionUtil.getBlockStateYRotationForDirection(direction);
                        int xRot = model.x + 90;
                        boolean uvLock = slab.shouldUvLockModel();
                        return new BlockStateFile.Model(model.model, xRot, yRot, uvLock, model.weight);
                    })).toList().toArray(new BlockStateFile.Model[0])
                );
            }

            // encode and return blockstate file
            BlockStateFile verticalSlabFile = new BlockStateFile(Optional.of(verticalSlabVariants), Optional.empty());
            DataResult<JsonElement> verticalSlabBlockStateJSON = BlockStateFile.CODEC.encode(verticalSlabFile, JsonOps.INSTANCE, new JsonObject());
//            System.out.println(verticalSlabBlockStateJSON.getOrThrow().toString());
            return verticalSlabBlockStateJSON.getOrThrow().toString();

        } else if(slabBlockStateFile.multipart().isPresent()) {
            // file uses multipart
            return null;
        }
        return null;
    }
}
