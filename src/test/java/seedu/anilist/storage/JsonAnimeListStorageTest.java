package seedu.anilist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalPersons.ALICE;
import static seedu.anilist.testutil.TypicalPersons.HOON;
import static seedu.anilist.testutil.TypicalPersons.IDA;
import static seedu.anilist.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.anilist.commons.exceptions.DataConversionException;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;

public class JsonAnimeListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAnimeList> readAddressBook(String filePath) throws Exception {
        return new JsonAniListStorage(Paths.get(filePath)).readAniList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AnimeList original = getTypicalAddressBook();
        JsonAniListStorage jsonAddressBookStorage = new JsonAniListStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAniList(original, filePath);
        ReadOnlyAnimeList readBack = jsonAddressBookStorage.readAniList(filePath).get();
        assertEquals(original, new AnimeList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAnime(HOON);
        original.removeAnime(ALICE);
        jsonAddressBookStorage.saveAniList(original, filePath);
        readBack = jsonAddressBookStorage.readAniList(filePath).get();
        assertEquals(original, new AnimeList(readBack));

        // Save and read without specifying file path
        original.addAnime(IDA);
        jsonAddressBookStorage.saveAniList(original); // file path not specified
        readBack = jsonAddressBookStorage.readAniList().get(); // file path not specified
        assertEquals(original, new AnimeList(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAnimeList addressBook, String filePath) {
        try {
            new JsonAniListStorage(Paths.get(filePath))
                    .saveAniList(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AnimeList(), null));
    }
}
