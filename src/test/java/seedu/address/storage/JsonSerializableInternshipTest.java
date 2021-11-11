package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Internship;
import seedu.address.testutil.TypicalApplications;

public class JsonSerializableInternshipTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternshipTest");
    private static final Path TYPICAL_APPLICATIONS_FILE =
            TEST_DATA_FOLDER.resolve("typicalApplicationsInternship.json");
    private static final Path INVALID_APPLICATION_FILE = TEST_DATA_FOLDER.resolve("invalidApplicationInternship.json");
    private static final Path DUPLICATE_APPLICATION_FILE =
            TEST_DATA_FOLDER.resolve("duplicateApplicationInternship.json");

    @Test
    public void toModelType_typicalApplicationsFile_success() throws Exception {
        JsonSerializableInternship dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICATIONS_FILE,
                JsonSerializableInternship.class).get();
        Internship internshipFromFile = dataFromFile.toModelType();
        Internship typicalInternship = TypicalApplications.getTypicalInternship();
        assertEquals(internshipFromFile, typicalInternship);
    }

    @Test
    public void toModelType_invalidApplicationFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternship dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICATION_FILE,
                JsonSerializableInternship.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplications_throwsIllegalValueException() throws Exception {
        JsonSerializableInternship dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICATION_FILE,
                JsonSerializableInternship.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternship.MESSAGE_DUPLICATE_APPLICATION,
                dataFromFile::toModelType);
    }
}
