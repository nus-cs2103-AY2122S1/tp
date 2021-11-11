package seedu.intern.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intern.commons.exceptions.DataConversionException;
import seedu.intern.model.ReadOnlyInternWatcher;
import seedu.intern.model.ReadOnlyUserPrefs;
import seedu.intern.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InternWatcherStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternWatcherFilePath();

    @Override
    Optional<ReadOnlyInternWatcher> readInternWatcher() throws DataConversionException, IOException;

    @Override
    void saveInternWatcher(ReadOnlyInternWatcher internWatcher) throws IOException;

}
