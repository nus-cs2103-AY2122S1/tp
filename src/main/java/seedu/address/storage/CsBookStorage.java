package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CsBook;
import seedu.address.model.ReadOnlyCsBook;

/**
 * Represents a storage for {@link CsBook}.
 */
public interface CsBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCsBookFilePath();

    /**
     * Returns CsBook data as a {@link ReadOnlyCsBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCsBook> readCsBook() throws DataConversionException, IOException;

    /**
     * @see #getCsBookFilePath()
     */
    Optional<ReadOnlyCsBook> readCsBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCsBook} to the storage.
     * @param csBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCsBook(ReadOnlyCsBook csBook) throws IOException;

    /**
     * @see #saveCsBook(ReadOnlyCsBook)
     */
    void saveCsBook(ReadOnlyCsBook csBook, Path filePath) throws IOException;

}
