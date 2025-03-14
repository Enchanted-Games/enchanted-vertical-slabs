package games.enchanted.verticalslabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantedVerticalSlabsLogging {
    public static final Logger LOG = LoggerFactory.getLogger(EnchantedVerticalSlabsConstants.MOD_NAME);

    private static final String messagePrefix = "[" + EnchantedVerticalSlabsConstants.MOD_NAME + "]: ";

    public static void info(String message, Object... args) {
        LOG.info(messagePrefix + message, args);
    }

    public static void warn(String message, Object... args) {
        LOG.warn(messagePrefix + message, args);
    }

    public static void error(String message, Object... args) {
        LOG.error(messagePrefix + message, args);
    }

    public static void debug(String message, Object... args) {
        LOG.debug(messagePrefix + message, args);
    }
}
