package seedu.notor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notor.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.commons.util.JsonUtil;
import seedu.notor.model.Notor;
import seedu.notor.testutil.TypicalPersons;

public class JsonSerializableNotorTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNotorTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsNotor.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonNotor.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonNotor.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableNotor dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableNotor.class).get();
        Notor notorFromFile = dataFromFile.toModelType();
        Notor typicalPersonsNotor = TypicalPersons.getTypicalNotor();
        assertEquals(notorFromFile, typicalPersonsNotor);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNotor dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableNotor.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableNotor dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableNotor.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNotor.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
