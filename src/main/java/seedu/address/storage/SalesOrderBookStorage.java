package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySalesOrderBook;

/**
 * Represents a storage for {@link seedu.address.model.SalesOrderBook}.
 */
public interface SalesOrderBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSalesOrderFilePath();

    /**
     * Returns TaskList data as a {@link ReadOnlySalesOrderBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySalesOrderBook> readSalesOrderBook() throws DataConversionException, IOException;

    /**
     * @see #getSalesOrderFilePath()
     */
    Optional<ReadOnlySalesOrderBook> readSalesOrderBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySalesOrderBook} to the storage.
     * @param salesOrderBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSalesOrderBook(ReadOnlySalesOrderBook salesOrderBook) throws IOException;

    /**
     * @see #saveSalesOrderBook(ReadOnlySalesOrderBook)
     */
    void saveSalesOrderBook(ReadOnlySalesOrderBook salesOrderBook, Path filePath) throws IOException;

}
