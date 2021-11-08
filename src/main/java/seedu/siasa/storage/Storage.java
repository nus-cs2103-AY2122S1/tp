package seedu.siasa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.ReadOnlyUserPrefs;
import seedu.siasa.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SiasaStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSiasaFilePath();

    @Override
    Optional<ReadOnlySiasa> readSiasa() throws DataConversionException, IOException;

    @Override
    void saveSiasa(ReadOnlySiasa siasa) throws IOException;

}
