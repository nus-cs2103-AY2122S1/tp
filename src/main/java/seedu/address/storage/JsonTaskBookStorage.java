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
import seedu.address.model.ReadOnlyTaskBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonTaskBookStorage implements TaskBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskBookStorage.class);

    private Path filePath;

    public JsonTaskBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskList() throws DataConversionException, IOException {
        return readTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyTaskBook> readTaskList(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskBook> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskBook.class);
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
    public void saveTaskBook(ReadOnlyTaskBook addressBook) throws IOException {
        saveTaskBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveTaskBook(ReadOnlyTaskBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskList, Path filePath) throws IOException {
        requireNonNull(taskList);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskBook(taskList), filePath);
    }
}
