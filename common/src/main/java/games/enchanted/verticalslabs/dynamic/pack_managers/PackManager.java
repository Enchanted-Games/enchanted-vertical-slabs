package games.enchanted.verticalslabs.dynamic.pack_managers;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class PackManager {
    boolean hasBeenInitialised = false;
    boolean isInitialising = false;
    List<Runnable> reloadCallbacks = new ArrayList<>();
    List<Runnable> completionCallbacks = new ArrayList<>();

    public void initialiseInternal() {
        if(hasBeenInitialised) return;
        if(isInitialising) throw new IllegalStateException("Cannot initialise dynamic pack: it is already being initialised");
        isInitialising = true;
        initialiseResources();
    }

    /**
     * Generate all resources here
     */
    abstract void initialiseResources();

    /**
     * Call this when all the resources have been generated
     *
     * @param requiresReload if the resource manager should be reloaded on completion
     */
    protected void complete(boolean requiresReload) {
        complete(requiresReload, null);
    }
    /**
     * Call this when all the resources have been generated
     *
     * @param requiresReload if the resource manager should be reloaded on completion
     * @param functionOnComplete a runnable to run on completion (called before resources are reloaded)
     */
    protected void complete(boolean requiresReload, @Nullable Runnable functionOnComplete) {
        hasBeenInitialised = true;
        isInitialising = false;
        if(functionOnComplete != null) {
            functionOnComplete.run();
        }
        completionCallbacks.forEach(Runnable::run);
        if(requiresReload) {
            reloadCallbacks.forEach(Runnable::run);
        }
    }

    public boolean hasBeenInitialised() {
        return hasBeenInitialised;
    }

    /**
     * Add a runnable that should reload the relevant resources for this pack manager
     *
     * @param reloadCallback the reload callback
     */
    public void addReloadCallback(Runnable reloadCallback) {
        reloadCallbacks.add(reloadCallback);
    }

    /**
     * Add a runnable that gets called when this pack has been fully initialised
     *
     * @param completionCallback the callback to run on completion
     */
    public void addCompletionCallback(Runnable completionCallback) {
        completionCallbacks.add(completionCallback);
    }

    protected boolean hasReloadCallbacks() {
        return !reloadCallbacks.isEmpty();
    }
}
