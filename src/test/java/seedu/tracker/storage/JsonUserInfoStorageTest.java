package seedu.tracker.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.module.Mc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalUserInfo.getTypicalUserInfo;

class JsonUserInfoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserInfoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUserInfo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserInfo(null));
    }

    private java.util.Optional<ReadOnlyUserInfo> readUserInfo(String filePath) throws Exception {
        return new JsonUserInfoStorage(Paths.get(filePath)).readUserInfo(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUserInfo("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserInfo("notJsonFormatUserInfo.json"));
    }

    @Test
    public void readUserInfo_invalidMcUserInfo_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUserInfo("invalidMcUserInfo.json"));
    }

    @Test
    public void readUserInfo_invalidYearUserInfo_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUserInfo("invalidYearUserInfo.json"));
    }

    @Test
    public void readUserInfo_invalidSemesterUserInfo_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUserInfo("invalidSemesterUserInfo.json"));
    }


    @Test
    public void readAndSaveUserInfo_allInOrder_success() throws Exception {
        Path filePath = TEST_DATA_FOLDER.resolve("TempUserInfo.json");
        UserInfo original = getTypicalUserInfo();
        JsonUserInfoStorage jsonUserInfoStorage = new JsonUserInfoStorage(filePath);

        // Save in new file and read back
        jsonUserInfoStorage.saveUserInfo(original, filePath);
        ReadOnlyUserInfo readBack = jsonUserInfoStorage.readUserInfo(filePath).get();
        assertEquals(original, new UserInfo(readBack));

        // Save and read without specifying file path
        jsonUserInfoStorage.saveUserInfo(original); // file path not specified
        readBack = jsonUserInfoStorage.readUserInfo().get(); // file path not specified
        assertEquals(original, new UserInfo(readBack));

        // Modify data, overwrite exiting file, and read back
        original.setMcGoal(new Mc(160));
        jsonUserInfoStorage.saveUserInfo(original, filePath);
        readBack = jsonUserInfoStorage.readUserInfo(filePath).get();
        assertFalse(original.equals(new UserInfo(readBack)));
    }

    @Test
    public void saveUserInfo_nullUserInfo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserInfo(null, "SomeFile.json"));
    }

    /**
     * Saves {@code userInfo} at the specified {@code filePath}.
     */
    private void saveUserInfo(ReadOnlyUserInfo userInfo, String filePath) {
        try {
            new JsonUserInfoStorage(Paths.get(filePath))
                    .saveUserInfo(userInfo, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveUserInfo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserInfo(new UserInfo(), null));
    }
}