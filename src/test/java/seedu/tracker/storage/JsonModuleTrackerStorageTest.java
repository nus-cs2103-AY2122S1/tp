package seedu.tracker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS2101;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.ReadOnlyModuleTracker;

public class JsonModuleTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModuleTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyModuleTracker> readAddressBook(String filePath) throws Exception {
        return new JsonModuleTrackerStorage(Paths.get(filePath)).readModuleTracker(
                addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatModuleTracker.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidModuleModuleTracker.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidModuleModuleTracker.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempModuleTracker.json");
        ModuleTracker original = getTypicalModuleTracker();
        JsonModuleTrackerStorage jsonModuleTrackerStorage = new JsonModuleTrackerStorage(filePath);

        // Save in new file and read back
        jsonModuleTrackerStorage.saveModuleTracker(original, filePath);
        ReadOnlyModuleTracker readBack = jsonModuleTrackerStorage.readModuleTracker(filePath).get();
        assertEquals(original, new ModuleTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeModule(CS2101);
        jsonModuleTrackerStorage.saveModuleTracker(original, filePath);
        readBack = jsonModuleTrackerStorage.readModuleTracker(filePath).get();
        assertEquals(original, new ModuleTracker(readBack));

        // Save and read without specifying file path
        jsonModuleTrackerStorage.saveModuleTracker(original); // file path not specified
        readBack = jsonModuleTrackerStorage.readModuleTracker().get(); // file path not specified
        assertEquals(original, new ModuleTracker(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyModuleTracker addressBook, String filePath) {
        try {
            new JsonModuleTrackerStorage(Paths.get(filePath))
                .saveModuleTracker(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new ModuleTracker(), null));
    }
}
