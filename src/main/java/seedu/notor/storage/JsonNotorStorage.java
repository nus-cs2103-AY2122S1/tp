package seedu.notor.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.notor.commons.core.LogsCenter;
import seedu.notor.commons.exceptions.DataConversionException;
import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.commons.util.FileUtil;
import seedu.notor.commons.util.JsonUtil;
import seedu.notor.model.ReadOnlyNotor;

/**
 * A class to access Notor data stored as a json file on the hard disk.
 */
public class JsonNotorStorage implements NotorStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNotorStorage.class);

    private final Path filePath;

    public JsonNotorStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNotorFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNotor> readNotor() throws DataConversionException {
        return readNotor(filePath);
    }

    /**
     * Similar to {@link #readNotor()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyNotor> readNotor(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableNotor> jsonNotor = JsonUtil.readJsonFile(
                filePath, JsonSerializableNotor.class);
        if (!jsonNotor.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNotor.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNotor(ReadOnlyNotor notor) throws IOException {
        saveNotor(notor, filePath);
    }

    /**
     * Similar to {@link #saveNotor(ReadOnlyNotor)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNotor(ReadOnlyNotor notor, Path filePath) throws IOException {
        requireNonNull(notor);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNotor(notor), filePath);
    }

}
