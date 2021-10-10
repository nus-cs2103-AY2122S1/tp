package seedu.programmer.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.ReadOnlyUserPrefs;
import seedu.programmer.model.UserPrefs;

/**
 * Manages storage of ProgrammerError data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ProgrammerErrorStorage programmerErrorStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ProgrammerErrorStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ProgrammerErrorStorage programmerErrorStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.programmerErrorStorage = programmerErrorStorage;
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


    // ================ ProgrammerError methods =========================

    @Override
    public Path getProgrammerErrorFilePath() {
        return programmerErrorStorage.getProgrammerErrorFilePath();
    }

    @Override
    public Optional<ReadOnlyProgrammerError> readProgrammerError() throws DataConversionException, IOException {
        return readProgrammerError(programmerErrorStorage.getProgrammerErrorFilePath());
    }

    @Override
    public Optional<ReadOnlyProgrammerError> readProgrammerError(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return programmerErrorStorage.readProgrammerError(filePath);
    }

    @Override
    public void saveProgrammerError(ReadOnlyProgrammerError programmerError) throws IOException {
        saveProgrammerError(programmerError, programmerErrorStorage.getProgrammerErrorFilePath());
    }

    @Override
    public void saveProgrammerError(ReadOnlyProgrammerError programmerError, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        programmerErrorStorage.saveProgrammerError(programmerError, filePath);
    }

}
