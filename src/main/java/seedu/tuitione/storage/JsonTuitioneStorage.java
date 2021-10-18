package seedu.tuitione.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tuitione.commons.core.LogsCenter;
import seedu.tuitione.commons.exceptions.DataConversionException;
import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.commons.util.FileUtil;
import seedu.tuitione.commons.util.JsonUtil;
import seedu.tuitione.model.ReadOnlyTuitione;

/**
 * A class to access Tuitione data stored as a json file on the hard disk.
 */
public class JsonTuitioneStorage implements TuitioneStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTuitioneStorage.class);

    private Path filePath;

    public JsonTuitioneStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTuitioneFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTuitione> readTuitione() throws DataConversionException {
        return readTuitione(filePath);
    }

    /**
     * Similar to {@link #readTuitione()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTuitione> readTuitione(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTuitione> jsonTuitione = JsonUtil.readJsonFile(
                filePath, JsonSerializableTuitione.class);
        if (!jsonTuitione.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTuitione.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTuitione(ReadOnlyTuitione tuitione) throws IOException {
        saveTuitione(tuitione, filePath);
    }

    /**
     * Similar to {@link #saveTuitione(ReadOnlyTuitione)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTuitione(ReadOnlyTuitione tuitione, Path filePath) throws IOException {
        requireNonNull(tuitione);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTuitione(tuitione), filePath);
    }

}
