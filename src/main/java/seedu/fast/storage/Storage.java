package seedu.fast.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fast.commons.exceptions.DataConversionException;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.ReadOnlyUserPrefs;
import seedu.fast.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FastStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFastFilePath();

    @Override
    Optional<ReadOnlyFast> readFast() throws DataConversionException, IOException;

    @Override
    void saveFast(ReadOnlyFast addressBook) throws IOException;

}
