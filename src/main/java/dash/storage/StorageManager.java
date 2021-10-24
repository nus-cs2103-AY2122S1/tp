package dash.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import dash.commons.exceptions.DataConversionException;
import dash.model.ReadOnlyAddressBook;
import dash.model.ReadOnlyUserPrefs;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.task.TaskList;
import dash.storage.addressbook.AddressBookStorage;
import dash.storage.tasklist.TaskListStorage;
import dash.storage.userinputlist.UserInputListStorage;
import dash.storage.userprefs.UserPrefsStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TaskListStorage taskListStorage;
    private UserInputListStorage userInputListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, TaskListStorage taskListStorage,
                          UserInputListStorage userInputListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.taskListStorage = taskListStorage;
        this.userInputListStorage = userInputListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ TaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<TaskList> readTaskList() throws DataConversionException, IOException {
        return readTaskList(taskListStorage.getTaskListFilePath());
    }

    @Override
    public Optional<TaskList> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskListStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        saveTaskList(taskList, taskListStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskList(TaskList taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskListStorage.saveTaskList(taskList, filePath);
    }

    // ================ UserInputList methods =========================

    @Override
    public Path getUserInputListFilePath() {
        return userInputListStorage.getUserInputListFilePath();
    }

    @Override
    public Optional<UserInputList> readUserInputList() throws DataConversionException, IOException {
        return readUserInputList(userInputListStorage.getUserInputListFilePath());
    }

    @Override
    public Optional<UserInputList> readUserInputList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return userInputListStorage.readUserInputList(filePath);
    }

    @Override
    public void saveUserInputList(UserInputList userInputList) throws IOException {
        userInputListStorage.saveUserInputList(userInputList, userInputListStorage.getUserInputListFilePath());
    }

    @Override
    public void saveUserInputList(UserInputList userInputList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        userInputListStorage.saveUserInputList(userInputList, filePath);
    }

}
