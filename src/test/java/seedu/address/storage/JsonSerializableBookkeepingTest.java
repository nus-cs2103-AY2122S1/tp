package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalBookkeeping.getTypicalBookkeeping;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.BookKeeping;

public class JsonSerializableBookkeepingTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableBookkeepingTest");
    private static final Path TYPICAL_BOOKKEEPING_FILE = TEST_DATA_FOLDER.resolve("typicalBookkeeping.json");

    @Test
    public void toModelType_typicalBookkeeping_success() throws Exception {
        JsonSerializableBookKeeping dataFromFile = JsonUtil.readJsonFile(TYPICAL_BOOKKEEPING_FILE,
                JsonSerializableBookKeeping.class).get();
        BookKeeping bookKeepingFromFile = dataFromFile.toModelType();
        BookKeeping typicalBookkeeping = getTypicalBookkeeping();
        assertEquals(bookKeepingFromFile, typicalBookkeeping);
    }

}
