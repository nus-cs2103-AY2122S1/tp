package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.siasa.testutil.Assert.assertThrows;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.commons.util.JsonUtil;
import seedu.siasa.model.Siasa;
import seedu.siasa.testutil.TypicalPolicyBook;

public class JsonSerializablePolicyBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePolicyBookTest");
    private static final Path TYPICAL_POLICIES_FILE = TEST_DATA_FOLDER.resolve("typicalPolicyBook.json");
    private static final Path INVALID_POLICY_FILE = TEST_DATA_FOLDER.resolve("invalidPolicyPolicyBook.json");
    private static final Path DUPLICATE_POLICY_FILE = TEST_DATA_FOLDER.resolve("duplicatePolicyPolicyBook.json");

    /*
    TODO: Fix test
    @Test
    public void toModelType_typicalPoliciesFile_success() throws Exception {
        JsonSerializablePolicyBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_POLICIES_FILE,
            JsonSerializablePolicyBook.class).get();
        Siasa siasaFromFile = dataFromFile.toModelType();
        Siasa typicalPolicies = TypicalPolicyBook.getTypicalPolicyBook();
        assertEquals(siasaFromFile, typicalPolicies);
    }
     */


    @Test
    public void toModelType_invalidPolicyFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePolicyBook dataFromFile = JsonUtil.readJsonFile(INVALID_POLICY_FILE,
            JsonSerializablePolicyBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePolicyBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_POLICY_FILE,
            JsonSerializablePolicyBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePolicyBook.MESSAGE_DUPLICATE_POLICY,
            dataFromFile::toModelType);
    }
}
