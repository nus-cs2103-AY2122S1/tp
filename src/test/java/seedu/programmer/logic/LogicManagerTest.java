package seedu.programmer.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASS_ID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.ListCommand;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Student;
import seedu.programmer.storage.JsonProgrammerErrorStorage;
import seedu.programmer.storage.JsonUserPrefsStorage;
import seedu.programmer.storage.StorageManager;
import seedu.programmer.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonProgrammerErrorStorage programmerErrorStorage =
                new JsonProgrammerErrorStorage(temporaryFolder.resolve("ProgrammerError.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(programmerErrorStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "inavlid command";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommandEmptyModel_returnsNoStudentsMessage() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_NO_STUDENTS, model);
    }

    @Test
    public void execute_validCommandFilledModel_returnsSuccessMessage() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        model.addStudent(new StudentBuilder().build());
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonProgrammerErrorIoExceptionThrowingStub
        JsonProgrammerErrorStorage programmerErrorStorage =
                new JsonProgrammerErrorIoExceptionThrowingStub(temporaryFolder
                        .resolve("ioExceptionProgrammerError.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(programmerErrorStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + STUDENT_ID_DESC_AMY + CLASS_ID_DESC_AMY
                + EMAIL_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).buildNoLab();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
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
        Model expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());
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
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonProgrammerErrorIoExceptionThrowingStub extends JsonProgrammerErrorStorage {
        private JsonProgrammerErrorIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveProgrammerError(ReadOnlyProgrammerError programmerError, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
