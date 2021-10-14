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
import seedu.address.model.ReadOnlyGamesList;

/**
 * A class to access games list data stored as a json file on the hard disk.
 */
public class JsonGamesListStorage implements GamesListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGamesListStorage.class);

    private final Path filePath;

    public JsonGamesListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getGamesListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGamesList> readGamesList() throws DataConversionException {
        return readGamesList(filePath);
    }

    /**
     * Similar to {@link #readGamesList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGamesList> readGamesList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGamesList> jsonGamesList = JsonUtil.readJsonFile(
                filePath, JsonSerializableGamesList.class);
        if (jsonGamesList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGamesList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGamesList(ReadOnlyGamesList gamesList) throws IOException {
        saveGamesList(gamesList, filePath);
    }

    /**
     * Similar to {@link #saveGamesList(ReadOnlyGamesList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGamesList(ReadOnlyGamesList gamesList, Path filePath) throws IOException {
        requireNonNull(gamesList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGamesList(gamesList), filePath);
    }

}

