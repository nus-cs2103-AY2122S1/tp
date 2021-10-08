package seedu.tracker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.ReadOnlyUserPrefs;
import seedu.tracker.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModuleTrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getModuleTrackerFilePath();

    @Override
    Optional<ReadOnlyModuleTracker> readModuleTracker() throws DataConversionException, IOException;

    @Override
    void saveModuleTracker(ReadOnlyModuleTracker moduleTracker) throws IOException;

}
