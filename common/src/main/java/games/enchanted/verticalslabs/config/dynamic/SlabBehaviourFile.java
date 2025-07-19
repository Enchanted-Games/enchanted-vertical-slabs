package games.enchanted.verticalslabs.config.dynamic;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import games.enchanted.verticalslabs.config.BasicConfigFile;

public class SlabBehaviourFile extends BasicConfigFile {
    private static final String SLAB_BEHAVIOUR_FILENAME = "slab_behaviour.json";

    public static SlabBehaviourFile INSTANCE = new SlabBehaviourFile(false);

    private static final Codec<SlabBehaviourFile> SETTINGS_FILE_CODEC = RecordCodecBuilder.create((RecordCodecBuilder.Instance<SlabBehaviourFile> instance) ->
        instance.group(
            Codec.BOOL.fieldOf("use_separate_vertical_slab_item").forGetter(SlabBehaviourFile::getUseSeparateVerticalSlabItems)
        ).apply(
            instance,
            SlabBehaviourFile::new
        )
    );

    private boolean useSeparateVerticalSlabItems;

    SlabBehaviourFile(boolean useSeparateVerticalSlabItems) {
        super(SLAB_BEHAVIOUR_FILENAME);
        this.useSeparateVerticalSlabItems = useSeparateVerticalSlabItems;
    }

    @Override
    public BasicConfigFile getInstance() {
        return INSTANCE;
    }

    @Override
    protected BasicConfigFile decode(JsonElement json) {
        DataResult<Pair<SlabBehaviourFile, JsonElement>> result = SETTINGS_FILE_CODEC.decode(JsonOps.INSTANCE, json);
        if (result.error().isPresent()) {
            throw new RuntimeException(result.error().get().message());
        }

        SlabBehaviourFile decoded = result.getOrThrow().getFirst();
        setInstanceFromDecoded(decoded);
        return decoded;
    }

    @Override
    protected JsonElement encode() {
        DataResult<JsonElement> result = SETTINGS_FILE_CODEC.encodeStart(JsonOps.INSTANCE, INSTANCE);
        if (result.error().isPresent()) {
            throw new RuntimeException(result.error().get().message());
        }

        return result.getOrThrow();
    }

    private void setInstanceFromDecoded(SlabBehaviourFile decoded) {
        setUseSeparateVerticalSlabItems(decoded.useSeparateVerticalSlabItems);
    }

    public boolean getUseSeparateVerticalSlabItems() {
        return INSTANCE.useSeparateVerticalSlabItems;
    }

    public void setUseSeparateVerticalSlabItems(boolean useSeparateVerticalSlabItems) {
        INSTANCE.useSeparateVerticalSlabItems = useSeparateVerticalSlabItems;
    }
}