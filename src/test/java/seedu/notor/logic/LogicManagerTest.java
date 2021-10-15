package seedu.notor.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.notor.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.notor.testutil.Assert.assertThrows;
import static seedu.notor.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.ListCommand;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.person.Person;
import seedu.notor.storage.JsonNotorStorage;
import seedu.notor.storage.JsonUserPrefsStorage;
import seedu.notor.storage.StorageManager;
import seedu.notor.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonNotorStorage notorStorage =
                new JsonNotorStorage(temporaryFolder.resolve("notor.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(notorStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
        Executor.setup(model);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertExecuteException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonNotorIoExceptionThrowingStub
        JsonNotorStorage notorStorage =
                new JsonNotorIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionNotor.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(notorStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = PersonCreateCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.createPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertExecuteFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertExecuteFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, ExecuteException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertExecuteFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertExecuteFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a ExecuteException is thrown and that the result message is correct.
     *
     * @see #assertExecuteFailure(String, Class, String, Model)
     */
    private void assertExecuteException(String inputCommand, String expectedMessage) {
        assertExecuteFailure(inputCommand, ExecuteException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertExecuteFailure(String, Class, String, Model)
     */
    private void assertExecuteFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getNotor(), new UserPrefs());
        assertExecuteFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertExecuteFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonNotorIoExceptionThrowingStub extends JsonNotorStorage {
        private JsonNotorIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveNotor(ReadOnlyNotor notor, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
