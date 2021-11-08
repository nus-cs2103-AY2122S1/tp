package seedu.gamebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.gamebook.commons.exceptions.DataConversionException;
import seedu.gamebook.model.ReadOnlyGameBook;
import seedu.gamebook.model.ReadOnlyUserPrefs;
import seedu.gamebook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends GameBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGameBookFilePath();

    @Override
    Optional<ReadOnlyGameBook> readGameBook() throws DataConversionException, IOException;

    @Override
    void saveGameBook(ReadOnlyGameBook gameBook) throws IOException;

}
