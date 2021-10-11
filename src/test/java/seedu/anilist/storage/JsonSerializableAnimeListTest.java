package seedu.anilist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.anilist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.anilist.commons.exceptions.IllegalValueException;
import seedu.anilist.commons.util.JsonUtil;
import seedu.anilist.model.AnimeList;
import seedu.anilist.testutil.TypicalAnimes;

public class JsonSerializableAnimeListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAnimeListTest");
    private static final Path TYPICAL_ANIMES_FILE = TEST_DATA_FOLDER.resolve("typicalAnimesAnimeList.json");
    private static final Path DUPLICATE_ANIME_FILE = TEST_DATA_FOLDER.resolve("duplicateAnimeAnimeList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAnimeList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ANIMES_FILE,
                JsonSerializableAnimeList.class).get();
        AnimeList animeListFromFile = dataFromFile.toModelType();
        AnimeList typicalAnimesAnimeList = TypicalAnimes.getTypicalAnimeList();
        assertEquals(animeListFromFile, typicalAnimesAnimeList);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAnimeList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ANIME_FILE,
                JsonSerializableAnimeList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAnimeList.MESSAGE_DUPLICATE_ANIME,
                dataFromFile::toModelType);
    }

}
