package seedu.tracker.storage;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link UserInfo}.
 */
public interface UserInfoStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getUserInfoFilePath();

    /**
     * Returns UserInfo data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserInfo> readUserInfo() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.tracker.model.ReadOnlyUserInfo} to the storage.
     * @param userInfo cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserInfo(ReadOnlyUserInfo userInfo) throws IOException;
}
