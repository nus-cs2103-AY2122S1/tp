package seedu.tuitione.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tuitione.commons.exceptions.DataConversionException;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.ReadOnlyUserPrefs;
import seedu.tuitione.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TuitioneStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTuitioneFilePath();

    @Override
    Optional<ReadOnlyTuitione> readTuitione() throws DataConversionException, IOException;

    @Override
    void saveTuitione(ReadOnlyTuitione tuitione) throws IOException;

}
