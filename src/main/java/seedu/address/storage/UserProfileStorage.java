package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.person.Person;

public interface UserProfileStorage {

    /**
     * Returns the file path of the user profile file.
     */
    Path getUserProfilePath();

    /**
     * Returns Person data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<Person> readUserProfile() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.address.storage.JsonSerializableUserProfile} to the storage.
     *
     * @param profile cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserProfile(JsonSerializableUserProfile profile) throws IOException;

}
