package seedu.unify.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.unify.commons.core.LogsCenter;
import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.commons.exceptions.IllegalValueException;
import seedu.unify.commons.util.FileUtil;
import seedu.unify.commons.util.JsonUtil;
import seedu.unify.model.ReadOnlyUniFy;

/**
 * A class to access UniFy data stored as a json file on the hard disk.
 */
public class JsonUniFyStorage implements UniFyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniFyStorage.class);

    private Path filePath;

    public JsonUniFyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUniFyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniFy> readUniFy() throws DataConversionException {
        return readUniFy(filePath);
    }

    /**
     * Similar to {@link #readUniFy()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUniFy> readUniFy(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUniFy> jsonUniFy = JsonUtil.readJsonFile(
                filePath, JsonSerializableUniFy.class);
        if (!jsonUniFy.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUniFy.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUniFy(ReadOnlyUniFy uniFy) throws IOException {
        saveUniFy(uniFy, filePath);
    }

    /**
     * Similar to {@link #saveUniFy(seedu.unify.model.ReadOnlyUniFy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUniFy(ReadOnlyUniFy uniFy, Path filePath) throws IOException {
        requireNonNull(uniFy);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUniFy(uniFy), filePath);
    }

}
