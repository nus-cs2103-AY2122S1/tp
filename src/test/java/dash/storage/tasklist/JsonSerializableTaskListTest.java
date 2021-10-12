package dash.storage.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import dash.commons.exceptions.IllegalValueException;
import dash.commons.util.JsonUtil;
import dash.model.task.TaskList;
import dash.testutil.Assert;
import dash.testutil.TypicalTasks;

class JsonSerializableTaskListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskListTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskList.json");
    private static final Path INVALID_TASKS_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskList.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTaskList.class).get();
        TaskList taskListFromFile = dataFromFile.toModelType();
        TaskList typicalTasksTaskList = TypicalTasks.getTypicalTaskList();
        assertEquals(taskListFromFile, typicalTasksTaskList);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_TASKS_FILE,
                JsonSerializableTaskList.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
