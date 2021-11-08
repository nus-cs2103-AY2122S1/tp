package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HrManager data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HrManagerStorage hrManagerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HrManagerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HrManagerStorage hrManagerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.hrManagerStorage = hrManagerStorage;
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


    // ================ HrManager methods ==============================

    @Override
    public Path getHrManagerCandidatesFilePath() {
        return hrManagerStorage.getHrManagerCandidatesFilePath();
    }

    @Override
    public Path getHrManagerPositionsFilePath() {
        return hrManagerStorage.getHrManagerPositionsFilePath();
    }

    @Override
    public Path getHrManagerInterviewsFilePath() {
        return hrManagerStorage.getHrManagerInterviewsFilePath();
    }

    @Override
    public Optional<ReadOnlyHrManager> readHrManager() throws DataConversionException, IOException {
        return readHrManager(hrManagerStorage.getHrManagerCandidatesFilePath(),
                hrManagerStorage.getHrManagerPositionsFilePath(), hrManagerStorage.getHrManagerInterviewsFilePath());
    }

    @Override
    public Optional<ReadOnlyHrManager> readHrManager(Path candidatesFilePath, Path positionsFilepath,
                                                     Path interviewsFilePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read candidates data from file: " + candidatesFilePath);
        logger.fine("Attempting to read positions data from file: " + positionsFilepath);
        logger.fine("Attempting to read interviews data from file: " + interviewsFilePath);
        return hrManagerStorage.readHrManager(candidatesFilePath, positionsFilepath, interviewsFilePath);
    }

    @Override
    public void saveHrManager(ReadOnlyHrManager hrManager) throws IOException {
        saveHrManager(hrManager, hrManagerStorage.getHrManagerCandidatesFilePath(),
                hrManagerStorage.getHrManagerPositionsFilePath(), hrManagerStorage.getHrManagerInterviewsFilePath());
    }

    @Override
    public void saveHrManager(ReadOnlyHrManager hrManager, Path candidatesFilePath, Path positionsFilePath,
                              Path interviewsFilePath)
            throws IOException {
        logger.fine("Attempting to write to candidate data file: " + candidatesFilePath);
        logger.fine("Attempting to write to position data file: " + positionsFilePath);
        logger.fine("Attempting to write to interviews data file: " + interviewsFilePath);
        hrManagerStorage.saveHrManager(hrManager, candidatesFilePath, positionsFilePath, interviewsFilePath);
    }
}
