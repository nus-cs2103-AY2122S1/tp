package seedu.teachbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.ReadOnlyUserPrefs;
import seedu.teachbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TeachBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTeachBookFilePath();

    @Override
    Optional<ReadOnlyTeachBook> readTeachBook() throws DataConversionException, IOException;

    @Override
    void saveTeachBook(ReadOnlyTeachBook addressBook) throws IOException;

}
