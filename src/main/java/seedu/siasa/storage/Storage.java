package seedu.siasa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.ReadOnlyUserPrefs;
import seedu.siasa.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, PolicyBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlySiasa> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlySiasa addressBook) throws IOException;

    @Override
    Path getPolicyBookFilePath();

    @Override
    Optional<ReadOnlySiasa> readPolicyBook() throws DataConversionException, IOException;

    @Override
    void savePolicyBook(ReadOnlySiasa policyBook) throws IOException;

}
