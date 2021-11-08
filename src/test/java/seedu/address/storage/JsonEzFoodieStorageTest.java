package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.HOON;
import static seedu.address.testutil.TypicalMembers.IDA;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.EzFoodie;
import seedu.address.model.ReadOnlyEzFoodie;

/**
 * Tests the functionalities of
 * {@code JsonEzFoodieStorage}.
 */
public class JsonEzFoodieStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEzFoodieStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEzFoodie_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEzFoodie(null));
    }

    private java.util.Optional<ReadOnlyEzFoodie> readEzFoodie(String filePath) throws Exception {
        return new JsonEzFoodieStorage(Paths.get(filePath)).readEzFoodie(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEzFoodie("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEzFoodie("notJsonFormatEzFoodie.json"));
    }

    @Test
    public void readEzFoodie_invalidMemberEzFoodie_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEzFoodie("invalidMemberEzFoodie.json"));
    }

    @Test
    public void readEzFoodie_invalidAndValidMemberEzFoodie_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEzFoodie("invalidAndValidMemberEzFoodie.json"));
    }

    @Test
    public void readAndSaveEzFoodie_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEzFoodie.json");
        EzFoodie original = getTypicalEzFoodie();
        JsonEzFoodieStorage jsonEzFoodieStorage = new JsonEzFoodieStorage(filePath);

        // Save in new file and read back
        jsonEzFoodieStorage.saveEzFoodie(original, filePath);
        ReadOnlyEzFoodie readBack = jsonEzFoodieStorage.readEzFoodie(filePath).get();
        assertEquals(original, new EzFoodie(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addMember(HOON);
        original.removeMember(ALICE);
        jsonEzFoodieStorage.saveEzFoodie(original, filePath);
        readBack = jsonEzFoodieStorage.readEzFoodie(filePath).get();
        assertEquals(original, new EzFoodie(readBack));

        // Save and read without specifying file path
        original.addMember(IDA);
        jsonEzFoodieStorage.saveEzFoodie(original); // file path not specified
        readBack = jsonEzFoodieStorage.readEzFoodie().get(); // file path not specified
        assertEquals(original, new EzFoodie(readBack));

    }

    @Test
    public void saveEzFoodie_nullEzFoodie_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEzFoodie(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ezFoodie} at the specified {@code filePath}.
     */
    private void saveEzFoodie(ReadOnlyEzFoodie ezFoodie, String filePath) {
        try {
            new JsonEzFoodieStorage(Paths.get(filePath))
                    .saveEzFoodie(ezFoodie, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEzFoodie_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEzFoodie(new EzFoodie(), null));
    }
}
