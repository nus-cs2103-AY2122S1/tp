package seedu.edrecord.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.ReadOnlyUserPrefs;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;

/**
 * API of the Storage component
 */
public interface Storage extends EdRecordStorage, ModuleSystemStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getEdRecordFilePath();

    @Override
    Optional<ReadOnlyEdRecord> readEdRecord() throws DataConversionException, IOException;

    @Override
    void saveEdRecord(ReadOnlyEdRecord edRecord) throws IOException;

    @Override
    Path getModuleSystemFilePath();

    @Override
    Optional<ReadOnlyModuleSystem> readModuleSystem() throws DataConversionException, IOException;

    @Override
    void saveModuleSystem(ReadOnlyModuleSystem moduleSystem) throws IOException;

}
