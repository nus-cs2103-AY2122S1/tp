package seedu.edrecord.storage;

import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalModules.setTypicalModuleSystem;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.commons.util.JsonUtil;

public class JsonSerializableEdRecordTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEdRecordTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsEdRecord.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonEdRecord.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonEdRecord.json");

    // TODO: Add assignments to a person's modules in json format, so that this tests can pass.
    /*:ee*/
    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEdRecord dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableEdRecord.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        setTypicalModuleSystem();
        JsonSerializableEdRecord dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableEdRecord.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEdRecord.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
