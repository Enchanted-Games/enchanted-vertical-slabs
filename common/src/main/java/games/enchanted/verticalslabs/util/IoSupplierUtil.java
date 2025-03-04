package games.enchanted.verticalslabs.util;

import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IoSupplierUtil {
    public static IoSupplier<InputStream> stringToIoSupplier(String string) {
        return new IoSupplier<>() {
            @Override
            public @NotNull InputStream get() {
                return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
            }
        };
    }
}
