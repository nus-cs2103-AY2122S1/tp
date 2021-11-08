package seedu.gamebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.gamebook.commons.exceptions.DataConversionException;
import seedu.gamebook.model.ReadOnlyGameBook;

/**
 * Represents a storage for {@link seedu.gamebook.model.GameBook}.
 */

public interface GameBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGameBookFilePath();

    /**
     * Returns GameBook data as a {@link ReadOnlyGameBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGameBook> readGameBook() throws DataConversionException, IOException;

    /**
     * @see #getGameBookFilePath()
     */
    Optional<ReadOnlyGameBook> readGameBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyGameBook} to the storage.
     * @param gameBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGameBook(ReadOnlyGameBook gameBook) throws IOException;

    /**
     * @see #saveGameBook(ReadOnlyGameBook)
     */
    void saveGameBook(ReadOnlyGameBook gameBook, Path filePath) throws IOException;

}
