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

    public String getFullErrorAndStacktrace() {
        Throwable causationException = this.getCause();
        StringBuilder fullError = new StringBuilder();
        fullError.append("Cause: ").append(causationException.toString()).append("\nStacktrace:\n");
        for (StackTraceElement line : causationException.getStackTrace()) {
            fullError.append(line).append("\n");
        }
        return fullError.toString();
    }
}
