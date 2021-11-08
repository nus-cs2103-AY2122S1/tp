package seedu.edrecord.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.ReadOnlyUserPrefs;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;

/**
 * Manages storage of EdRecord data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EdRecordStorage edRecordStorage;
    private ModuleSystemStorage moduleSystemStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EdRecordStorage}, {@code ModuleSystemStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(EdRecordStorage edRecordStorage, ModuleSystemStorage moduleSystemStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.edRecordStorage = edRecordStorage;
        this.moduleSystemStorage = moduleSystemStorage;
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


    // ================ EdRecord methods ==============================

    @Override
    public Path getEdRecordFilePath() {
        return edRecordStorage.getEdRecordFilePath();
    }

    @Override
    public Optional<ReadOnlyEdRecord> readEdRecord() throws DataConversionException, IOException {
        return readEdRecord(edRecordStorage.getEdRecordFilePath());
    }

    @Override
    public Optional<ReadOnlyEdRecord> readEdRecord(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return edRecordStorage.readEdRecord(filePath);
    }

    @Override
    public void saveEdRecord(ReadOnlyEdRecord edRecord) throws IOException {
        saveEdRecord(edRecord, edRecordStorage.getEdRecordFilePath());
    }

    @Override
    public void saveEdRecord(ReadOnlyEdRecord edRecord, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        edRecordStorage.saveEdRecord(edRecord, filePath);
    }

    // ================ ModuleSystem methods ==============================

    @Override
    public Path getModuleSystemFilePath() {
        return moduleSystemStorage.getModuleSystemFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleSystem> readModuleSystem() throws DataConversionException, IOException {
        return readModuleSystem(moduleSystemStorage.getModuleSystemFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleSystem> readModuleSystem(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleSystemStorage.readModuleSystem(filePath);
    }

    @Override
    public void saveModuleSystem(ReadOnlyModuleSystem moduleSystem) throws IOException {
        saveModuleSystem(moduleSystem, moduleSystemStorage.getModuleSystemFilePath());
    }

    @Override
    public void saveModuleSystem(ReadOnlyModuleSystem moduleSystem, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleSystemStorage.saveModuleSystem(moduleSystem, filePath);
    }
}
