package games.enchanted.verticalslabs.dynamic.datagen;

import com.google.common.hash.HashCode;
import games.enchanted.verticalslabs.util.IoSupplierUtil;
import net.minecraft.data.CachedOutput;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class DynamicDataOutput implements CachedOutput {
    @Override
    public void writeIfNeeded(@NotNull Path path, byte @NotNull [] bytes, @NotNull HashCode hashCode) throws IOException {
        IoSupplier<InputStream> ioStream = IoSupplierUtil.byteStreamToIoSupplier(new ByteArrayInputStream(bytes));
        System.out.println(path);
//        System.out.println(IOUtils.toString(ioStream.get(), StandardCharsets.UTF_8));
//        EVSDynamicResources.INSTANCE.addRawResource(path.toString(), ioStream);
    }
}
