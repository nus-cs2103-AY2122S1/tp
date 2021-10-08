package seedu.unify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.unify.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.exceptions.IllegalValueException;
import seedu.unify.commons.util.JsonUtil;
import seedu.unify.model.UniFy;
import seedu.unify.testutil.TypicalTasks;

public class JsonSerializableUniFyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableUniFyTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksAddressBook.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskAddressBook.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskAddressBook.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableUniFy dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableUniFy.class).get();
        UniFy uniFyFromFile = dataFromFile.toModelType();
        UniFy typicalTasksUniFy = TypicalTasks.getTypicalAddressBook();
        assertEquals(uniFyFromFile, typicalTasksUniFy);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUniFy dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableUniFy.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableUniFy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableUniFy.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableUniFy.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
