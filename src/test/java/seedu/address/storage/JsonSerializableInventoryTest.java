package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Inventory;
import seedu.address.testutil.TypicalItems;

public class JsonSerializableInventoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInventoryTest");
    private static final Path TYPICAL_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalItemInventory.json");
    private static final Path INVALID_ITEM_FILE = TEST_DATA_FOLDER.resolve("invalidItemInventory.json");
    private static final Path DUPLICATE_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateItemInventory.json");

    @Test
    public void toModelType_typicalItemsFile_success() throws Exception {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITEMS_FILE,
                JsonSerializableInventory.class).get();
        Inventory inventoryFromFile = dataFromFile.toModelType();
        Inventory typicalItemInventory = TypicalItems.getTypicalInventory();
        assertEquals(inventoryFromFile, typicalItemInventory);
    }

    @Test
    public void toModelType_invalidItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(INVALID_ITEM_FILE,
                JsonSerializableInventory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItems_throwsIllegalValueException() throws Exception {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITEM_FILE,
                JsonSerializableInventory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInventory.MESSAGE_DUPLICATE_ITEM,
                dataFromFile::toModelType);
    }

}
