package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyRhrh;

/**
 * A class to access Rhrh data stored as a json file on the hard disk.
 */
public class JsonRhrhStorage implements RhrhStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRhrhStorage.class);

    private Path filePath;

    public JsonRhrhStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRhrhFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRhrh> readRhrh() throws DataConversionException {
        return readRhrh(filePath);
    }

    /**
     * Similar to {@link #readRhrh()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRhrh> readRhrh(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRhrh> jsonRhrh = JsonUtil.readJsonFile(
                filePath, JsonSerializableRhrh.class);
        if (!jsonRhrh.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRhrh.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRhrh(ReadOnlyRhrh rhrh) throws IOException {
        saveRhrh(rhrh, filePath);
    }

    /**
     * Similar to {@link #saveRhrh(ReadOnlyRhrh)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRhrh(ReadOnlyRhrh rhrh, Path filePath) throws IOException {
        requireNonNull(rhrh);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRhrh(rhrh), filePath);
    }

}
