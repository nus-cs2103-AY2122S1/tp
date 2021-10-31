package seedu.track2gather.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.track2gather.commons.core.LogsCenter;
import seedu.track2gather.commons.exceptions.DataConversionException;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.ReadOnlyUserPrefs;
import seedu.track2gather.model.UserPrefs;

/**
 * Manages storage of Track2Gather data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private Track2GatherStorage track2GatherStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code Track2GatherStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(Track2GatherStorage track2GatherStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.track2GatherStorage = track2GatherStorage;
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


    // ================ Track2Gather methods ==============================

    @Override
    public Path getTrack2GatherFilePath() {
        return track2GatherStorage.getTrack2GatherFilePath();
    }

    @Override
    public Optional<ReadOnlyTrack2Gather> readTrack2Gather() throws DataConversionException, IOException {
        return readTrack2Gather(track2GatherStorage.getTrack2GatherFilePath());
    }

    @Override
    public Optional<ReadOnlyTrack2Gather> readTrack2Gather(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return track2GatherStorage.readTrack2Gather(filePath);
    }

    @Override
    public void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather) throws IOException {
        saveTrack2Gather(track2Gather, track2GatherStorage.getTrack2GatherFilePath());
    }

    @Override
    public void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        track2GatherStorage.saveTrack2Gather(track2Gather, filePath);
    }

}
