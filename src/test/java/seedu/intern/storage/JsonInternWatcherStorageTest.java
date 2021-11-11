package seedu.intern.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.intern.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.intern.commons.exceptions.DataConversionException;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.ReadOnlyInternWatcher;

public class JsonInternWatcherStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInternWatcherStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternWatcher_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternWatcher(null));
    }

    private java.util.Optional<ReadOnlyInternWatcher> readInternWatcher(String filePath) throws Exception {
        return new JsonInternWatcherStorage(Paths.get(filePath))
                .readInternWatcher(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternWatcher("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInternWatcher("notJsonFormatInternWatcher.json"));
    }

    @Test
    public void readInternWatcher_invalidApplicantInternWatcher_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readInternWatcher("invalidApplicantInternWatcher.json"));
    }

    @Test
    public void readInternWatcher_invalidAndValidApplicantInternWatcher_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readInternWatcher("invalidAndValidApplicantInternWatcher.json"));
    }

    @Test
    public void saveInternWatcher_nullInternWatcher_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternWatcher(null, "SomeFile.json"));
    }

    /**
     * Saves {@code onternWatcher} at the specified {@code filePath}.
     * */
    private void saveInternWatcher(ReadOnlyInternWatcher internWatcher, String filePath) {
        try {
            new JsonInternWatcherStorage(Paths.get(filePath))
                    .saveInternWatcher(internWatcher, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternWatcher_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternWatcher(new InternWatcher(), null));
    }
}
