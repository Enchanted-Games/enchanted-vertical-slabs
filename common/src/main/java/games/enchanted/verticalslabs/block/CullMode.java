package games.enchanted.verticalslabs.block;

public enum CullMode {
    NO_CULL(true, true),
    CULL(true, false),
    VANILLA_CULL(false, false);

    public final boolean forceReturnValue;
    public final boolean cull;

    CullMode(boolean forceReturnValue, boolean renderFace) {
        this.forceReturnValue = forceReturnValue;
        this.cull = renderFace;
    }
}
