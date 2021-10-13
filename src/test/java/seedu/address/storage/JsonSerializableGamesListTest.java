package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.GamesList;
import seedu.address.testutil.TypicalGames;

public class JsonSerializableGamesListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableGamesListTest");
    private static final Path TYPICAL_GAMES_LIST_FILE = TEST_DATA_FOLDER.resolve("typicalGamesList.json");
    private static final Path INVALID_GAMES_LIST_FILE = TEST_DATA_FOLDER.resolve("invalidGamesList.json");
    private static final Path DUPLICATE_GAMES_IN_GAMES_LIST_FILE =
            TEST_DATA_FOLDER.resolve("duplicateGameIdsInGamesList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableGamesList dataFromFile = JsonUtil.readJsonFile(TYPICAL_GAMES_LIST_FILE,
                JsonSerializableGamesList.class).get();
        GamesList gamesListFromFile = dataFromFile.toModelType();
        GamesList typicalPersonsGamesList = TypicalGames.getTypicalGamesList();
        assertEquals(gamesListFromFile, typicalPersonsGamesList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGamesList dataFromFile = JsonUtil.readJsonFile(INVALID_GAMES_LIST_FILE,
                JsonSerializableGamesList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableGamesList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GAMES_IN_GAMES_LIST_FILE,
                JsonSerializableGamesList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGamesList.MESSAGE_DUPLICATE_GAME,
                dataFromFile::toModelType);
    }
}
