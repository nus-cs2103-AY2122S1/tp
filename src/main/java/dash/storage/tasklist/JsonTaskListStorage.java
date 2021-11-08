package dash.storage.tasklist;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import dash.commons.exceptions.DataConversionException;
import dash.commons.exceptions.IllegalValueException;
import dash.commons.util.FileUtil;
import dash.commons.util.JsonUtil;
import dash.model.task.TaskList;
import dash.storage.addressbook.JsonAddressBookStorage;

/**
 * A class to access TaskList data stored as a json file on the hard disk.
 */
public class JsonTaskListStorage implements TaskListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<TaskList> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList(Path)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<TaskList> readTaskList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);
        if (!jsonTaskList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        saveTaskList(taskList, filePath);
    }

    /**
     * Similar to {@link #saveTaskList(TaskList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskList(TaskList taskList, Path filePath) throws IOException {
        requireNonNull(taskList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(taskList), filePath);
    }

}
