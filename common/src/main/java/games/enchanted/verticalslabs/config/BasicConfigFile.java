package games.enchanted.verticalslabs.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsLogging;
import games.enchanted.verticalslabs.EnchantedVerticalSlabsMod;
import games.enchanted.verticalslabs.util.FilesystemUtil;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public abstract class BasicConfigFile {
    protected final String fileName;

    protected BasicConfigFile(String fileName) {
        this.fileName = fileName;
    }

    public abstract BasicConfigFile getInstance();

    protected abstract BasicConfigFile decode(JsonElement json);

    protected abstract JsonElement encode();

    public void readOrCreateSettingsFile() {
        try {
            byte[] bytesFromFile;
            try {
                bytesFromFile = Files.readAllBytes(EnchantedVerticalSlabsMod.getEVSModDirectory().resolve(this.fileName));
            } catch (NoSuchFileException e) {
                writeSettingsFile();
                return;
            }
            Gson parser = new Gson();
            JsonElement jsonFromFile = parser.fromJson(new String(bytesFromFile, StandardCharsets.UTF_8), JsonElement.class);
            decode(jsonFromFile); // TODO: make this a dataresult
        } catch (Exception e) {
            EnchantedVerticalSlabsLogging.error("Error while trying to read file '{}', please report this if it persists.\n" + e, this.fileName);
        }
        writeSettingsFile();
    }

    public void writeSettingsFile() {
        try {
            JsonElement encodedFile = encode();
            FilesystemUtil.writeToFile(
                EnchantedVerticalSlabsMod.getEVSModDirectory().resolve(this.fileName),
                encodedFile.toString().getBytes(StandardCharsets.UTF_8),
                true
            );
        } catch (Exception e) {
            EnchantedVerticalSlabsLogging.error("Error while trying to save file '{}', please report this if it persists.\n" + e, this.fileName);
        }
    }
}
