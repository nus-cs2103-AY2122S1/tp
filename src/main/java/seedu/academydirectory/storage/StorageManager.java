package seedu.academydirectory.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.commons.exceptions.DataConversionException;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;
import seedu.academydirectory.model.ReadOnlyUserPrefs;
import seedu.academydirectory.model.UserPrefs;
import seedu.academydirectory.versioncontrol.objects.VcObject;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

/**
 * Manages storage of AcademyDirectory data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AcademyDirectoryStorage academyDirectoryStorage;
    private UserPrefsStorage userPrefsStorage;
    private StageAreaStorage stageAreaStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AcademyDirectoryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AcademyDirectoryStorage academyDirectoryStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.academyDirectoryStorage = academyDirectoryStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.stageAreaStorage = new StageAreaStorage(HashMethod.SHA1, Paths.get("vc"));
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


    // ================ AcademyDirectory methods ==============================

    @Override
    public Path getAcademyDirectoryFilePath() {
        return academyDirectoryStorage.getAcademyDirectoryFilePath();
    }

    @Override
    public Optional<ReadOnlyAcademyDirectory> readAcademyDirectory() throws DataConversionException, IOException {
        return readAcademyDirectory(academyDirectoryStorage.getAcademyDirectoryFilePath());
    }

    @Override
    public Optional<ReadOnlyAcademyDirectory> readAcademyDirectory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return academyDirectoryStorage.readAcademyDirectory(filePath);
    }

    @Override
    public void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory) throws IOException {
        saveAcademyDirectory(academyDirectory, academyDirectoryStorage.getAcademyDirectoryFilePath());
    }

    @Override
    public void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        academyDirectoryStorage.saveAcademyDirectory(academyDirectory, filePath);
    }

    @Override
    public void saveStageArea(List<VcObject> stageArea) throws IOException {
        logger.fine("Attempt to write stage area to file: ");
        stageAreaStorage.saveStageArea(stageArea);
    }

}
