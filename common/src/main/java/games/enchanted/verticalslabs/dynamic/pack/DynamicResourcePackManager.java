package games.enchanted.verticalslabs.dynamic.pack;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import games.enchanted.verticalslabs.dynamic.BlockStateFile;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabs;
import games.enchanted.verticalslabs.platform.Services;
import games.enchanted.verticalslabs.registry.RegistryHelpers;
import games.enchanted.verticalslabs.util.BlockRenderTypeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.block.state.BlockState;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DynamicResourcePackManager {
    private static boolean hasBeenInitialised = false;
    public static void initialise() {
        if(hasBeenInitialised) return;
        addBlockstates();
        hasBeenInitialised = true;
    }

    private static void addBlockstates() {
        Optional<Resource> resource = Minecraft.getInstance().getResourceManager().getResource(ResourceLocation.fromNamespaceAndPath("mo_glass", "blockstates/tinted_glass_slab.json"));
        if(resource.isEmpty()) return;
        byte[] bytes;
        try {
            bytes = resource.get().open().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String string = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(string);
        JsonObject jsonObject = JsonParser.parseString(string).getAsJsonObject();

//        BlockModelDefinition parsedDefinition = BlockModelDefinition.fromJsonElement(jsonObject);
//        String tojson = BlockModelDefinition.GSON.toJson(parsedDefinition);

        DataResult<BlockStateFile> file = BlockStateFile.CODEC.parse(JsonOps.INSTANCE, jsonObject);
        if(file.isSuccess()) {
            BlockStateFile finalFile = file.getOrThrow();
            Map<List<BlockStateFile. PropertyValue>, List<BlockStateFile. Model>> variants = finalFile.variants().get().variants();
            List<BlockStateFile.Model> fullBlockModels;
            for (Map. Entry<List<BlockStateFile.PropertyValue>, List<BlockStateFile.Model>> entry : variants. entrySet()) {
                boolean isCorrectProperty = entry.getKey().contains(BlockStateFile.PropertyValue.create("type", "bottom"));
                if(!isCorrectProperty) continue;
                System.out.println(entry.getValue().get(0));
            };
            BlockRenderTypeUtil.copyRenderTypeFromBlock(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "mo_glass/vertical_tinted_glass_slab"), ResourceLocation.fromNamespaceAndPath("minecraft", "tinted_glass"));
            RenderType type = ItemBlockRenderTypes.getRenderType(RegistryHelpers.getBlockFromLocation(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "mo_glass/vertical_tinted_glass_slab")).defaultBlockState());
            System.out.println("");
        }
    }
}
