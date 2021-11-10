package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.VALID_KEY;
import static seedu.address.testutil.TypicalPersons.getTypicalShortcut;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.Shortcut;



public class JsonShortcutStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonShortcutStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readShortcut_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readShortcut(null));
    }

    private java.util.Optional<ReadOnlyShortcut> readShortcut(String filePath) throws Exception {
        return new JsonShortcutStorage(Paths.get(filePath)).readShortcut(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readShortcut("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readShortcut("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAndSaveShortcut_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempShortcut.json");
        Shortcut original = getTypicalShortcut();
        JsonShortcutStorage jsonShortcutStorage = new JsonShortcutStorage(filePath);

        // Save in new file and read back
        jsonShortcutStorage.saveShortcut(original, filePath);
        ReadOnlyShortcut readBack = jsonShortcutStorage.readShortcut(filePath).get();
        assertEquals(original, new Shortcut(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addShortcut("a", "list");
        original.removeShortcut(VALID_KEY);
        jsonShortcutStorage.saveShortcut(original, filePath);
        readBack = jsonShortcutStorage.readShortcut(filePath).get();
        assertEquals(original, new Shortcut(readBack));

        // Save and read without specifying file path
        original.addShortcut("b", "find b");
        jsonShortcutStorage.saveShortcut(original); // file path not specified
        readBack = jsonShortcutStorage.readShortcut().get(); // file path not specified
        assertEquals(original, new Shortcut(readBack));
    }

    @Test
    public void saveShortcut_nullShortcut_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveShortcut(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveShortcut(ReadOnlyShortcut shortcut, String filePath) {
        try {
            new JsonShortcutStorage(Paths.get(filePath))
                    .saveShortcut(shortcut, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveShortcut_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveShortcut(new Shortcut(), null));
    }
}
