package seedu.plannermd.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.plannermd.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.commons.util.JsonUtil;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.testutil.TypicalPersons;

public class JsonSerializablePlannerMdTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePlannerMdTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsPlannerMd.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonPlannerMd.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonPlannerMd.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializablePlannerMd.class).get();
        PlannerMd plannerMdFromFile = dataFromFile.toModelType();
        PlannerMd typicalPersonsPlannerMd = TypicalPersons.getTypicalPlannerMd();
        assertEquals(plannerMdFromFile, typicalPersonsPlannerMd);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializablePlannerMd.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializablePlannerMd.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePlannerMd.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
