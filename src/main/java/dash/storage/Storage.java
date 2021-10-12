package dash.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import dash.commons.exceptions.DataConversionException;
import dash.model.ReadOnlyAddressBook;
import dash.model.ReadOnlyUserPrefs;
import dash.model.UserPrefs;
import dash.model.task.TaskList;
import dash.storage.addressbook.AddressBookStorage;
import dash.storage.tasklist.TaskListStorage;
import dash.storage.userprefs.UserPrefsStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, TaskListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<TaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(TaskList taskList) throws IOException;
}
