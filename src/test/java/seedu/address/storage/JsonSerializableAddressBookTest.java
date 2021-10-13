package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalLessons;
import seedu.address.testutil.TypicalStudents;
import seedu.address.testutil.TypicalTuition;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsAddressBook.json");
    private static final Path TYPICAL_LESSONS_FILE = TEST_DATA_FOLDER.resolve("typicalLessonsAddressBook.json");
    private static final Path TYPICAL_TUITION_FILE = TEST_DATA_FOLDER.resolve("typicalTuitionAddressBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAddressBook.json");
    private static final Path INVALID_LESSON_FILE = TEST_DATA_FOLDER.resolve("invalidLessonAddressBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentAddressBook.json");
    private static final Path DUPLICATE_LESSON_FILE = TEST_DATA_FOLDER.resolve("duplicateLessonAddressBook.json");
    private static final Path DUPLICATE_ENROLLMENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateEnrollmentAddressBook.json");
    private static final Path ENROLLED_LESSON_NOT_FOUND_FILE = TEST_DATA_FOLDER
            .resolve("enrolledLessonNotFoundAddressBook.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalStudentsAddressBook = TypicalStudents.getTypicalAddressBook();
        assertEquals(typicalStudentsAddressBook, addressBookFromFile);
    }

    @Test
    public void toModelType_typicalLessonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_LESSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalLessonsAddressBook = TypicalLessons.getTypicalAddressBook();
        assertEquals(typicalLessonsAddressBook, addressBookFromFile);
    }

    @Test
    public void toModelType_typicalTuitionFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUITION_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTuitionAddressBook = TypicalTuition.getTypicalAddressBook();
        assertEquals(typicalTuitionAddressBook, addressBookFromFile);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidLessonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_LESSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LESSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_LESSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_doubleEnrollment_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENROLLMENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ENROLLMENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_lessonNotFound_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(ENROLLED_LESSON_NOT_FOUND_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_INVALID_LESSON_CODE,
                dataFromFile::toModelType);
    }
}
