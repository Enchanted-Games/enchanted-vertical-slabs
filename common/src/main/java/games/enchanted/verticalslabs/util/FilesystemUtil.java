package games.enchanted.verticalslabs.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesystemUtil {
    public static Path trimFilenameFromPath(Path path) {
        if(path.getFileName().toString().contains(".")) {
            return path.getParent();
        }
        return path;
    }

    /**
     * Creates directories for the given path. If they already exist, this method will do nothing.
     * If given a path ending with a file, it will only create directories up to the parent of the file.
     * <p>
     * Given the path `/a/b/c`, directories a, b, and c will be created.
     * Given the path `/a/b/file.txt`, directories a and b will be created.
     *
     *
     * @param path the path
     */
    public static void createDirectories(Path path) throws IOException {
        path = trimFilenameFromPath(path);
        Files.createDirectories(Files.exists(path) ? path.toRealPath() : path);
    }

    /**
     * Write data to a file on the file system. Any missing directories will be created if necessary.
     *
     * @param filePath        the file path
     * @param contents        the contents
     * @param replaceExisting should replace existing file if it already exists
     */
    public static void writeToFile(Path filePath, byte[] contents, boolean replaceExisting) throws IOException {
        createDirectories(filePath);
        if(!replaceExisting && Files.exists(filePath)) {
            return;
        }
        Files.write(filePath, contents);
    }
}
