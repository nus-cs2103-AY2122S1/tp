package seedu.sourcecontrol.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sourcecontrol.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.sourcecontrol.commons.exceptions.IllegalValueException;
import seedu.sourcecontrol.commons.util.JsonUtil;
import seedu.sourcecontrol.model.SourceControl;
import seedu.sourcecontrol.testutil.TypicalPersons;

public class JsonSerializableSourceControlTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSourceControlTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsSourceControl.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonSourceControl.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonSourceControl.json");
    private static final Path DUPLICATE_GROUP_FILE = TEST_DATA_FOLDER.resolve("duplicateGroupSourceControl.json");
    private static final Path DUPLICATE_ASSESSMENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateAssessmentSourceControl.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSourceControl dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableSourceControl.class).get();
        SourceControl sourceControlFromFile = dataFromFile.toModelType();
        SourceControl typicalPersonsSourceControl = TypicalPersons.getTypicalSourceControl();
        assertEquals(sourceControlFromFile, typicalPersonsSourceControl);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSourceControl dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableSourceControl.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSourceControl dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableSourceControl.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSourceControl.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGroups_throwsIllegalValueException() throws Exception {
        JsonSerializableSourceControl dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GROUP_FILE,
                JsonSerializableSourceControl.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSourceControl.MESSAGE_DUPLICATE_GROUP,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAssessments_throwsIllegalValueException() throws Exception {
        JsonSerializableSourceControl dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ASSESSMENT_FILE,
                JsonSerializableSourceControl.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSourceControl.MESSAGE_DUPLICATE_ASSESSMENT,
                dataFromFile::toModelType);
    }

}
