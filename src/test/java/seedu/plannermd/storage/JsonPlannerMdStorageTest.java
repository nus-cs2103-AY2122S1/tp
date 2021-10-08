package seedu.plannermd.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.HOON;
import static seedu.plannermd.testutil.patient.TypicalPatients.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.plannermd.commons.exceptions.DataConversionException;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;

public class JsonPlannerMdStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPlannerMdStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPlannerMd_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPlannerMd(null));
    }

    private java.util.Optional<ReadOnlyPlannerMd> readPlannerMd(String filePath) throws Exception {
        return new JsonPlannerMdStorage(Paths.get(filePath)).readPlannerMd(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPlannerMd("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPlannerMd("notJsonFormatPlannerMd.json"));
    }

    @Test
    public void readPlannerMd_invalidPatientPlannerMd_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPlannerMd("invalidPatientPlannerMd.json"));
    }

    @Test
    public void readPlannerMd_invalidAndValidPatientPlannerMd_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPlannerMd("invalidAndValidPatientPlannerMd.json"));
    }

    @Test
    public void readAndSavePlannerMd_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPlannerMd.json");
        PlannerMd original = getTypicalPlannerMd();
        JsonPlannerMdStorage jsonPlannerMdStorage = new JsonPlannerMdStorage(filePath);

        // Save in new file and read back
        jsonPlannerMdStorage.savePlannerMd(original, filePath);
        ReadOnlyPlannerMd readBack = jsonPlannerMdStorage.readPlannerMd(filePath).get();
        System.out.println(original);
        System.out.println(readBack);
        assertEquals(original, new PlannerMd(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonPlannerMdStorage.savePlannerMd(original, filePath);
        readBack = jsonPlannerMdStorage.readPlannerMd(filePath).get();
        assertEquals(original, new PlannerMd(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonPlannerMdStorage.savePlannerMd(original); // file path not specified
        readBack = jsonPlannerMdStorage.readPlannerMd().get(); // file path not specified
        assertEquals(original, new PlannerMd(readBack));

    }

    @Test
    public void savePlannerMd_nullPlannerMd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePlannerMd(null, "SomeFile.json"));
    }

    /**
     * Saves {@code plannerMd} at the specified {@code filePath}.
     */
    private void savePlannerMd(ReadOnlyPlannerMd plannerMd, String filePath) {
        try {
            new JsonPlannerMdStorage(Paths.get(filePath))
                    .savePlannerMd(plannerMd, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePlannerMd_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePlannerMd(new PlannerMd(), null));
    }
}
