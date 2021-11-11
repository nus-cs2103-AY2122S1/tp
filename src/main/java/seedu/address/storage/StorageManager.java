package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternship;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Internship data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternshipStorage internshipStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternshipStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternshipStorage internshipStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.internshipStorage = internshipStorage;
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


    // ================ Internship methods ==============================

    @Override
    public Path getInternshipFilePath() {
        return internshipStorage.getInternshipFilePath();
    }

    @Override
    public Optional<ReadOnlyInternship> readInternship() throws DataConversionException, IOException {
        return readInternship(internshipStorage.getInternshipFilePath());
    }

    @Override
    public Optional<ReadOnlyInternship> readInternship(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipStorage.readInternship(filePath);
    }

    @Override
    public void saveInternship(ReadOnlyInternship internship) throws IOException {
        saveInternship(internship, internshipStorage.getInternshipFilePath());
    }

    @Override
    public void saveInternship(ReadOnlyInternship internship, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipStorage.saveInternship(internship, filePath);
    }

}
