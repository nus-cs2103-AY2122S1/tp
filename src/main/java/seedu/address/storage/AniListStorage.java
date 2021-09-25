package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AnimeList;
import seedu.address.model.ReadOnlyAnimeList;

/**
 * Represents a storage for {@link AnimeList}.
 */
public interface AniListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAniListFilePath();

    /**
     * Returns AniList data as a {@link ReadOnlyAnimeList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAnimeList> readAniList() throws DataConversionException, IOException;

    /**
     * @see #getAniListFilePath()
     */
    Optional<ReadOnlyAnimeList> readAniList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAnimeList} to the storage.
     * @param aniList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAniList(ReadOnlyAnimeList aniList) throws IOException;

    /**
     * @see #saveAniList(ReadOnlyAnimeList)
     */
    void saveAniList(ReadOnlyAnimeList aniList, Path filePath) throws IOException;

}
