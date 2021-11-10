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
import seedu.address.model.ReadOnlyCountdown;

/**
 * A class to access Countdown data stored as a json file on the hard disk.
 */
public class JsonCountdownStorage implements CountdownStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCountdownStorage.class);

    private Path filePath;

    public JsonCountdownStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCountdownFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCountdown> readCountdown() throws DataConversionException {
        return readCountdown(filePath);
    }

    /**
     * Similar to {@link #readCountdown()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCountdown> readCountdown(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCountdown> jsonCountdown = JsonUtil.readJsonFile(
                filePath, JsonSerializableCountdown.class);
        if (!jsonCountdown.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCountdown.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCountdown(ReadOnlyCountdown countdown) throws IOException {
        saveCountdown(countdown, filePath);
    }

    /**
     * Similar to {@link #saveCountdown(ReadOnlyCountdown)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCountdown(ReadOnlyCountdown countdown, Path filePath) throws IOException {
        requireNonNull(countdown);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCountdown(countdown), filePath);
    }

}
