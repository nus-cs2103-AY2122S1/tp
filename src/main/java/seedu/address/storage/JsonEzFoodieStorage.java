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
import seedu.address.model.ReadOnlyEzFoodie;

/**
 * A class to access EzFoodie data stored as a json file on the hard disk.
 */
public class JsonEzFoodieStorage implements EzFoodieStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEzFoodieStorage.class);

    private Path filePath;

    /**
     * Constructs a {@code JsonEzFoodieStorage} with the given ezFoodie file path details.
     */
    public JsonEzFoodieStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets EzFoodie file path.
     *
     * @return Path of EzFoodie file.
     */
    public Path getEzFoodieFilePath() {
        return filePath;
    }

    /**
     * Returns ezFoodie data as a {@link ReadOnlyEzFoodie}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyEzFoodie> readEzFoodie() throws DataConversionException {
        return readEzFoodie(filePath);
    }

    /**
     * Similar to {@link #readEzFoodie()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEzFoodie> readEzFoodie(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEzFoodie> jsonEzFoodie = JsonUtil.readJsonFile(
                filePath, JsonSerializableEzFoodie.class);
        if (!jsonEzFoodie.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEzFoodie.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the given {@link ReadOnlyEzFoodie} to the storage.
     *
     * @param ezFoodie cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveEzFoodie(ReadOnlyEzFoodie ezFoodie) throws IOException {
        saveEzFoodie(ezFoodie, filePath);
    }

    /**
     * Similar to {@link #saveEzFoodie(ReadOnlyEzFoodie)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEzFoodie(ReadOnlyEzFoodie ezFoodie, Path filePath) throws IOException {
        requireNonNull(ezFoodie);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEzFoodie(ezFoodie), filePath);
    }

}
