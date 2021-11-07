package tutoraid.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static tutoraid.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static tutoraid.logic.commands.CommandTestUtil.CAPACITY_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.LESSON_NAME_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_NAME_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.PARENT_PHONE_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.PRICE_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.STUDENT_PHONE_DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.TIMING_DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.VALID_ADD_LESSON_COMMAND;
import static tutoraid.logic.commands.CommandTestUtil.VALID_ADD_STUDENT_COMMAND;
import static tutoraid.testutil.Assert.assertThrows;
import static tutoraid.testutil.TypicalLessons.MATHS_TWO;
import static tutoraid.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tutoraid.commons.core.Messages;
import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.logic.commands.CommandResult;
import tutoraid.logic.commands.ListCommand;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.LessonBook;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.UserPrefs;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;
import tutoraid.model.util.SampleDataUtil;
import tutoraid.storage.JsonTutorAidLessonStorage;
import tutoraid.storage.JsonTutorAidStudentStorage;
import tutoraid.storage.JsonUserPrefsStorage;
import tutoraid.storage.StorageManager;
import tutoraid.testutil.LessonBuilder;
import tutoraid.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() throws DataConversionException {
        JsonTutorAidLessonStorage lessonBookStorage =
                new JsonTutorAidLessonStorage(temporaryFolder.resolve("tutorAidLessons.json"));
        JsonTutorAidStudentStorage studentBookStorage =
                new JsonTutorAidStudentStorage(temporaryFolder.resolve("tutorAidStudents.json"),
                        lessonBookStorage.readLessonBook().orElseGet(SampleDataUtil::getSampleLessonBook));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(studentBookStorage, lessonBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "del -s 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, Messages.MESSAGE_LIST_SUCCESS, model);
    }

    @Test
    public void execute_studentsStorageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTutorAidIoExceptionThrowingStub
        JsonTutorAidStudentStorage studentBookStorage =
                new JsonTutorAidStudentIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionStudentBook.json"));
        JsonTutorAidLessonStorage lessonBookStorage =
                new JsonTutorAidLessonIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionLessonBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(studentBookStorage, lessonBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add student command
        String addCommand = VALID_ADD_STUDENT_COMMAND + STUDENT_NAME_DESC_AMY + STUDENT_PHONE_DESC_AMY
                + PARENT_NAME_DESC_AMY + PARENT_PHONE_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void execute_lessonsStorageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTutorAidIoExceptionThrowingStub
        JsonTutorAidStudentStorage studentBookStorage =
                new JsonTutorAidStudentIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionStudentBook.json"));
        JsonTutorAidLessonStorage lessonBookStorage =
                new JsonTutorAidLessonIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionLessonBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(studentBookStorage, lessonBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add lesson command
        String addLessonCommand = VALID_ADD_LESSON_COMMAND + LESSON_NAME_DESC_MATH + CAPACITY_DESC_MATH
                + PRICE_DESC_MATH + TIMING_DESC_MATH;
        Lesson expectedLesson = new LessonBuilder(MATHS_TWO).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addLesson(expectedLesson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addLessonCommand, CommandException.class, expectedMessage, expectedModel);

    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
    }

    @Test
    public void getFilteredLessonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredLessonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called on a student book.
     */
    private static class JsonTutorAidStudentIoExceptionThrowingStub extends JsonTutorAidStudentStorage {
        private JsonTutorAidStudentIoExceptionThrowingStub(Path filePath) {
            super(filePath, new LessonBook());
        }

        @Override
        public void saveStudentBook(ReadOnlyStudentBook studentBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called on a lesson book.
     */
    private static class JsonTutorAidLessonIoExceptionThrowingStub extends JsonTutorAidLessonStorage {
        private JsonTutorAidLessonIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
