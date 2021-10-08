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
    private TeachingAssistantBuddyStorage TeachingAssistantBuddyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TeachingAssistantBuddyStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(TeachingAssistantBuddyStorage TeachingAssistantBuddyStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.TeachingAssistantBuddyStorage = TeachingAssistantBuddyStorage;
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
        return TeachingAssistantBuddyStorage.getTeachingAssistantBuddyFilePath();
    }

    @Override
    public Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy()
            throws DataConversionException, IOException {
        return readTeachingAssistantBuddy(TeachingAssistantBuddyStorage.getTeachingAssistantBuddyFilePath());
    }

    @Override
    public Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return TeachingAssistantBuddyStorage.readTeachingAssistantBuddy(filePath);
    }

    @Override
    public void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy) throws IOException {
        saveTeachingAssistantBuddy(assistantBuddy, TeachingAssistantBuddyStorage.getTeachingAssistantBuddyFilePath());
    }

    @Override
    public void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        TeachingAssistantBuddyStorage.saveTeachingAssistantBuddy(assistantBuddy, filePath);
    }

}
