package socialite.storage;

import java.io.IOException;
import java.util.Optional;

import socialite.commons.exceptions.DataConversionException;
import socialite.model.ReadOnlyCommandHistory;
import socialite.model.ReadOnlyContactList;
import socialite.model.ReadOnlyUserPrefs;
import socialite.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ContactListStorage, UserPrefsStorage, CommandHistoryStorage, ProfilePictureStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException;

    @Override
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

    @Override
    Optional<ReadOnlyCommandHistory> readCommandHistory() throws DataConversionException, IOException;

    @Override
    void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException;
}
