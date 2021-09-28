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
 * A class to access AniList data stored as a json file on the hard disk.
 */
public class JsonAniListStorage implements AniListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAniListStorage.class);

    private Path filePath;

    public JsonAniListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAniListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAnimeList> readAniList() throws DataConversionException {
        return readAniList(filePath);
    }

    /**
     * Similar to {@link #readAniList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAnimeList> readAniList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAniList> jsonAniList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAniList.class);
        if (!jsonAniList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAniList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAniList(ReadOnlyAnimeList aniList) throws IOException {
        saveAniList(aniList, filePath);
    }

    /**
     * Similar to {@link #saveAniList(ReadOnlyAnimeList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAniList(ReadOnlyAnimeList aniList, Path filePath) throws IOException {
        requireNonNull(aniList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAniList(aniList), filePath);
    }

}
