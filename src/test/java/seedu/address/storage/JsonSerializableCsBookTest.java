package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
//import seedu.address.model.CsBook;
//import seedu.address.testutil.TypicalStudents;

public class JsonSerializableCsBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCsBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsCsBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentCsBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentCsBook.json");

    //TODO Fix test case
    /*
    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableCsBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableCsBook.class).get();
        CsBook addressBookFromFile = dataFromFile.toModelType();
        CsBook typicalStudentsAddressBook = TypicalStudents.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalStudentsAddressBook);
    }

     */

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCsBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableCsBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableCsBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableCsBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCsBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
