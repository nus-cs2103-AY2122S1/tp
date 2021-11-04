package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.testutil.TypicalModules;

public class JsonSerializableAssistantBuddyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAssistantBuddyTest");
    private static final Path TYPICAL_BUDDY_FILE = TEST_DATA_FOLDER.resolve("typicalAssistantBuddy.json");
    private static final Path INVALID_BUDDY_FILE = TEST_DATA_FOLDER.resolve("invalidAssistantBuddy.json");
    private static final Path DUPLICATE_MODULES_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleAssistantBuddy.json");

    @Test
    public void toModelType_typicalBuddyFile_success() throws Exception {
        JsonSerializableAssistantBuddy dataFromFile = JsonUtil.readJsonFile(TYPICAL_BUDDY_FILE,
                JsonSerializableAssistantBuddy.class).get();
        TeachingAssistantBuddy assistantBuddyFromFile = dataFromFile.toModelType();
        TeachingAssistantBuddy typicalAssistantBuddy = TypicalModules.getTypicalBuddy();
        assertEquals(assistantBuddyFromFile, typicalAssistantBuddy);
    }

    @Test
    public void toModelType_invalidBuddyFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAssistantBuddy dataFromFile = JsonUtil.readJsonFile(INVALID_BUDDY_FILE,
                JsonSerializableAssistantBuddy.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableAssistantBuddy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULES_FILE,
                JsonSerializableAssistantBuddy.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAssistantBuddy.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

}
