package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.testutil.InterviewBuilder;

public class JsonHrManagerStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonHrManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHrManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHrManager(null,
                null, null));
    }

    private java.util.Optional<ReadOnlyHrManager> readHrManager(String candidatesFilePath,
                                                                String positionsFilePath,
                                                                String interviewsFilePath) throws Exception {
        return new JsonHrManagerStorage(Paths.get(candidatesFilePath), Paths.get(positionsFilePath),
                Paths.get(interviewsFilePath))
                .readHrManager(addToTestDataPathIfNotNull(candidatesFilePath),
                        addToTestDataPathIfNotNull(positionsFilePath),
                        addToTestDataPathIfNotNull(interviewsFilePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHrManager("NonExistentCandidateFile.json",
                "NonExistentPositionFile.json",
                "NonExistentInterviewFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "notJsonFormatHrManagerCandidates.json",
                "notJsonFormatHrManagerPositions.json",
                "notJsonFormatHrManagerInterviews.json"));
    }

    @Test
    public void readHrManager_invalidAllHrManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager("invalidPersonHrManager.json",
                "invalidPositionHrManager.json", "invalidInterviewHrManager.json"));
    }

    @Test
    public void readHrManager_invalidAndValidAllHrManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "invalidAndValidPersonHrManager.json",
                "invalidAndValidPositionHrManager.json",
                "invalidAndValidInterviewHrManager.json"));
    }

    @Test
    public void readHrManager_invalidPersonHrManagerEntry_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "invalidAndValidPersonHrManager.json",
                "validPositionHrManager.json",
                "validInterviewHrManager.json"));
    }

    @Test
    public void readHrManager_invalidPositionHrManagerEntry_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "validPersonHrManager.json",
                "invalidAndValidPositionHrManager.json",
                "validInterviewHrManager.json"));
    }

    @Test
    public void readHrManager_invalidInterviewHrManagerEntry_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "validPersonHrManager.json",
                "validAndValidPositionHrManager.json",
                "invalidAndValidInterviewHrManager.json"));
    }


    @Test
    public void readAndSaveHrManager_allInOrder_success() throws Exception {
        Path candidatesFilePath = testFolder.resolve("HrManagerCandidates.json");
        Path positionsFilePath = testFolder.resolve("HrManagerPositions.json");
        Path interviewsFilePath = testFolder.resolve("HrManagerInterviews.json");
        HrManager original = getTypicalHrManager();
        JsonHrManagerStorage jsonHrManagerStorage = new JsonHrManagerStorage(candidatesFilePath, positionsFilePath,
                interviewsFilePath);

        // Save in new file and read back
        jsonHrManagerStorage.saveHrManager(original, candidatesFilePath, positionsFilePath, interviewsFilePath);
        ReadOnlyHrManager readBack = jsonHrManagerStorage.readHrManager(candidatesFilePath, positionsFilePath,
                interviewsFilePath).get();

        assertEquals(original, new HrManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonHrManagerStorage.saveHrManager(original, candidatesFilePath, positionsFilePath, interviewsFilePath);
        readBack = jsonHrManagerStorage.readHrManager(candidatesFilePath, positionsFilePath,
                interviewsFilePath).get();

        assertEquals(original, new HrManager(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonHrManagerStorage.saveHrManager(original); // file path not specified
        readBack = jsonHrManagerStorage.readHrManager().get(); // file path not specified
        assertEquals(original, new HrManager(readBack));

        //add interview save and read back
        original.addInterview(new InterviewBuilder().build());
        jsonHrManagerStorage.saveHrManager(original); // file path not specified
        readBack = jsonHrManagerStorage.readHrManager().get(); // file path not specified
        assertEquals(original, new HrManager(readBack));

    }

    @Test
    public void saveHrManager_nullHrManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHrManager(null,
                "SomeCandidatesFile.json", "SomePositionsFile.json",
                "SomeInterviewsFile.json"));
    }

    /**
     * Saves {@code hrManager} at the specified {@code filePath}.
     */
    private void saveHrManager(ReadOnlyHrManager hrManager, String candidatesFilePath, String positionsFilePath,
                               String interviewsFilePath) {
        try {
            new JsonHrManagerStorage(Paths.get(candidatesFilePath), Paths.get(positionsFilePath),
                    Paths.get(interviewsFilePath)).saveHrManager(hrManager,
                    addToTestDataPathIfNotNull(candidatesFilePath), addToTestDataPathIfNotNull(positionsFilePath),
                    addToTestDataPathIfNotNull(interviewsFilePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHrManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHrManager(new HrManager(),
                null, null, null));
    }
}
