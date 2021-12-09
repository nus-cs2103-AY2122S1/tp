package seedu.times.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.times.commons.exceptions.DataConversionException;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.ReadOnlyUserPrefs;
import seedu.times.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TimesTableStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTimesTableFilePath();

    @Override
    Optional<ReadOnlyTimesTable> readTimesTable() throws DataConversionException, IOException;

    @Override
    void saveTimesTable(ReadOnlyTimesTable timesTable) throws IOException;

}
