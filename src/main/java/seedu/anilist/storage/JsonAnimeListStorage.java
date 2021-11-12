package seedu.anilist.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.commons.exceptions.DataConversionException;
import seedu.anilist.commons.exceptions.IllegalValueException;
import seedu.anilist.commons.util.FileUtil;
import seedu.anilist.commons.util.JsonUtil;
import seedu.anilist.model.ReadOnlyAnimeList;

/**
 * A class to access AnimeList data stored as a json file on the hard disk.
 */
public class JsonAnimeListStorage implements AnimeListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAnimeListStorage.class);

    private Path filePath;

    public JsonAnimeListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAnimeListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAnimeList> readAnimeList() throws DataConversionException {
        return readAnimeList(filePath);
    }

    /**
     * Similar to {@link #readAnimeList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAnimeList> readAnimeList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAnimeList> jsonAnimeList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAnimeList.class);
        if (!jsonAnimeList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAnimeList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAnimeList(ReadOnlyAnimeList animeList) throws IOException {
        saveAnimeList(animeList, filePath);
    }

    /**
     * Similar to {@link #saveAnimeList(ReadOnlyAnimeList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAnimeList(ReadOnlyAnimeList animeList, Path filePath) throws IOException {
        requireNonNull(animeList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAnimeList(animeList), filePath);
    }

}
