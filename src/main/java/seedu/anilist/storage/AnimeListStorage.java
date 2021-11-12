package seedu.anilist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.anilist.commons.exceptions.DataConversionException;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;

/**
 * Represents a storage for {@link AnimeList}.
 */
public interface AnimeListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAnimeListFilePath();

    /**
     * Returns AnimeList data as a {@link ReadOnlyAnimeList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAnimeList> readAnimeList() throws DataConversionException, IOException;

    /**
     * @see #getAnimeListFilePath()
     */
    Optional<ReadOnlyAnimeList> readAnimeList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAnimeList} to the storage.
     * @param animeList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAnimeList(ReadOnlyAnimeList animeList) throws IOException;

    /**
     * @see #saveAnimeList(ReadOnlyAnimeList)
     */
    void saveAnimeList(ReadOnlyAnimeList animeList, Path filePath) throws IOException;

}
