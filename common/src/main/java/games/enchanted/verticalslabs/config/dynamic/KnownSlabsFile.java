package games.enchanted.verticalslabs.config.dynamic;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import games.enchanted.verticalslabs.config.BasicConfigFile;
import games.enchanted.verticalslabs.dynamic.DynamicVerticalSlabsManager;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class KnownSlabsFile extends BasicConfigFile {
    private static final String KNOWN_SLABS_FILENAME = "known_slabs.json";

    public static KnownSlabsFile INSTANCE = new KnownSlabsFile(new ArrayList<>());

    private static final Codec<KnownSlabsFile> SETTINGS_FILE_CODEC = RecordCodecBuilder.create((RecordCodecBuilder.Instance<KnownSlabsFile> instance) ->
        instance.group(
            DynamicVerticalSlabsManager.DYNAMIC_SLABS_LIST_CODEC.fieldOf("known_slabs").forGetter(KnownSlabsFile::getKnownSlabs)
        ).apply(
            instance,
            KnownSlabsFile::new
        )
    );

    private List<ResourceLocation> knownSlabs;

    KnownSlabsFile(List<ResourceLocation> knownSlabs) {
        super(KNOWN_SLABS_FILENAME);
        this.knownSlabs = knownSlabs;
    }

    @Override
    public BasicConfigFile getInstance() {
        return INSTANCE;
    }

    @Override
    protected BasicConfigFile decode(JsonElement json) {
        DataResult<Pair<KnownSlabsFile, JsonElement>> result = SETTINGS_FILE_CODEC.decode(JsonOps.INSTANCE, json);
        if(result.error().isPresent()) {
            throw new RuntimeException(result.error().get().message());
        }

        KnownSlabsFile decoded = result.getOrThrow().getFirst();
        setInstanceFromDecoded(decoded);
        return decoded;
    }

    @Override
    protected JsonElement encode() {
        DataResult<JsonElement> result = SETTINGS_FILE_CODEC.encodeStart(JsonOps.INSTANCE, INSTANCE);
        if(result.error().isPresent()) {
            throw new RuntimeException(result.error().get().message());
        }

        return result.getOrThrow();
    }

    private void setInstanceFromDecoded(KnownSlabsFile decoded) {
        setKnownSlabs(decoded.knownSlabs);
    }

    public List<ResourceLocation> getKnownSlabs() {
        return INSTANCE.knownSlabs;
    }
    public void setKnownSlabs(List<ResourceLocation> knownSlabs) {
        INSTANCE.knownSlabs = knownSlabs;
    }
}
