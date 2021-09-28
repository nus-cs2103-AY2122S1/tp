package seedu.anilist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.anilist.commons.exceptions.DataConversionException;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.ReadOnlyUserPrefs;
import seedu.anilist.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AniListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAniListFilePath();

    @Override
    Optional<ReadOnlyAnimeList> readAniList() throws DataConversionException, IOException;

    @Override
    void saveAniList(ReadOnlyAnimeList aniList) throws IOException;

}
