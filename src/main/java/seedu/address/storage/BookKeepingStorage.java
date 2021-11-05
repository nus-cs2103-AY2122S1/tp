package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBookKeeping;
import seedu.address.model.ReadOnlyTransactionList;

/**
 * Represents a storage for {@link seedu.address.model.BookKeeping}.
 */
public interface BookKeepingStorage {

    /**
     * Returns the file path of the BookKeeping file.
     */
    Path getBookKeepingPath();

    /**
     * Returns TransactionRecord List data as a {@link ReadOnlyTransactionList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBookKeeping> readBookKeeping() throws DataConversionException, IOException;

    /**
     * @see #getBookKeepingPath()
     */
    Optional<ReadOnlyBookKeeping> readBookKeeping(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBookKeeping} to the storage.
     * @param bookKeeping cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBookKeeping(ReadOnlyBookKeeping bookKeeping) throws IOException;

    /**
     * @see #saveBookKeeping(ReadOnlyBookKeeping)
     */
    void saveBookKeeping(ReadOnlyBookKeeping bookKeeping, Path filePath) throws IOException;
}
