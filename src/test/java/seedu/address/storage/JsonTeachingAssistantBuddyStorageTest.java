package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.testutil.TypicalModules;

public class JsonTeachingAssistantBuddyStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonTeachingAssistantBuddyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTeachingAssistantBuddy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTeachingAssistantBuddy(null));
    }

    private java.util.Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy(String filePath)
            throws Exception {
        return new JsonTeachingAssistantBuddyStorage(Paths.get(filePath)).readTeachingAssistantBuddy(
        addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTeachingAssistantBuddy("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readTeachingAssistantBuddy("notJsonFormatAssistantBuddy.json"));
    }

    @Test
    public void readAddressBook_invalidAssistantBuddy_throwDataConversionException() { //need to change to module
        assertThrows(DataConversionException.class, () ->
                readTeachingAssistantBuddy("invalidAssistantBuddy.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidModulesAssistantBuddy_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTeachingAssistantBuddy("invalidAndValidModulesAssistantBuddy.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAsisstantBuddy.json");
        TeachingAssistantBuddy original = TypicalModules.getTypicalBuddy();
        JsonTeachingAssistantBuddyStorage jsonTeachingAssistantBuddyStorage =
                new JsonTeachingAssistantBuddyStorage(filePath);

        // Save in new file and read back
        jsonTeachingAssistantBuddyStorage.saveTeachingAssistantBuddy(original, filePath);
        ReadOnlyTeachingAssistantBuddy readBack =
                jsonTeachingAssistantBuddyStorage.readTeachingAssistantBuddy(filePath).get();
        assertEquals(original, new TeachingAssistantBuddy(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeModule(MODULE_1);
        jsonTeachingAssistantBuddyStorage.saveTeachingAssistantBuddy(original, filePath);
        readBack = jsonTeachingAssistantBuddyStorage.readTeachingAssistantBuddy(filePath).get();
        assertEquals(original, new TeachingAssistantBuddy(readBack));

        // Save and read without specifying file path
        original.addModule(MODULE_1);
        jsonTeachingAssistantBuddyStorage.saveTeachingAssistantBuddy(original); // file path not specified
        readBack = jsonTeachingAssistantBuddyStorage.readTeachingAssistantBuddy().get(); // file path not specified
        assertEquals(original, new TeachingAssistantBuddy(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAssistantBuddy(null, "SomeFile.json"));
    }

    /**
     * Saves {@code assistantBuddy} at the specified {@code filePath}.
     */
    private void saveAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy, String filePath) {
        try {
            new JsonTeachingAssistantBuddyStorage(Paths.get(filePath))
                    .saveTeachingAssistantBuddy(assistantBuddy, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveAssistantBuddy(new TeachingAssistantBuddy(), null));
    }
}
