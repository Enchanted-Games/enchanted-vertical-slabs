package games.enchanted.verticalslabs.util;

import net.minecraft.client.Minecraft;

import java.util.Collection;
import java.util.Collections;

public class ResourcepackUtil {
    public static Collection<String> removeAllResourcepacks() {
        Collection<String> alreadyActivePacks = Minecraft.getInstance().getResourcePackRepository().getSelectedIds();
        Minecraft.getInstance().getResourcePackRepository().setSelected(Collections.emptyList());
        Collection<String> remainingPacksAfterDisabling = Minecraft.getInstance().getResourcePackRepository().getSelectedIds();

        if(alreadyActivePacks.equals(remainingPacksAfterDisabling)) {
            return alreadyActivePacks;
        }
        Minecraft.getInstance().reloadResourcePacks();
        return alreadyActivePacks;
    }

    public static void reEnableResourcepacks(Collection<String> packsToReEnable) {
        reEnableResourcepacks(packsToReEnable, false);
    }

    public static void reEnableResourcepacks(Collection<String> packsToReEnable, boolean forceReload) {
        Collection<String> alreadyActivePacks = Minecraft.getInstance().getResourcePackRepository().getSelectedIds();
        Minecraft.getInstance().getResourcePackRepository().setSelected(packsToReEnable);
        Collection<String> remainingPacksAfterDisabling = Minecraft.getInstance().getResourcePackRepository().getSelectedIds();

        if(alreadyActivePacks.equals(remainingPacksAfterDisabling) && !forceReload) {
            return;
        }
        Minecraft.getInstance().reloadResourcePacks();
    }
}
