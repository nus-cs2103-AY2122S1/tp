package socialite.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import socialite.commons.exceptions.DataConversionException;
import socialite.model.CommandHistory;
import socialite.model.ReadOnlyCommandHistory;
import socialite.testutil.Assert;
import socialite.testutil.TypicalCommandHistory;

public class JsonCommandHistoryStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonCommandHistoryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCommandHistory_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readCommandHistory(null));
    }

    private java.util.Optional<ReadOnlyCommandHistory> readCommandHistory(String filePath) throws Exception {
        return new JsonCommandHistoryStorage(Paths.get(filePath))
                .readCommandHistory(addToTestDataPathIfNotNull(filePath));
    }


    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCommandHistory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, ()
            -> readCommandHistory("notJsonFormatCommandHistory.json"));
    }

    @Test
    public void readCommandHistory_invalidCommandHistory_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readCommandHistory("invalidCommandHistory.json"));
    }

    @Test
    public void readCommandHistory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCommandHistory.json");
        CommandHistory original = TypicalCommandHistory.getTypicalCommandHistory();
        JsonCommandHistoryStorage jsonCommandHistoryStorage = new JsonCommandHistoryStorage(filePath);

        // Save in new file and read back
        jsonCommandHistoryStorage.saveCommandHistory(original, filePath);
        ReadOnlyCommandHistory readBack = jsonCommandHistoryStorage.readCommandHistory(filePath).get();
        assertEquals(original, new CommandHistory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCommand(TypicalCommandHistory.EDIT);
        original.addCommand(TypicalCommandHistory.ADD);
        jsonCommandHistoryStorage.saveCommandHistory(original, filePath);
        readBack = jsonCommandHistoryStorage.readCommandHistory(filePath).get();
        assertEquals(original, new CommandHistory(readBack));

        // Save and read without specifying file path
        original.addCommand(TypicalCommandHistory.FIND);
        jsonCommandHistoryStorage.saveCommandHistory(original); // file path not specified
        readBack = jsonCommandHistoryStorage.readCommandHistory().get(); // file path not specified
        assertEquals(original, new CommandHistory(readBack));
    }


    @Test
    public void saveCommandHistory_nullCommandHistory_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveCommandHistory(null, "SomeFile.json"));
    }


    private void saveCommandHistory(ReadOnlyCommandHistory commandHistory, String filePath) {
        try {
            new JsonCommandHistoryStorage(Paths.get(filePath))
                    .saveCommandHistory(commandHistory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCommandHistory_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveCommandHistory(new CommandHistory(), null));
    }
}
