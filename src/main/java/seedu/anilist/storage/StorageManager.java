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
    private AniListStorage aniListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AniListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AniListStorage aniListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.aniListStorage = aniListStorage;
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
    public Path getAniListFilePath() {
        return aniListStorage.getAniListFilePath();
    }

    @Override
    public Optional<ReadOnlyAnimeList> readAniList() throws DataConversionException, IOException {
        return readAniList(aniListStorage.getAniListFilePath());
    }

    @Override
    public Optional<ReadOnlyAnimeList> readAniList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return aniListStorage.readAniList(filePath);
    }

    @Override
    public void saveAniList(ReadOnlyAnimeList aniList) throws IOException {
        saveAniList(aniList, aniListStorage.getAniListFilePath());
    }

    @Override
    public void saveAniList(ReadOnlyAnimeList aniList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        aniListStorage.saveAniList(aniList, filePath);
    }

}
