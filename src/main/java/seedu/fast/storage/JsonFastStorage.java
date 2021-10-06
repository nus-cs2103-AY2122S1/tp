package seedu.fast.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.commons.exceptions.DataConversionException;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.commons.util.FileUtil;
import seedu.fast.commons.util.JsonUtil;
import seedu.fast.model.ReadOnlyFast;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonFastStorage implements FastStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFastStorage.class);

    private Path filePath;

    public JsonFastStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFastFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFast> readFast() throws DataConversionException {
        return readFast(filePath);
    }

    /**
     * Similar to {@link #readFast()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFast> readFast(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFast> jsonFast = JsonUtil.readJsonFile(
                filePath, JsonSerializableFast.class);
        if (!jsonFast.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFast.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFast(ReadOnlyFast fast) throws IOException {
        saveFast(fast, filePath);
    }

    /**
     * Similar to {@link #saveFast(ReadOnlyFast)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFast(ReadOnlyFast fast, Path filePath) throws IOException {
        requireNonNull(fast);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFast(fast), filePath);
    }

}
