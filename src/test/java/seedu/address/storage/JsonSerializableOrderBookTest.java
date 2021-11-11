package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.OrderBook;
import seedu.address.testutil.TypicalOrders;

public class JsonSerializableOrderBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableOrderBookTest");
    private static final Path TYPICAL_ORDERS_FILE = TEST_DATA_FOLDER.resolve("typicalOrdersOrderBook.json");
    private static final Path INVALID_ORDERS_FILE = TEST_DATA_FOLDER.resolve("invalidOrdersOrderBook.json");
    private static final Path DUPLICATE_ORDER_FILE = TEST_DATA_FOLDER.resolve("duplicateOrdersAddressBook.json");


    @Test
    public void toModelType_typicalOrderFile_success() throws Exception {
        OrderBook typicalOrdersOrderBook = TypicalOrders.getTypicalOrderBook();
        JsonSerializableOrderBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ORDERS_FILE,
                JsonSerializableOrderBook.class).get();
        OrderBook orderBookBookFromFile = dataFromFile.toModelType();
        assertEquals(orderBookBookFromFile, typicalOrdersOrderBook);
    }

    @Test
    public void toModelType_invalidOrderFile_throwsIllegalValueException() throws Exception {
        JsonSerializableOrderBook dataFromFile = JsonUtil.readJsonFile(INVALID_ORDERS_FILE,
                JsonSerializableOrderBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateOrders_throwsIllegalValueException() throws Exception {
        JsonSerializableOrderBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORDER_FILE,
                JsonSerializableOrderBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableOrderBook.MESSAGE_DUPLICATE_ORDER_ID,
                dataFromFile::toModelType);
    }

}
