package seedu.smartnus.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.smartnus.logic.commands.CommandTestUtil.ANSWER_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTION_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTION_DESC_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTION_DESC_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.QUESTION_DESC_3;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.STORAGE_QUESTION_1;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartnus.commons.core.theme.LightTheme;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.storage.JsonSmartNusStorage;
import seedu.smartnus.storage.JsonUserPrefsStorage;
import seedu.smartnus.storage.StorageManager;
import seedu.smartnus.testutil.QuestionBuilder;
import seedu.smartnus.ui.panel.QuestionListPanel;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonSmartNusStorage smartNusStorage =
                new JsonSmartNusStorage(temporaryFolder.resolve("smartNus.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(smartNusStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete question 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonSmartNusIoExceptionThrowingStub
        JsonSmartNusStorage smartNusStorage =
                new JsonSmartNusIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionSmartNus.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(smartNusStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addMcqCommand = AddMcqCommand.COMMAND_WORD + QUESTION_DESC_3 + ANSWER_DESC_1
                + OPTION_DESC_1 + OPTION_DESC_3 + OPTION_DESC_4 + IMPORTANCE_DESC_1;
        Question expectedQuestion = new QuestionBuilder(STORAGE_QUESTION_1).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addQuestion(expectedQuestion);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addMcqCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredQuestionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredQuestionList().remove(0));
    }

    @Test
    public void getFilteredNoteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredNoteList().remove(0));
    }

    @Test
    public void getFilteredQuizQuestionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredQuizQuestionList().remove(0));
    }

    @Test
    public void getFilteredTagStatisticList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTagStatisticList().remove(0));
    }

    @Test
    public void theme_test() {
        logic.setTheme(new Theme());
        assertEquals(new LightTheme(), logic.getTheme());
    }

    @Test
    public void panel_test() {
        logic.setPanel(QuestionListPanel.QUESTION_PANEL);
        assertEquals(QuestionListPanel.QUESTION_PANEL, logic.getPanel());
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
        Model expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
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
    private static class JsonSmartNusIoExceptionThrowingStub extends JsonSmartNusStorage {
        private JsonSmartNusIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSmartNus(ReadOnlySmartNus smartNus, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
