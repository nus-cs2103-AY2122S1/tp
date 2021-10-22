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
import seedu.address.model.ReadOnlySalesOrderBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonSalesOrderBookStorage implements SalesOrderBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSalesOrderBookStorage.class);

    private Path filePath;

    public JsonSalesOrderBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getSalesOrderFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySalesOrderBook> readSalesOrderBook() throws DataConversionException, IOException {
        return readSalesOrderBook(filePath);
    }


    /**
     * Similar to {@link #readSalesOrderBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlySalesOrderBook> readSalesOrderBook(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableSalesOrderBook> jsonSalesOrderBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSalesOrderBook.class);
        if (!jsonSalesOrderBook.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonSalesOrderBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }


    @Override
    public void saveSalesOrderBook(ReadOnlySalesOrderBook salesOrderBook) throws IOException {
        saveSalesOrderBook(salesOrderBook, filePath);
    }

    /**
     * Similar to {@link #saveSalesOrderBook(ReadOnlySalesOrderBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveSalesOrderBook(ReadOnlySalesOrderBook salesOrderBook, Path filePath) throws IOException {
        requireNonNull(salesOrderBook);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSalesOrderBook(salesOrderBook), filePath);
    }

}
