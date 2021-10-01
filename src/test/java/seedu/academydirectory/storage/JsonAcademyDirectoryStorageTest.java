package seedu.academydirectory.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.academydirectory.testutil.Assert.assertThrows;
import static seedu.academydirectory.testutil.TypicalPersons.ALICE;
import static seedu.academydirectory.testutil.TypicalPersons.HOON;
import static seedu.academydirectory.testutil.TypicalPersons.IDA;
import static seedu.academydirectory.testutil.TypicalPersons.getTypicalAcademyDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.academydirectory.commons.exceptions.DataConversionException;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;

public class JsonAcademyDirectoryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAcademyDirectoryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAcademyDirectory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAcademyDirectory(null));
    }

    private java.util.Optional<ReadOnlyAcademyDirectory> readAcademyDirectory(String filePath) throws Exception {
        return new JsonAcademyDirectoryStorage(Paths.get(filePath)).readAcademyDirectory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAcademyDirectory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAcademyDirectory("notJsonFormatAcademyDirectory.json"));
    }

    @Test
    public void readAcademyDirectory_invalidPersonAAcademyDirectory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAcademyDirectory("invalidPersonAcademyDirectory.json"));
    }

    @Test
    public void readAcademyDirectory_invalidAndValidPersonAcademyDirectory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAcademyDirectory("invalidAndValidPersonAcademyDirectory.json"));
    }

    @Test
    public void readAndSaveAcademyDirectory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAcademyDirectory.json");
        AcademyDirectory original = getTypicalAcademyDirectory();
        JsonAcademyDirectoryStorage jsonAcademyDirectoryStorage = new JsonAcademyDirectoryStorage(filePath);

        // Save in new file and read back
        jsonAcademyDirectoryStorage.saveAcademyDirectory(original, filePath);
        ReadOnlyAcademyDirectory readBack = jsonAcademyDirectoryStorage.readAcademyDirectory(filePath).get();
        assertEquals(original, new AcademyDirectory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAcademyDirectoryStorage.saveAcademyDirectory(original, filePath);
        readBack = jsonAcademyDirectoryStorage.readAcademyDirectory(filePath).get();
        assertEquals(original, new AcademyDirectory(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAcademyDirectoryStorage.saveAcademyDirectory(original); // file path not specified
        readBack = jsonAcademyDirectoryStorage.readAcademyDirectory().get(); // file path not specified
        assertEquals(original, new AcademyDirectory(readBack));

    }

    @Test
    public void saveAcademyDirectory_nullAcademyDirectory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAcademyDirectory(null, "SomeFile.json"));
    }

    /**
     * Saves {@code academyDirectory} at the specified {@code filePath}.
     */
    private void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory, String filePath) {
        try {
            new JsonAcademyDirectoryStorage(Paths.get(filePath))
                    .saveAcademyDirectory(academyDirectory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAcademyDirectory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAcademyDirectory(new AcademyDirectory(), null));
    }
}
