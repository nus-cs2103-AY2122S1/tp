package seedu.tracker.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.UserPrefs;
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

    private java.util.Optional<UserInfo> readUserInfo(String userInfoFileInTestDataFolder) throws Exception {
        Path infoFilePath = addToTestDataPathIfNotNull(userInfoFileInTestDataFolder);
        return new JsonUserInfoStorage(infoFilePath).readUserInfo(infoFilePath);
    }

    @Test
    public void readUserInfo_missingFile_emptyResult() throws Exception {
        assertFalse(readUserInfo("NonExistentFile.json").isPresent());
    }

    @Test
    public void readUserInfo_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserInfo("NotJsonFormatUserInfo.json"));
    }

    private Path addToTestDataPathIfNotNull(String userInfoFileInTestDataFolder) {
        return userInfoFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userInfoFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUserInfo_fileInOrder_successfullyRead() throws Exception {
        UserInfo expected = getTypicalUserInfo();
        UserInfo actual = readUserInfo("TypicalUserInfo.json").get();
        assertEquals(expected, actual);
    }
    
}