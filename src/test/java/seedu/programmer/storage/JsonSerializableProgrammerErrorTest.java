package seedu.programmer.storage;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.commons.util.JsonUtil;
// import seedu.programmer.model.ProgrammerError;
// import seedu.programmer.testutil.TypicalStudents;

class JsonSerializableProgrammerErrorTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableProgrammerErrorTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsProgrammerError.json");
    private static final Path INVALID_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("invalidStudentProgrammerError.json");
    private static final Path DUPLICATE_STUDENTS_FILE = TEST_DATA_FOLDER
            .resolve("duplicateStudentProgrammerError.json");

    /* TODO: Update `addressBookFromFile` and `typicalAddressBook` to have the same fields and data
    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableProgrammerError dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableProgrammerError.class).orElse(null);
        System.out.println(dataFromFile);
        assert dataFromFile != null;
        ProgrammerError addressBookFromFile = dataFromFile.toModelType();
        ProgrammerError typicalStudentsAddressBook = TypicalStudents.getTypicalProgrammerError();
        assertEquals(addressBookFromFile, typicalStudentsAddressBook);
    }
    */

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableProgrammerError dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENTS_FILE,
                JsonSerializableProgrammerError.class).orElse(null);
        assert dataFromFile != null;
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableProgrammerError dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENTS_FILE,
                JsonSerializableProgrammerError.class).orElse(null);
        assert dataFromFile != null;
        assertThrows(IllegalValueException.class, JsonSerializableProgrammerError.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
