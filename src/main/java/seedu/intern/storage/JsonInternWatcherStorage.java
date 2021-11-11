package seedu.intern.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.intern.commons.core.LogsCenter;
import seedu.intern.commons.exceptions.DataConversionException;
import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.commons.util.FileUtil;
import seedu.intern.commons.util.JsonUtil;
import seedu.intern.model.ReadOnlyInternWatcher;

/**
 * A class to access InternWatcher data stored as a json file on the hard disk.
 */
public class JsonInternWatcherStorage implements InternWatcherStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternWatcherStorage.class);

    private Path filePath;

    public JsonInternWatcherStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternWatcherFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternWatcher> readInternWatcher() throws DataConversionException {
        return readInternWatcher(filePath);
    }

    /**
     * Similar to {@link #readInternWatcher()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInternWatcher> readInternWatcher(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternWatcher> jsonInternWatcher = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternWatcher.class);
        if (!jsonInternWatcher.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternWatcher.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInternWatcher(ReadOnlyInternWatcher internWatcher) throws IOException {
        saveInternWatcher(internWatcher, filePath);
    }

    /**
     * Similar to {@link #saveInternWatcher(ReadOnlyInternWatcher)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternWatcher(ReadOnlyInternWatcher internWatcher, Path filePath) throws IOException {
        requireNonNull(internWatcher);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternWatcher(internWatcher), filePath);
    }

}
