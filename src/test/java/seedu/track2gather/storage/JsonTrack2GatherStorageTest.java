package seedu.track2gather.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.track2gather.testutil.Assert.assertThrows;
import static seedu.track2gather.testutil.TypicalPersons.ALICE;
import static seedu.track2gather.testutil.TypicalPersons.HOON;
import static seedu.track2gather.testutil.TypicalPersons.IDA;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.track2gather.commons.exceptions.DataConversionException;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.Track2Gather;

public class JsonTrack2GatherStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrack2GatherStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTrack2Gather_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTrack2Gather(null));
    }

    private java.util.Optional<ReadOnlyTrack2Gather> readTrack2Gather(String filePath) throws Exception {
        return new JsonTrack2GatherStorage(Paths.get(filePath)).readTrack2Gather(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTrack2Gather("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTrack2Gather("notJsonFormatTrack2Gather.json"));
    }

    @Test
    public void readTrack2Gather_invalidPersonTrack2Gather_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTrack2Gather("invalidPersonTrack2Gather.json"));
    }

    @Test
    public void readTrack2Gather_invalidAndValidPersonTrack2Gather_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTrack2Gather("invalidAndValidPersonTrack2Gather.json"));
    }

    @Test
    public void readAndSaveTrack2Gather_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrack2Gather.json");
        Track2Gather original = getTypicalTrack2Gather();
        JsonTrack2GatherStorage jsonTrack2GatherStorage = new JsonTrack2GatherStorage(filePath);

        // Save in new file and read back
        jsonTrack2GatherStorage.saveTrack2Gather(original, filePath);
        ReadOnlyTrack2Gather readBack = jsonTrack2GatherStorage.readTrack2Gather(filePath).get();
        assertEquals(original, new Track2Gather(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonTrack2GatherStorage.saveTrack2Gather(original, filePath);
        readBack = jsonTrack2GatherStorage.readTrack2Gather(filePath).get();
        assertEquals(original, new Track2Gather(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonTrack2GatherStorage.saveTrack2Gather(original); // file path not specified
        readBack = jsonTrack2GatherStorage.readTrack2Gather().get(); // file path not specified
        assertEquals(original, new Track2Gather(readBack));

    }

    @Test
    public void saveTrack2Gather_nullTrack2Gather_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrack2Gather(null, "SomeFile.json"));
    }

    /**
     * Saves {@code track2Gather} at the specified {@code filePath}.
     */
    private void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather, String filePath) {
        try {
            new JsonTrack2GatherStorage(Paths.get(filePath))
                    .saveTrack2Gather(track2Gather, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrack2Gather_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrack2Gather(new Track2Gather(), null));
    }
}
