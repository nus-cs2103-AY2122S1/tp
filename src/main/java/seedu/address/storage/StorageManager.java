package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TeachingAssistantBuddy data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TeachingAssistantBuddyStorage teachingAssistantBuddyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TeachingAssistantBuddyStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(TeachingAssistantBuddyStorage teachingAssistantBuddyStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.teachingAssistantBuddyStorage = teachingAssistantBuddyStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TeachingAssistantBuddy methods ==============================

    @Override
    public Path getTeachingAssistantBuddyFilePath() {
        return teachingAssistantBuddyStorage.getTeachingAssistantBuddyFilePath();
    }

    @Override
    public Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy()
            throws DataConversionException, IOException {
        return readTeachingAssistantBuddy(teachingAssistantBuddyStorage.getTeachingAssistantBuddyFilePath());
    }

    @Override
    public Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return teachingAssistantBuddyStorage.readTeachingAssistantBuddy(filePath);
    }

    @Override
    public void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy) throws IOException {
        saveTeachingAssistantBuddy(assistantBuddy, teachingAssistantBuddyStorage.getTeachingAssistantBuddyFilePath());
    }

    @Override
    public void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        teachingAssistantBuddyStorage.saveTeachingAssistantBuddy(assistantBuddy, filePath);
    }

}
