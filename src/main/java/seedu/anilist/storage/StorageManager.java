package seedu.anilist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.commons.exceptions.DataConversionException;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.ReadOnlyUserPrefs;
import seedu.anilist.model.UserPrefs;

/**
 * Manages storage of AniList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AnimeListStorage animeListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AnimeListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AnimeListStorage animeListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.animeListStorage = animeListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AniList methods ==============================

    @Override
    public Path getAnimeListFilePath() {
        return animeListStorage.getAnimeListFilePath();
    }

    @Override
    public Optional<ReadOnlyAnimeList> readAnimeList() throws DataConversionException, IOException {
        return readAnimeList(animeListStorage.getAnimeListFilePath());
    }

    @Override
    public Optional<ReadOnlyAnimeList> readAnimeList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return animeListStorage.readAnimeList(filePath);
    }

    @Override
    public void saveAnimeList(ReadOnlyAnimeList animeList) throws IOException {
        saveAnimeList(animeList, animeListStorage.getAnimeListFilePath());
    }

    @Override
    public void saveAnimeList(ReadOnlyAnimeList animeList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        animeListStorage.saveAnimeList(animeList, filePath);
    }

}
