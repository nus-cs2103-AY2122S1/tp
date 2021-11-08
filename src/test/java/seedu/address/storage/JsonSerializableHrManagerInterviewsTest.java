package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HrManager;
import seedu.address.testutil.TypicalPersons;

class JsonSerializableHrManagerInterviewsTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableHrManagerInterviewsTest");
    private static final Path TYPICAL_INTERVIEWS_FILE = TEST_DATA_FOLDER.resolve("typicalInterviewHrManager.json");
    private static final Path INVALID_INTERVIEWS_FILE = TEST_DATA_FOLDER.resolve("invalidInterviewHrManager.json");
    private static final Path DUPLICATE_INTERVIEWS_FILE = TEST_DATA_FOLDER.resolve("duplicateInterviewHrManager.json");

    @Test
    public void toModelType_typicalInterviewsFile_success() throws Exception {
        //this test case reads only interviews
        JsonSerializableHrManagerInterviews dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERVIEWS_FILE,
                JsonSerializableHrManagerInterviews.class).get();
        HrManager hrManagerFromFile = dataFromFile.toModelType();
        HrManager typicalInterviewsHrManager = TypicalPersons.getTypicalHrManagerWithOnlyTypicalInterviews();
        assertEquals(hrManagerFromFile, typicalInterviewsHrManager);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHrManagerInterviews dataFromFile = JsonUtil.readJsonFile(INVALID_INTERVIEWS_FILE,
                JsonSerializableHrManagerInterviews.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableHrManagerInterviews dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERVIEWS_FILE,
                JsonSerializableHrManagerInterviews.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHrManagerInterviews.MESSAGE_DUPLICATE_INTERVIEWS,
                dataFromFile::toModelType);
    }
}
