package seedu.tracker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.tracker.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;


class JsonUserInfoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserInfoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUserInfo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserInfo(null));
    }


    @Test
    public void readUserInfo_missingFile_emptyResult() throws DataConversionException {
        assertFalse(readUserInfo("NonExistentFile.json").isPresent());
    }

    @Test
    public void readUserInfo_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserInfo("notJsonFormatUserInfo.json"));
    }

    private Optional<UserInfo> readUserInfo(String userInfoFileInTestDataFolder) throws DataConversionException {
        Path infoFilePath = addToTestDataPathIfNotNull(userInfoFileInTestDataFolder);
        return new JsonUserInfoStorage(infoFilePath).readUserInfo(infoFilePath);
    }

    private Path addToTestDataPathIfNotNull(String userInfoFileInTestDataFolder) {
        return userInfoFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userInfoFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUserInfo_fileInOrder_successfullyRead() throws DataConversionException, IOException {
        UserInfo expected = getTypicalUserInfo();
        Path infoFilePath = TEST_DATA_FOLDER.resolve("TempInfo.json");
        JsonUserInfoStorage jsonUserInfoStorage = new JsonUserInfoStorage(infoFilePath);
        UserInfo actual = jsonUserInfoStorage.readUserInfo().get();
        assertEquals(expected, actual);
    }

    @Test
    public void readUserInfo_valuesMissingFromFile_defaultValueUsed() throws DataConversionException {
        UserInfo actual = readUserInfo("EmptyUserInfo.json").get();
        assertEquals(new UserInfo(), actual);
    }

    @Test
    public void readUserInfo_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        UserInfo expected = getTypicalUserInfo();
        UserInfo actual = readUserInfo("ExtraValuesUserInfo.json").get();
        assertEquals(expected, actual);
    }

    private UserInfo getTypicalUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setCurrentSemester(new AcademicCalendar(new AcademicYear(4), new Semester(1)));
        userInfo.setMcGoal(new Mc(160));
        return userInfo;
    }

    @Test
    public void saveInfo_nullInfo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserInfo(null, "SomeFile.json"));
    }

    @Test
    public void saveInfo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserInfo(new UserInfo(), null));
    }

    /**
     * Saves {@code userInfo} at the specified {@code infoFileInTestDataFolder} filepath.
     */
    private void saveUserInfo(UserInfo userInfo, String infoFileInTestDataFolder) {
        try {
            new JsonUserInfoStorage(addToTestDataPathIfNotNull(infoFileInTestDataFolder))
                    .saveUserInfo(userInfo);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }
    @Test
    public void saveUserInfo_allInOrder_success() throws IOException, DataConversionException {
        UserInfo original = new UserInfo();
        original.setCurrentSemester(new AcademicCalendar(new AcademicYear(4), new Semester(1)));
        original.setMcGoal(new Mc(160));
        Path infoFilePath = testFolder.resolve("TempInfo.json");
        JsonUserInfoStorage jsonUserInfoStorage = new JsonUserInfoStorage(infoFilePath);

        jsonUserInfoStorage.saveUserInfo(original);
        UserInfo readBack = jsonUserInfoStorage.readUserInfo().get();
        assertEquals(original, readBack);

        original.setMcGoal(new Mc(320));
        jsonUserInfoStorage.saveUserInfo(original);
        readBack = jsonUserInfoStorage.readUserInfo().get();
        assertEquals(original, readBack);
    }
}
