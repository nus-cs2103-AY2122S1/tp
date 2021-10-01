package seedu.academydirectory.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.exceptions.IllegalValueException;
import seedu.academydirectory.commons.util.JsonUtil;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.testutil.TypicalPersons;

public class JsonSerializableAcademyDirectoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAcademyDirectoryTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAcademyDirectory.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAcademyDirectory.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAcademyDirectory.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAcademyDirectory dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAcademyDirectory.class).get();
        AcademyDirectory addressBookFromFile = dataFromFile.toModelType();
        AcademyDirectory typicalPersonsAddressBook = TypicalPersons.getTypicalAcademyDirectory();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAcademyDirectory dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableAcademyDirectory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAcademyDirectory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAcademyDirectory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAcademyDirectory.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
