package seedu.smartnus.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.commons.exceptions.DataConversionException;
import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.commons.util.FileUtil;
import seedu.smartnus.commons.util.JsonUtil;
import seedu.smartnus.model.ReadOnlySmartNus;

/**
 * A class to access SmartNus data stored as a json file on the hard disk.
 */
public class JsonSmartNusStorage implements SmartNusStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSmartNusStorage.class);

    private Path filePath;

    public JsonSmartNusStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSmartNusFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySmartNus> readSmartNus() throws DataConversionException {
        return readSmartNus(filePath);
    }

    /**
     * Similar to {@link #readSmartNus()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySmartNus> readSmartNus(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSmartNus> jsonSmartNus;
        try {
            jsonSmartNus = JsonUtil.readJsonFile(filePath, JsonSerializableSmartNus.class);
        } catch (NullPointerException e) { // user replaced file contents with null
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataConversionException(e);
        }

        if (!jsonSmartNus.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSmartNus.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSmartNus(ReadOnlySmartNus smartNus) throws IOException {
        saveSmartNus(smartNus, filePath);
    }

    /**
     * Similar to {@link #saveSmartNus(ReadOnlySmartNus)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSmartNus(ReadOnlySmartNus smartNus, Path filePath) throws IOException {
        requireNonNull(smartNus);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSmartNus(smartNus), filePath);
    }

}
