package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendsList;
import seedu.address.model.ReadOnlyFriendsList;

/**
 * Represents a storage for {@link FriendsList}.
 */
public interface FriendsListStorage {

    /**
     * Returns the file path of the friends list file.
     */
    Path getFriendsListFilePath();

    /**
     * Returns gitGud friends list as a {@link ReadOnlyFriendsList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFriendsList> readFriendsList() throws DataConversionException, IOException;

    /**
     * @see #getFriendsListFilePath()
     */
    Optional<ReadOnlyFriendsList> readFriendsList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFriendsList} to the storage.
     *
     * @param friendsList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFriendsList(ReadOnlyFriendsList friendsList) throws IOException;

    /**
     * @see #saveFriendsList(ReadOnlyFriendsList)
     */
    void saveFriendsList(ReadOnlyFriendsList friendsList, Path filePath) throws IOException;
}
