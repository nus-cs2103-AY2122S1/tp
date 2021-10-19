package seedu.plannermd.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.plannermd.commons.core.LogsCenter;
import seedu.plannermd.commons.exceptions.DataConversionException;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.ReadOnlyUserPrefs;
import seedu.plannermd.model.UserPrefs;

/**
 * Manages storage of PlannerMd data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PlannerMdStorage plannerMdStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PlannerMdStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PlannerMdStorage plannerMdStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.plannerMdStorage = plannerMdStorage;
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


    // ================ PlannerMd methods ==============================

    @Override
    public Path getPlannerMdFilePath() {
        return plannerMdStorage.getPlannerMdFilePath();
    }

    @Override
    public Optional<ReadOnlyPlannerMd> readPlannerMd() throws DataConversionException, IOException {
        return readPlannerMd(plannerMdStorage.getPlannerMdFilePath());
    }

    @Override
    public Optional<ReadOnlyPlannerMd> readPlannerMd(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return plannerMdStorage.readPlannerMd(filePath);
    }

    @Override
    public void savePlannerMd(ReadOnlyPlannerMd plannerMd) throws IOException {
        savePlannerMd(plannerMd, plannerMdStorage.getPlannerMdFilePath());
    }

    @Override
    public void savePlannerMd(ReadOnlyPlannerMd plannerMd, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        plannerMdStorage.savePlannerMd(plannerMd, filePath);
    }

}
