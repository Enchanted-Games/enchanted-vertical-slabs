package games.enchanted.verticalslabs.util;

import net.minecraft.client.Minecraft;

import java.util.Collection;
import java.util.Collections;

public class ResourcepackUtil {
    public static Collection<String> removeAllResourcepacks() {
        Collection<String> alreadyActivePacks = Minecraft.getInstance().getResourcePackRepository().getSelectedIds();
        Minecraft.getInstance().getResourcePackRepository().setSelected(Collections.emptyList());
        Minecraft.getInstance().reloadResourcePacks();
        return alreadyActivePacks;
    }

    public static void reEnableResourcepacks(Collection<String> packsToReEnable) {
        Minecraft.getInstance().getResourcePackRepository().setSelected(packsToReEnable);
        Minecraft.getInstance().reloadResourcePacks();
    }
}
