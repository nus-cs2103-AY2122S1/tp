package seedu.tracker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tracker.commons.core.LogsCenter;
import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.ReadOnlyUserPrefs;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.UserPrefs;


/**
 * Manages storage of ModuleTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleTrackerStorage moduleTrackerStorage;
    private UserPrefsStorage userPrefsStorage;
    private UserInfoStorage userInfoStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ModuleTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleTrackerStorage moduleTrackerStorage,
                          UserPrefsStorage userPrefsStorage, UserInfoStorage userInfoStorage) {
        super();
        this.moduleTrackerStorage = moduleTrackerStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.userInfoStorage = userInfoStorage;
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

    // ================ UserInfo methods ==============================

    @Override
    public Path getUserInfoFilePath() {
        return userInfoStorage.getUserInfoFilePath();
    }

    @Override
    public Optional<UserInfo> readUserInfo() throws DataConversionException, IOException {
        return userInfoStorage.readUserInfo();
    }

    @Override
    public void saveUserInfo(ReadOnlyUserInfo userInfo) throws IOException {
        userInfoStorage.saveUserInfo(userInfo);
    }


    // ================ ModuleTracker methods ==============================

    @Override
    public Path getModuleTrackerFilePath() {
        return moduleTrackerStorage.getModuleTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleTracker> readModuleTracker() throws DataConversionException, IOException {
        return readModuleTracker(moduleTrackerStorage.getModuleTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleTracker> readModuleTracker(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleTrackerStorage.readModuleTracker(filePath);
    }

    @Override
    public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker) throws IOException {
        saveModuleTracker(moduleTracker, moduleTrackerStorage.getModuleTrackerFilePath());
    }

    @Override
    public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleTrackerStorage.saveModuleTracker(moduleTracker, filePath);
    }

}
