package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCountdown;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;


/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, CountdownStorage, UserPrefsStorage, ShortcutStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Path getCountdownFilePath();

    @Override
    Path getShortcutFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyCountdown> readCountdown() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyShortcut> readShortcut() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    void saveCountdown(ReadOnlyCountdown countdown) throws IOException;

    @Override
    void saveShortcut(ReadOnlyShortcut shortcut) throws IOException;

}
