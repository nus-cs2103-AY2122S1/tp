package seedu.fast.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fast.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.commons.util.JsonUtil;
import seedu.fast.model.Fast;
import seedu.fast.testutil.TypicalPersons;

public class JsonSerializableFastTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFastTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsFast.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonFast.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonFast.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFast dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFast.class).get();
        Fast fastFromFile = dataFromFile.toModelType();
        Fast typicalPersonsFast = TypicalPersons.getTypicalFast();
        assertEquals(fastFromFile, typicalPersonsFast);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFast dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFast.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFast dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFast.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFast.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
