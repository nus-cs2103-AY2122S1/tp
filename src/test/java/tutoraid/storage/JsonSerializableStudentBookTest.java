package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.commons.util.JsonUtil;
import tutoraid.model.StudentBook;
import tutoraid.testutil.TypicalPersons;
import tutoraid.testutil.Assert;

public class JsonSerializableStudentBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableStudentBook.class).get();
        StudentBook studentBookFromFile = dataFromFile.toModelType();
        StudentBook typicalPersonsStudentBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(studentBookFromFile, typicalPersonsStudentBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableStudentBook.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableStudentBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
