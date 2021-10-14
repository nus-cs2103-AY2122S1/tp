package seedu.notor.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.notor.commons.exceptions.DataConversionException;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.ReadOnlyUserPrefs;
import seedu.notor.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends NotorStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getNotorFilePath();

    @Override
    Optional<ReadOnlyNotor> readNotor() throws DataConversionException, IOException;

    @Override
    void saveNotor(ReadOnlyNotor notor) throws IOException;

}
