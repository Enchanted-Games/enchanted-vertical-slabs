package games.enchanted.verticalslabs.dynamic.resources;

public class ResourceGenerationException extends RuntimeException {
    final String causeMessage;

    public ResourceGenerationException(String causeMessage, Throwable e) {
        super(causeMessage, e);
        this.causeMessage = causeMessage;
    }

    public ResourceGenerationException(Throwable e) {
        super("Unknown cause", e);
        this.causeMessage = "Unknown cause";
    }

    public String getCauseMessage() {
        return causeMessage;
    }
}
