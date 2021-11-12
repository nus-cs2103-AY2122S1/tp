package seedu.teachbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.TeachBook;

/**
 * Represents a storage for {@link TeachBook}.
 */
public interface TeachBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTeachBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyTeachBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTeachBook> readTeachBook() throws DataConversionException, IOException;

    /**
     * @see #getTeachBookFilePath()
     */
    Optional<ReadOnlyTeachBook> readTeachBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTeachBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTeachBook(ReadOnlyTeachBook addressBook) throws IOException;

    /**
     * @see #saveTeachBook(ReadOnlyTeachBook)
     */
    void saveTeachBook(ReadOnlyTeachBook addressBook, Path filePath) throws IOException;

}
