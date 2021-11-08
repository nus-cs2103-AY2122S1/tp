package seedu.edrecord.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.edrecord.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.edrecord.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.GROUP_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.INFO_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.edrecord.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalModules.getTypicalModuleSystem;
import static seedu.edrecord.testutil.TypicalModules.setTypicalModuleSystem;
import static seedu.edrecord.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.edrecord.logic.commands.AddCommand;
import seedu.edrecord.logic.commands.CommandResult;
import seedu.edrecord.logic.commands.ListCommand;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.ModelManager;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.storage.JsonEdRecordStorage;
import seedu.edrecord.storage.JsonUserPrefsStorage;
import seedu.edrecord.storage.StorageManager;
import seedu.edrecord.storage.module.JsonModuleSystemStorage;
import seedu.edrecord.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonEdRecordStorage edRecordStorage =
                new JsonEdRecordStorage(temporaryFolder.resolve("edRecord.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonModuleSystemStorage moduleSystemStorage = new JsonModuleSystemStorage(
                temporaryFolder.resolve("moduleSystem.json"));
        StorageManager storage = new StorageManager(edRecordStorage, moduleSystemStorage, userPrefsStorage);
        model.setModuleSystem(getTypicalModuleSystem());
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonEdRecordIoExceptionThrowingStub
        JsonEdRecordStorage edRecordStorage =
                new JsonEdRecordIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionEdRecord.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonModuleSystemStorage moduleSystemStorage =
                new JsonModuleSystemStorage(temporaryFolder.resolve("ioExceptionModuleSystem.json"));
        StorageManager storage = new StorageManager(edRecordStorage, moduleSystemStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
        setTypicalModuleSystem();

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + INFO_DESC_AMY + MODULE_DESC_AMY + GROUP_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager(new EdRecord(), getTypicalModuleSystem(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
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
        Model expectedModel = new ModelManager(model.getEdRecord(), model.getModuleSystem(), new UserPrefs());
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
    private static class JsonEdRecordIoExceptionThrowingStub extends JsonEdRecordStorage {
        private JsonEdRecordIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveEdRecord(ReadOnlyEdRecord edRecord, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
