package seedu.intern.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intern.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.commons.util.JsonUtil;
import seedu.intern.model.InternWatcher;
import seedu.intern.testutil.TypicalApplicants;

public class JsonSerializableInternWatcherTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializableInternWatcherTest");
    private static final Path TYPICAL_APPLICANTS_FILE = TEST_DATA_FOLDER.resolve("typicalApplicantsInternWatcher.json");
    private static final Path INVALID_APPLICANT_FILE = TEST_DATA_FOLDER.resolve("invalidApplicantInternWatcher.json");
    private static final Path DUPLICATE_APPLICANT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateApplicantInternWatcher.json");

    @Test
    public void toModelType_typicalApplicantsFile_success() throws Exception {
        JsonSerializableInternWatcher dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICANTS_FILE,
                JsonSerializableInternWatcher.class).get();
        InternWatcher internWatcherFromFile = dataFromFile.toModelType();
        InternWatcher typicalApplicantsInternWatcher = TypicalApplicants.getTypicalInternWatcher();
        assertEquals(internWatcherFromFile, typicalApplicantsInternWatcher);
    }

    @Test
    public void toModelType_invalidApplicantFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternWatcher dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANT_FILE,
                JsonSerializableInternWatcher.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicants_throwsIllegalValueException() throws Exception {
        JsonSerializableInternWatcher dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANT_FILE,
                JsonSerializableInternWatcher.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternWatcher.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }

}
