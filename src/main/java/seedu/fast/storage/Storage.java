package seedu.fast.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fast.commons.exceptions.DataConversionException;
import seedu.fast.model.ReadOnlyAddressBook;
import seedu.fast.model.ReadOnlyUserPrefs;
import seedu.fast.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FastStorage, UserPrefsStorage {

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

}
