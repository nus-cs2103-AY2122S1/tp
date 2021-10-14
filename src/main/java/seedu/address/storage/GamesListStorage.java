package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGamesList;

public interface GamesListStorage {
    /**
     * Returns the file path of the games list data file.
     */
    Path getGamesListFilePath();

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGamesList> readGamesList() throws DataConversionException, IOException;

    /**
     * @see #getGamesListFilePath()
     */
    Optional<ReadOnlyGamesList> readGamesList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     *
     * @param gamesList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGamesList(ReadOnlyGamesList gamesList) throws IOException;

    /**
     * @see #saveGamesList(ReadOnlyGamesList)
     */
    void saveGamesList(ReadOnlyGamesList gamesList, Path filePath) throws IOException;
}

