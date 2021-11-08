package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Schedule data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ScheduleStorage scheduleStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ScheduleStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ScheduleStorage scheduleStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.scheduleStorage = scheduleStorage;
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


    // ================ Schedule methods ==============================

    @Override
    public Path getScheduleFilePath() {
        return scheduleStorage.getScheduleFilePath();
    }

    @Override
    public Optional<ReadOnlySchedule> readSchedule() throws DataConversionException, IOException {
        return readSchedule(scheduleStorage.getScheduleFilePath());
    }

    @Override
    public Optional<ReadOnlySchedule> readSchedule(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleStorage.readSchedule(filePath);
    }

    @Override
    public void saveSchedule(ReadOnlySchedule schedule) throws IOException {
        saveSchedule(schedule, scheduleStorage.getScheduleFilePath());
    }

    @Override
    public void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleStorage.saveSchedule(schedule, filePath);
    }

}
