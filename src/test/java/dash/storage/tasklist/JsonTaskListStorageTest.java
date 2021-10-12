package dash.storage.tasklist;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dash.commons.exceptions.DataConversionException;
import dash.model.task.TaskList;
import dash.testutil.Assert;

class JsonTaskListStorageTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonTaskListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskList_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readTaskList(null));
    }

    private java.util.Optional<TaskList> readTaskList(String filePath) throws Exception {
        return new JsonTaskListStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readTaskList(
                "notJsonFormatTaskList.json"));
    }

    @Test
    public void readTaskList_invalidTaskTaskList_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readTaskList(
                "invalidTaskTaskList.json"));
    }

    @Test
    public void readTaskList_invalidAndValidTasksTaskList_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readTaskList(
                "invalidAndValidTasksTaskList.json"));
    }

}
