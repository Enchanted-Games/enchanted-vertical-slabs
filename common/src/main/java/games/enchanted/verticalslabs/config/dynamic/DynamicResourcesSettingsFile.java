package games.enchanted.verticalslabs.config.dynamic;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import games.enchanted.verticalslabs.config.BasicConfigFile;

public class DynamicResourcesSettingsFile extends BasicConfigFile {
    private static final String DYNAMIC_RESOURCES_SETTINGS_FILENAME = "dynamic_resources_settings.json";

    public static DynamicResourcesSettingsFile INSTANCE = new DynamicResourcesSettingsFile(false);

    private static final Codec<DynamicResourcesSettingsFile> SETTINGS_FILE_CODEC = RecordCodecBuilder.create((RecordCodecBuilder.Instance<DynamicResourcesSettingsFile> instance) ->
        instance.group(
            Codec.BOOL.fieldOf("force_regenerate").forGetter(DynamicResourcesSettingsFile::getForceRegenerate)
        ).apply(
            instance,
            DynamicResourcesSettingsFile::new
        )
    );

    private boolean forceRegenerate;

    DynamicResourcesSettingsFile(boolean forceRegenerate) {
        super(DYNAMIC_RESOURCES_SETTINGS_FILENAME);
        this.forceRegenerate = forceRegenerate;
    }

    @Override
    public BasicConfigFile getInstance() {
        return INSTANCE;
    }

    @Override
    protected BasicConfigFile decode(JsonElement json) {
        DataResult<Pair<DynamicResourcesSettingsFile, JsonElement>> result = SETTINGS_FILE_CODEC.decode(JsonOps.INSTANCE, json);
        if(result.error().isPresent()) {
            throw new RuntimeException(result.error().get().message());
        }

        DynamicResourcesSettingsFile decoded = result.getOrThrow().getFirst();
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

    private void setInstanceFromDecoded(DynamicResourcesSettingsFile decoded) {
        setForceRegenerate(decoded.forceRegenerate);
    }

    public void resetForceRegenerateToFalse() {
        if(INSTANCE.forceRegenerate) {
            INSTANCE.forceRegenerate = false;
            writeSettingsFile();
        }
    }
    public boolean getForceRegenerate() {
        return INSTANCE.forceRegenerate;
    }
    public void setForceRegenerate(boolean newValue) {
        INSTANCE.forceRegenerate = newValue;
    }
}
