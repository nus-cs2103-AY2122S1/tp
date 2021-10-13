package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.VALORANT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GamesList;
import seedu.address.model.ReadOnlyGamesList;

public class JsonGamesListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonGamesListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void getGamesListFilePath_validFilePath_returnsCorrectFilePath() {
        JsonGamesListStorage jsonGamesListStorage =
                new JsonGamesListStorage(TEST_DATA_FOLDER.resolve("testFilePath.json"));
        assertEquals(TEST_DATA_FOLDER.resolve("testFilePath.json"), jsonGamesListStorage.getGamesListFilePath());
    }

    @Test
    public void readGamesList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGamesList(null));
    }

    private java.util.Optional<ReadOnlyGamesList> readGamesList(String filePath) throws Exception {
        return new JsonGamesListStorage(Paths.get(filePath)).readGamesList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGamesList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readGamesList("notJsonFormatGamesList.json"));
    }

    @Test
    public void readGamesList_invalidGamesList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGamesList("invalidGamesList.json"));
    }

    @Test
    public void readGamesList_invalidAndValidGamesList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGamesList("invalidAndValidGamesList.json"));
    }

    @Test
    public void readTwoGamesWithDefinedFilePath_validGamesListWithTwoGames_success() throws Exception {
        Path filePath = TEST_DATA_FOLDER.resolve("validGamesList.json");
        JsonGamesListStorage jsonGamesListStorage = new JsonGamesListStorage(filePath);
        Optional<ReadOnlyGamesList> readResult = jsonGamesListStorage.readGamesList();
        ReadOnlyGamesList actualList = readResult.get();
        assertEquals("2 games", actualList.toString());
    }

    @Test
    public void readAndSaveGamesList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGamesList.json");
        GamesList original = getTypicalGamesList();
        JsonGamesListStorage jsonGamesListStorage = new JsonGamesListStorage(filePath);

        // Save in new file and read back
        jsonGamesListStorage.saveGamesList(original, filePath);
        ReadOnlyGamesList readBack = jsonGamesListStorage.readGamesList(filePath).get();
        assertEquals(original, new GamesList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGame(APEX_LEGENDS);
        original.removeGame(VALORANT);
        jsonGamesListStorage.saveGamesList(original, filePath);
        readBack = jsonGamesListStorage.readGamesList(filePath).get();
        assertEquals(original, new GamesList(readBack));

        // Save and read without specifying file path
        original.addGame(VALORANT);
        jsonGamesListStorage.saveGamesList(original); // file path not specified
        readBack = jsonGamesListStorage.readGamesList().get(); // file path not specified
        assertEquals(original, new GamesList(readBack));

    }

    @Test
    public void saveGamesList_nullGamesList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGamesList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code gamesList} at the specified {@code filePath}.
     */
    private void saveGamesList(ReadOnlyGamesList gamesList, String filePath) {
        try {
            new JsonGamesListStorage(Paths.get(filePath))
                    .saveGamesList(gamesList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGamesList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGamesList(new GamesList(), null));
    }
}
