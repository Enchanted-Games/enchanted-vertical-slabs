package games.enchanted.verticalslabs.dynamic.pack_managers;

public interface PackManager {
    void initialise();
    boolean requiresReloadToApply();
    void triggeredReload();
}
