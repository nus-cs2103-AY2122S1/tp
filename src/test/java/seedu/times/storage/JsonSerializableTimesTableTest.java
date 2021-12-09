package seedu.times.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.times.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.commons.util.JsonUtil;
import seedu.times.model.TimesTable;
import seedu.times.testutil.TypicalTimestable;

public class JsonSerializableTimesTableTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTimesTableTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTimesTable.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTimesTable.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTimesTable.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTimesTable dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTimesTable.class).get();
        TimesTable timesTableFromFile = dataFromFile.toModelType();
        TimesTable typicalPersonsTimesTable = TypicalTimestable.getTypicalTimesTable();
        assertEquals(timesTableFromFile, typicalPersonsTimesTable);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTimesTable dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTimesTable.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTimesTable dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTimesTable.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTimesTable.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
