package seedu.tracker.storage;

import org.junit.jupiter.api.Test;
import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.commons.util.JsonUtil;
import seedu.tracker.model.UserInfo;
import seedu.tracker.testutil.TypicalUserInfo;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.tracker.testutil.Assert.assertThrows;

class JsonSerializableUserInfoTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableUserInfoTest");
    private static final Path TYPICAL_USERINFO_FILE = TEST_DATA_FOLDER.resolve("typicalUserInfo.json");
    private static final Path INVALID_MC_FILE = TEST_DATA_FOLDER.resolve("invalidMcInfo.json");
    private static final Path INVALID_SEMESTER_FILE = TEST_DATA_FOLDER.resolve("invalidSemesterInfo.json");
    private static final Path INVALID_YEAR_FILE = TEST_DATA_FOLDER.resolve("invalidYearInfo.json");

    @Test
    public void toModelType_typicalUserInfoFile_success() throws Exception {
        JsonSerializableUserInfo dataFromFile = JsonUtil.readJsonFile(TYPICAL_USERINFO_FILE,
                JsonSerializableUserInfo.class).get();
        UserInfo userInfoFromFile = dataFromFile.toModelType();
        UserInfo typicalUserInfo = TypicalUserInfo.getTypicalUserInfo();
        assertEquals(userInfoFromFile, typicalUserInfo);
    }

    @Test
    public void toModelType_invalidMcFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUserInfo dataFromFile = JsonUtil.readJsonFile(INVALID_MC_FILE,
                JsonSerializableUserInfo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidSemesterFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUserInfo dataFromFile = JsonUtil.readJsonFile(INVALID_SEMESTER_FILE,
                JsonSerializableUserInfo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidYearFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUserInfo dataFromFile = JsonUtil.readJsonFile(INVALID_YEAR_FILE,
                JsonSerializableUserInfo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}