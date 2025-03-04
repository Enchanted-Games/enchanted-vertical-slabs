package games.enchanted.verticalslabs.dynamic;

import games.enchanted.verticalslabs.EnchantedVerticalSlabsConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class DynamicResourcePackManager {
    public static void onAddBlockstates() {
        Optional<Resource> resource = Minecraft.getInstance().getResourceManager().getResource(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, "blockstates/vertical_oak_slab.json"));
        if(resource.isPresent()) {
            try {
                final String[] file;
                try (BufferedReader stream = resource.get().openAsReader()) {
                    file = new String[]{""};
                    stream.lines().forEach((str) -> file[0] += (str));
                }
                System.out.println("from oninit" + file[0]);
                for (int i = 0; i < DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.size(); i++) {
                    String newPath = DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.get(i).getNamespace() + "/vertical_" + DynamicVerticalSlabs.DYNAMIC_SLAB_BLOCKS.get(i).getPath();
                    DynamicResourcePack.INSTANCE.addBlockstate(ResourceLocation.fromNamespaceAndPath(EnchantedVerticalSlabsConstants.LEGACY_RESOURCE_LOCATION, newPath), file[0]);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void onAddModels() {

    }

    public static void onAddItemDefinitions() {

    }
}
