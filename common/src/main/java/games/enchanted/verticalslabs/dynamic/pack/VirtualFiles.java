package games.enchanted.verticalslabs.dynamic.pack;

import com.google.common.base.Joiner;
import com.mojang.serialization.DataResult;
import net.minecraft.FileUtil;
import org.apache.logging.log4j.core.util.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class VirtualFiles<T> {
    private static final Pattern FILE_EXTENSION_SPLIT = Pattern.compile("\\.(?=[^.]*$)");
    private static final Joiner PATH_JOINER = Joiner.on("/");

    private final Hashtable<String, @Nullable VirtualFiles<T>> filesAndDirectories = new Hashtable<>();
    @Nullable final T content;

    public VirtualFiles(@Nullable T content) {
        this.content = content;
    }
    public VirtualFiles() {
        this.content = null;
    }

    public VirtualFiles<T> getDirectory(String... pathElements) throws IllegalArgumentException {
        VirtualFiles<T> directory = getPath(pathElements);
        if(directory.isFile()) {
            throw new IllegalArgumentException("Tried to get directory [" + PATH_JOINER.join(pathElements) + "] but got a file instead");
        }
        return directory;
    }

    public VirtualFiles<T> getFile(String... pathElements) throws IllegalArgumentException {
        VirtualFiles<T> file = getPath(pathElements);
        if(file.isDirectory()) {
            throw new IllegalArgumentException("Tried to get file [" + PATH_JOINER.join(pathElements) + "] but got a directory instead");
        }
        return file;
    }

    public VirtualFiles<T> getPath(String... pathElements) throws IllegalArgumentException {
        return getFilesRecursive(this, pathElements);
    }

    public void iterateOnFiles(FileConsumer consumer) {
        if(this.isFile()) {
            throw new IllegalStateException("Cannot call iterateOnFiles on a file");
        }
        iterateOnFiles(consumer, this, new ArrayList<>());
    }
    private void iterateOnFiles(FileConsumer consumer, VirtualFiles<T> directory, ArrayList<String> rootPath) {
        for (Map.Entry<String, @Nullable VirtualFiles<T>> subFileOrDirectory : directory.filesAndDirectories.entrySet()) {
            if(subFileOrDirectory.getValue() == null) continue;
            if(subFileOrDirectory.getValue().isDirectory()) {
                ArrayList<String> directoryPath = new ArrayList<>(rootPath);
                directoryPath.add(subFileOrDirectory.getKey());
                iterateOnFiles(consumer, subFileOrDirectory.getValue(), directoryPath);
            } else {
                String[] fileNameSplit = subFileOrDirectory.getKey().split(FILE_EXTENSION_SPLIT.pattern());
                String fileName = fileNameSplit[0];
                String fileExtension = fileNameSplit[fileNameSplit.length - 1];
                String relativeFilePath = rootPath.isEmpty() ? fileName : PATH_JOINER.join(rootPath.toArray(new String[0])) + "/" + fileName;
                consumer.consume(subFileOrDirectory.getValue(), relativeFilePath, fileExtension);
            }
        }
    }

    public interface FileConsumer {
        void consume(VirtualFiles<?> file, String path, String fileExtension);
    }

    private void tryPutDirectory(VirtualFiles<T> files, String... pathElements) {
        if(pathElements.length == 0) return;
        @Nullable VirtualFiles<T> folder = files.filesAndDirectories.get(pathElements[0]);
        if(folder != null) {
            // folder already exists, enter it and continue
            if(folder.isFile()) {
                throw new IllegalArgumentException("A file with that name already exists at path: " + PATH_JOINER.join(pathElements));
            }
            files.tryPutDirectory(folder, ArrayUtils.remove(pathElements, 0));
            return;
        }
        // no folder present, add one
        files.filesAndDirectories.put(pathElements[0], new VirtualFiles<>());
    }

    private void tryPutFile(T fileContent, String... pathElements) {
        VirtualFiles<T> fileDirectoryLocation = getDirectory(ArrayUtils.remove(pathElements, pathElements.length - 1));
        VirtualFiles<T> fileToAdd = new VirtualFiles<>(fileContent);
        fileDirectoryLocation.filesAndDirectories.put(pathElements[pathElements.length - 1], fileToAdd);
    }

    private VirtualFiles<T> getFilesRecursive(VirtualFiles<T> files, String... path) {
        if (path.length == 0) return files;
        VirtualFiles<T> directory = files.filesAndDirectories.get(path[0]);
        if(directory == null) {
            throw new IllegalArgumentException("Directory [" + path[0] + "] does not exist at: " + PATH_JOINER.join(path));
        }
        if(directory.isFile() || directory.filesAndDirectories.isEmpty()) {
            return directory;
        }
        return getFilesRecursive(directory, ArrayUtils.remove(path, 0));
    }

    public void add(String filePath, T fileContent) {
        DataResult<List<String>> decomposePath = FileUtil.decomposePath(filePath);
        List<String> elements = decomposePath.getOrThrow();
        String[] elementsArray = elements.toArray(new String[0]);
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            boolean isLast = i == elements.size() - 1;
            if(isLast) {
                tryPutFile(fileContent, elementsArray);
                return;
            }
            tryPutDirectory(this, ArrayUtils.remove(elementsArray, elementsArray.length - 1));
        }
    }

    public boolean isFile() {
        return this.content != null && this.filesAndDirectories.isEmpty();
    }
    public boolean isDirectory() {
        return !isFile();
    }
}
