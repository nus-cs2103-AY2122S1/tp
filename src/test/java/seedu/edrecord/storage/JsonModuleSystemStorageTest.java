package seedu.edrecord.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalModules.CS2100;
import static seedu.edrecord.testutil.TypicalModules.CS2102;
import static seedu.edrecord.testutil.TypicalModules.CS2103;
import static seedu.edrecord.testutil.TypicalModules.getTypicalModuleSystem;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.storage.module.JsonModuleSystemStorage;

/**
 * A class to test ModuleSystem reading/saving.
 */
public class JsonModuleSystemStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModuleSystemStorageTest");

    @TempDir
    public Path testFolder;

    /**
     * Reads a {@code ModuleSystem} from the specified {@code filePath}.
     * This is a "wrapper" for the method under test {@code JsonModuleSystemStorage#readModuleSystem()}
     */
    private Optional<ReadOnlyModuleSystem> readModuleSystem(String filePath) throws Exception {
        return new JsonModuleSystemStorage(Paths.get(filePath)).readModuleSystem(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readModuleSystem(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModuleSystem("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readModuleSystem("notJsonFormatModuleSystem.json"));
    }

    // Test for invalid module due to invalid module code
    @Test
    public void read_invalidModule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleSystem("invalidModule.json"));
    }

    @Test
    public void read_invalidAndValidModule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleSystem("invalidAndValidModule.json"));
    }

    // Test for invalid module due to invalid assignment
    @Test
    public void read_invalidAssignment_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleSystem("invalidAssignment.json"));
    }

    @Test
    public void read_invalidAndValidAssignment_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleSystem("invalidAndValidAssignment.json"));
    }

    /**
     * Saves {@code moduleSystem} at the specified {@code filePath}.
     */
    private void saveModuleSystem(ReadOnlyModuleSystem moduleSystem, String filePath) {
        try {
            new JsonModuleSystemStorage(Paths.get(filePath))
                    .saveModuleSystem(moduleSystem, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void save_nullModuleSystem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleSystem(null, "SomeFile.json"));
    }

    @Test
    public void save_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleSystem(new ModuleSystem(), null));
    }

    @Test
    public void readAndSave_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempModuleSystem.json");
        ModuleSystem original = getTypicalModuleSystem();
        JsonModuleSystemStorage jsonModuleSystemStorage = new JsonModuleSystemStorage(filePath);

        // Save in new file and read back
        jsonModuleSystemStorage.saveModuleSystem(original, filePath);
        Module.MODULE_SYSTEM.clear(); // clear the MODULE_SYSTEM static variable to prepare for reading back
        ReadOnlyModuleSystem readBack = jsonModuleSystemStorage.readModuleSystem(filePath).get();
        assertEquals(original, new ModuleSystem(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addModule(CS2100);
        original.removeModule(CS2103);
        jsonModuleSystemStorage.saveModuleSystem(original, filePath);
        Module.MODULE_SYSTEM.clear(); // clear the MODULE_SYSTEM static variable to prepare for reading back
        readBack = jsonModuleSystemStorage.readModuleSystem(filePath).get();
        assertEquals(original, new ModuleSystem(readBack));

        // Save and read without specifying file path
        original.addModule(CS2102);
        jsonModuleSystemStorage.saveModuleSystem(original); // file path not specified
        Module.MODULE_SYSTEM.clear(); // clear the MODULE_SYSTEM static variable to prepare for reading back
        readBack = jsonModuleSystemStorage.readModuleSystem().get(); // file path not specified
        assertEquals(original, new ModuleSystem(readBack));

    }

}
