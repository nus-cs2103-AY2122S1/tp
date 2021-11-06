package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.person.Person;

public class JsonUserProfileStorage implements UserProfileStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUserProfileStorage.class);
    private Path filePath = Paths.get("userprofile.json");

    public JsonUserProfileStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserProfilePath() {
        return filePath;
    }

    /**
     * Returns Person data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<Person> readUserProfile() throws DataConversionException {
        return readUserProfile(filePath);
    }

    /**
     * Similar to {@link #readUserProfile()}
     *
     * @param profileFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<Person> readUserProfile(Path profileFilePath) throws DataConversionException {
        Optional<JsonSerializableUserProfile> userProfile = JsonUtil.readJsonFile(profileFilePath,
                JsonSerializableUserProfile.class);

        if (userProfile.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(userProfile.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the given {@link seedu.address.model.person.Person} to the storage.
     *
     * @param profile cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    public void saveUserProfile(JsonSerializableUserProfile profile) throws IOException {
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(profile, filePath);
    }
}
