package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Internship;
import seedu.address.model.ReadOnlyInternship;

public class JsonInternshipStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInternshipStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternship_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternship(null));
    }

    private java.util.Optional<ReadOnlyInternship> readInternship(String filePath) throws Exception {
        return new JsonInternshipStorage(Paths.get(filePath)).readInternship(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternship("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInternship("notJsonFormatInternship.json"));
    }

    @Test
    public void readInternship_invalidApplicationInternship_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternship("invalidApplicationInternship.json"));
    }

    @Test
    public void reaInternship_invalidAndValidApplicationInternship_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternship("invalidAndValidApplicationInternship.json"));
    }

    @Test
    public void readAndSaveInternship_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternship.json");
        Internship original = getTypicalInternship();
        JsonInternshipStorage jsonInternshipStorage = new JsonInternshipStorage(filePath);

        // Save in new file and read back
        jsonInternshipStorage.saveInternship(original, filePath);
        ReadOnlyInternship readBack = jsonInternshipStorage.readInternship(filePath).get();
        assertEquals(original, new Internship(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeApplication(GRAB);
        jsonInternshipStorage.saveInternship(original, filePath);
        readBack = jsonInternshipStorage.readInternship(filePath).get();
        assertEquals(original, new Internship(readBack));

        // Save and read without specifying file path
        original.addApplication(GRAB);
        jsonInternshipStorage.saveInternship(original); // file path not specified
        readBack = jsonInternshipStorage.readInternship().get(); // file path not specified
        assertEquals(original, new Internship(readBack));

    }

    @Test
    public void saveInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternship(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Internship} at the specified {@code filePath}.
     */
    private void saveInternship(ReadOnlyInternship internship, String filePath) {
        try {
            new JsonInternshipStorage(Paths.get(filePath))
                    .saveInternship(internship, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternship_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternship(new Internship(), null));
    }
}
