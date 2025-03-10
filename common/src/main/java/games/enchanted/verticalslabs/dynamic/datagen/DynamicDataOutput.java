package games.enchanted.verticalslabs.dynamic.datagen;

import com.google.common.hash.HashCode;
import games.enchanted.verticalslabs.dynamic.pack.EVSDynamicResources;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.data.CachedOutput;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;

public class DynamicDataOutput implements CachedOutput {
    @Override
    public void writeIfNeeded(@NotNull Path path, byte @NotNull [] bytes, @NotNull HashCode hashCode) {
        EVSDynamicResources.INSTANCE.addRawResource(path.toString(), () -> IoSupplierUtil.byteStreamToIoSupplier(new ByteArrayInputStream(bytes)));
    }
}
