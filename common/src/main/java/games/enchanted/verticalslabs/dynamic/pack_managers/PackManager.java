package games.enchanted.verticalslabs.dynamic.pack_managers;

import games.enchanted.verticalslabs.dynamic.resources.ResourceGenerationException;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class PackManager {
    boolean hasBeenInitialised = false;
    boolean isInitialising = false;
    List<Runnable> reloadCallbacks = new ArrayList<>();
    List<Runnable> completionCallbacks = new ArrayList<>();
    List<Consumer<ResourceGenerationException>> exceptionCallbacks = new ArrayList<>();

    public void initialiseInternal(BiConsumer<String, Float> taskCompletionCallback) {
        if(hasBeenInitialised) return;
        if(isInitialising) throw new IllegalStateException("Cannot initialise dynamic pack: it is already being initialised");
        isInitialising = true;
        try {
            initialiseResources(taskCompletionCallback);
        }
        catch (ResourceGenerationException e) {
            exceptionCallbacks.forEach((callback) -> callback.accept(e));
            clearCallbacks();
        }
        catch (Exception e) {
            exceptionCallbacks.forEach((callback) -> callback.accept(new ResourceGenerationException("Unknown", e)));
            clearCallbacks();
        }
    }

    /**
     * Generate all resources here
     */
    abstract void initialiseResources(BiConsumer<String, Float> taskCompletionCallback) throws ResourceGenerationException;

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
        clearCallbacks();
    }

    public boolean hasBeenInitialised() {
        return hasBeenInitialised;
    }

    private void clearCallbacks() {
        this.exceptionCallbacks.clear();
        this.completionCallbacks.clear();
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
     * This is cleared once the resource generation is complete or an error occurs
     *
     * @param completionCallback the callback to run on completion
     */
    public void addCompletionCallback(Runnable completionCallback) {
        completionCallbacks.add(completionCallback);
    }

    /**
     * Add a consumer that gets called when a {@link ResourceGenerationException} is thrown
     * This is cleared once the resource generation is complete or an error occurs
     *
     * @param exceptionCallback the callback to run on completion
     */
    public void addExceptionCallback(Consumer<ResourceGenerationException> exceptionCallback) {
        exceptionCallbacks.add(exceptionCallback);
    }
}
