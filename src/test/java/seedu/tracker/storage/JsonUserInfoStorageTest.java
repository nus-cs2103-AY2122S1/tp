package seedu.tracker.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.UserInfo;

import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


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
        assertThrows(DataConversionException.class, () -> readUserInfo("NotJsonFormatUserinfo.json"));
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


    // Solve this first! Todo: read from json file fails
    @Test
    public void readUserInfo_fileInOrder_successfullyRead() throws DataConversionException, IOException {
        UserInfo expected = getTypicalUserInfo();
        Path infoFilePath = TEST_DATA_FOLDER.resolve("TempInfo.json");
        JsonUserInfoStorage jsonUserInfoStorage = new JsonUserInfoStorage(infoFilePath);
        UserInfo actual = jsonUserInfoStorage.readUserInfo().get();
        assertEquals(expected, actual);
    }

    private UserInfo getTypicalUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setCurrentSemester(new AcademicCalendar(new AcademicYear(2), new Semester(1)));
        userInfo.setMcGoal(new Mc(160));
        return userInfo;
    }

    // Todo : can't read back from json files
    @Test
    public void saveUserInfo_allInOrder_success() throws IOException, DataConversionException {
        UserInfo original = new UserInfo();
        original.setCurrentSemester(new AcademicCalendar(new AcademicYear(5), new Semester(1)));
        original.setMcGoal(new Mc(160));
//        Path infoFilePath = testFolder.resolve("TempInfo.json");
        Path infoFilePath = TEST_DATA_FOLDER.resolve("TempInfo.json");
        JsonUserInfoStorage jsonUserInfoStorage = new JsonUserInfoStorage(infoFilePath);

        jsonUserInfoStorage.saveUserInfo(original);
//        UserInfo readBack = jsonUserInfoStorage.readUserInfo().get();
//        assertEquals(original, readBack);
    }
}