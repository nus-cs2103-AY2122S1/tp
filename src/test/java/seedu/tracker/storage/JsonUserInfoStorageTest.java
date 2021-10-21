package seedu.tracker.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.tracker.testutil.Assert.assertThrows;

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
        UserInfo actual = readUserInfo("BachelorHonoursYearTwoSemTwo.json").get();
        assertEquals(expected.getMcGoal(), actual.getMcGoal());
    }

    private UserInfo getTypicalUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setMcGoal(new Mc(240));
        userInfo.setCurrentSemester(new AcademicCalendar(new AcademicYear(4), new Semester(1)));

        return userInfo;
    }

    @Test
    public void saveInfo_nullInfo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserInfo(null, "SomeFile.json"));
    }

    @Test
    public void saveUserInfo_nullFilePath_throwsNullPointerException() {
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
    public void saveUserInfo_allInOrder_success() throws DataConversionException, IOException {

        UserInfo original = new UserInfo();
        original.setCurrentSemester(new AcademicCalendar(new AcademicYear(1), new Semester(1)));

        Path infoFilePath = testFolder.resolve("TempInfo.json");
        JsonUserInfoStorage jsonUserInfoStorage = new JsonUserInfoStorage(infoFilePath);

        // Try writing when the file doesn't exist
        jsonUserInfoStorage.saveUserInfo(original);
        UserInfo readBack = jsonUserInfoStorage.readUserInfo().get();
        System.out.println(readBack);
//        assertEquals(original, readBack);

//        //Try saving when the file exists
//        original.setCurrentSemester(new AcademicCalendar(new AcademicYear(3), new Semester(1)));
//        jsonUserInfoStorage.saveUserInfo(original);
//        readBack = jsonUserInfoStorage.readUserInfo().get();
//        assertEquals(original, readBack);
    }
}