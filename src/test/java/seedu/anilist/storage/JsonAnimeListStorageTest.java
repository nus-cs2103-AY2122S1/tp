package seedu.anilist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.ALICE;
import static seedu.anilist.testutil.TypicalAnimes.HOON;
import static seedu.anilist.testutil.TypicalAnimes.IDA;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.anilist.commons.exceptions.DataConversionException;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;

public class JsonAnimeListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAnimeListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAnimeList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAnimeList(null));
    }

    private java.util.Optional<ReadOnlyAnimeList> readAnimeList(String filePath) throws Exception {
        return new JsonAnimeListStorage(Paths.get(filePath)).readAnimeList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAnimeList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAnimeList("notJsonFormatAnimeList.json"));
    }

    @Test
    public void readAndSaveAnimeList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAnimeList.json");
        AnimeList original = getTypicalAnimeList();
        JsonAnimeListStorage jsonAnimeListStorage = new JsonAnimeListStorage(filePath);

        // Save in new file and read back
        jsonAnimeListStorage.saveAnimeList(original, filePath);
        ReadOnlyAnimeList readBack = jsonAnimeListStorage.readAnimeList(filePath).get();
        assertEquals(original, new AnimeList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAnime(HOON);
        original.removeAnime(ALICE);
        jsonAnimeListStorage.saveAnimeList(original, filePath);
        readBack = jsonAnimeListStorage.readAnimeList(filePath).get();
        assertEquals(original, new AnimeList(readBack));

        // Save and read without specifying file path
        original.addAnime(IDA);
        jsonAnimeListStorage.saveAnimeList(original); // file path not specified
        readBack = jsonAnimeListStorage.readAnimeList().get(); // file path not specified
        assertEquals(original, new AnimeList(readBack));

    }

    @Test
    public void saveAnimeList_nullAnimeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAnimeList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code animeList} at the specified {@code filePath}.
     */
    private void saveAnimeList(ReadOnlyAnimeList animeList, String filePath) {
        try {
            new JsonAnimeListStorage(Paths.get(filePath))
                    .saveAnimeList(animeList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAnimeList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAnimeList(new AnimeList(), null));
    }
}
