package games.enchanted.verticalslabs.dynamic.pack_managers;

import games.enchanted.verticalslabs.dynamic.resources.ResourceGenerationException;
import net.minecraft.ResourceLocationException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class PackManager {
    boolean hasBeenInitialised = false;
    boolean isInitialising = false;
    List<Runnable> reloadCallbacks = new ArrayList<>();
    List<Runnable> completionCallbacks = new ArrayList<>();
    List<Consumer<ResourceGenerationException>> exceptionCallbacks = new ArrayList<>();

    public void initialiseInternal() {
        if(hasBeenInitialised) return;
        if(isInitialising) throw new IllegalStateException("Cannot initialise dynamic pack: it is already being initialised");
        isInitialising = true;
        try {
            initialiseResources();
        } catch (ResourceGenerationException e) {
            exceptionCallbacks.forEach((callback) -> callback.accept(e));
        }
    }

    /**
     * Generate all resources here
     */
    abstract void initialiseResources() throws ResourceGenerationException;

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
        if(requiresReload) {
            reloadCallbacks.forEach(Runnable::run);
        }
        completionCallbacks.forEach(Runnable::run);
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

    protected boolean hasReloadCallbacks() {
        return !reloadCallbacks.isEmpty();
    }

    /**
     * Add a runnable that gets called when this pack has been fully initialised
     *
     * @param completionCallback the callback to run on completion
     */
    public void addCompletionCallback(Runnable completionCallback) {
        completionCallbacks.add(completionCallback);
    }

    /**
     * Add a runnable that gets called when a {@link ResourceGenerationException} is thrown
     *
     * @param exceptionCallback the callback to run on completion
     */
    public void addExceptionCallback(Consumer<ResourceGenerationException> exceptionCallback) {
        exceptionCallbacks.add(exceptionCallback);
    }
}
