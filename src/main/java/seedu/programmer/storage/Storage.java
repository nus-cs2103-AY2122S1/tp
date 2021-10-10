package seedu.programmer.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.ReadOnlyUserPrefs;
import seedu.programmer.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyProgrammerError> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyProgrammerError addressBook) throws IOException;

}
