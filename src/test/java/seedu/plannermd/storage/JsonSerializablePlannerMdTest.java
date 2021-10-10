package seedu.plannermd.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.plannermd.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.commons.util.JsonUtil;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.testutil.TypicalPlannerMd;

public class JsonSerializablePlannerMdTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePlannerMdTest");
    private static final Path TYPICAL_PLANNERMD_FILE = TEST_DATA_FOLDER.resolve("typicalPlannerMd.json");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsPlannerMd.json");
    private static final Path TYPICAL_DOCTORS_FILE = TEST_DATA_FOLDER.resolve("typicalDoctorsPlannerMd.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientPlannerMd.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientPlannerMd.json");
    private static final Path INVALID_DOCTORS_FILE = TEST_DATA_FOLDER.resolve("invalidDoctorPlannerMd.json");
    private static final Path DUPLICATE_DOCTORS_FILE = TEST_DATA_FOLDER.resolve("duplicateDoctorPlannerMd.json");

    @Test
    public void toModelType_typicalPlannerMdFile_success() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(TYPICAL_PLANNERMD_FILE, JsonSerializablePlannerMd.class).get();
        PlannerMd plannerMdFromFile = dataFromFile.toModelType();
        PlannerMd typicalPlannerMd = TypicalPlannerMd.getTypicalPlannerMd();
        assertEquals(plannerMdFromFile, typicalPlannerMd);
    }

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(TYPICAL_PATIENTS_FILE, JsonSerializablePlannerMd.class).get();
        PlannerMd plannerMdFromFile = dataFromFile.toModelType();
        PlannerMd typicalPlannerMd = TypicalPlannerMd.getTypicalPatientsPlannerMd();
        assertEquals(plannerMdFromFile, typicalPlannerMd);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(INVALID_PATIENT_FILE, JsonSerializablePlannerMd.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(DUPLICATE_PATIENT_FILE, JsonSerializablePlannerMd.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePlannerMd.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalDoctorsFile_success() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(TYPICAL_DOCTORS_FILE, JsonSerializablePlannerMd.class).get();
        PlannerMd plannerMdFromFile = dataFromFile.toModelType();
        PlannerMd typicalPlannerMd = TypicalPlannerMd.getTypicalDoctorsPlannerMd();
        assertEquals(plannerMdFromFile, typicalPlannerMd);
    }

    @Test
    public void toModelType_invalidDoctorFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(INVALID_DOCTORS_FILE, JsonSerializablePlannerMd.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDoctors_throwsIllegalValueException() throws Exception {
        JsonSerializablePlannerMd dataFromFile = JsonUtil
                .readJsonFile(DUPLICATE_DOCTORS_FILE, JsonSerializablePlannerMd.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePlannerMd.MESSAGE_DUPLICATE_DOCTOR,
                dataFromFile::toModelType);
    }
}
