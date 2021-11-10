package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyShortcut;

/**
 * A class to access Shortcut data stored as a json file on the hard disk.
 */
public class JsonShortcutStorage implements ShortcutStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonShortcutStorage.class);

    private Path filePath;

    public JsonShortcutStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getShortcutFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyShortcut> readShortcut() throws DataConversionException {
        return readShortcut(filePath);
    }

    /**
     * Similar to {@link #readShortcut()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyShortcut> readShortcut(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableShortcut> jsonShortcut = JsonUtil.readJsonFile(
                filePath, JsonSerializableShortcut.class);
        if (!jsonShortcut.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(jsonShortcut.get().toModelType());
    }

    @Override
    public void saveShortcut(ReadOnlyShortcut shortcut) throws IOException {
        saveShortcut(shortcut, filePath);
    }

    /**
     * Similar to {@link #saveShortcut(ReadOnlyShortcut)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveShortcut(ReadOnlyShortcut shortcut, Path filePath) throws IOException {
        requireNonNull(shortcut);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableShortcut(shortcut), filePath);
    }

}
