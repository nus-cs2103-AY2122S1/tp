package seedu.notor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.notor.testutil.Assert.assertThrows;
import static seedu.notor.testutil.TypicalPersons.ALICE;
import static seedu.notor.testutil.TypicalPersons.HOON;
import static seedu.notor.testutil.TypicalPersons.IDA;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.notor.commons.exceptions.DataConversionException;
import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;

public class JsonNotorStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNotorStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNotor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNotor(null));
    }

    private java.util.Optional<ReadOnlyNotor> readNotor(String filePath) throws Exception {
        return new JsonNotorStorage(Paths.get(filePath)).readNotor(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNotor("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readNotor("notJsonFormatNotor.json"));
    }

    @Test
    public void readNotor_invalidPersonNotor_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNotor("invalidPersonNotor.json"));
    }

    @Test
    public void readNotor_invalidAndValidPersonNotor_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNotor("invalidAndValidPersonNotor.json"));
    }

    @Test
    public void readAndSaveNotor_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNotor.json");
        Notor original = getTypicalNotor();
        JsonNotorStorage jsonNotorStorage = new JsonNotorStorage(filePath);

        // Save in new file and read back
        jsonNotorStorage.saveNotor(original, filePath);
        ReadOnlyNotor readBack = jsonNotorStorage.readNotor(filePath).get();
        assertEquals(original, new Notor(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonNotorStorage.saveNotor(original, filePath);
        readBack = jsonNotorStorage.readNotor(filePath).get();
        assertEquals(original, new Notor(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonNotorStorage.saveNotor(original); // file path not specified
        readBack = jsonNotorStorage.readNotor().get(); // file path not specified
        assertEquals(original, new Notor(readBack));

    }

    @Test
    public void saveNotor_nullNotor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNotor(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Notor} at the specified {@code filePath}.
     */
    private void saveNotor(ReadOnlyNotor notor, String filePath) {
        try {
            new JsonNotorStorage(Paths.get(filePath))
                    .saveNotor(notor, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNotor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNotor(new Notor(), null));
    }
}
