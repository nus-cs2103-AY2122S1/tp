package manageme.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import manageme.commons.core.LogsCenter;
import manageme.commons.exceptions.DataConversionException;
import manageme.model.ReadOnlyManageMe;
import manageme.model.ReadOnlyUserPrefs;
import manageme.model.UserPrefs;

/**
 * Manages storage of ManageMe data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ManageMeStorage manageMeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ManageMeStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ManageMeStorage manageMeStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.manageMeStorage = manageMeStorage;
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


    // ================ ManageMe methods ==============================

    @Override
    public Path getManageMeFilePath() {
        return manageMeStorage.getManageMeFilePath();
    }

    @Override
    public Optional<ReadOnlyManageMe> readManageMe() throws DataConversionException, IOException {
        return readManageMe(manageMeStorage.getManageMeFilePath());
    }

    @Override
    public Optional<ReadOnlyManageMe> readManageMe(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return manageMeStorage.readManageMe(filePath);
    }

    @Override
    public void saveManageMe(ReadOnlyManageMe manageMe) throws IOException {
        saveManageMe(manageMe, manageMeStorage.getManageMeFilePath());
    }

    @Override
    public void saveManageMe(ReadOnlyManageMe manageMe, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        manageMeStorage.saveManageMe(manageMe, filePath);
    }

}
