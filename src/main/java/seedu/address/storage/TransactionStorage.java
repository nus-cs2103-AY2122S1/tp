package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTransactionList;

/**
 * Represents a storage for list of {@link seedu.address.model.TransactionList}.
 */
public interface TransactionStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTransactionFilePath();

    /**
     * Returns TransactionRecord List data as a {@link ReadOnlyTransactionList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTransactionList> readTransactionList() throws DataConversionException, IOException;

    /**
     * @see #getTransactionFilePath()
     */
    Optional<ReadOnlyTransactionList> readTransactionList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTransactionList} to the storage.
     * @param transactionList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTransactionList(ReadOnlyTransactionList transactionList) throws IOException;

    /**
     * @see #saveTransactionList(ReadOnlyTransactionList)
     */
    void saveTransactionList(ReadOnlyTransactionList transactionList, Path filePath) throws IOException;

}
