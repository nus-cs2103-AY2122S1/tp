package seedu.smartnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.smartnus.commons.exceptions.DataConversionException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SmartNusStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSmartNusFilePath();

    @Override
    Optional<ReadOnlySmartNus> readSmartNus() throws DataConversionException, IOException;

    @Override
    void saveSmartNus(ReadOnlySmartNus smartNus) throws IOException;

}
