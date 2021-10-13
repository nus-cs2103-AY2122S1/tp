package safeforhall.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.logic.commands.CommandTestUtil.COLLECTION_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.FET_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VACCSTATUS_DESC_AMY;
import static safeforhall.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.AddCommand;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.ListCommand;
import safeforhall.logic.commands.ViewCommand;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.ReadOnlyAddressBook;
import safeforhall.model.UserPrefs;
import safeforhall.model.person.Person;
import safeforhall.storage.JsonAddressBookStorage;
import safeforhall.storage.JsonUserPrefsStorage;
import safeforhall.storage.StorageManager;
import safeforhall.testutil.PersonBuilder;
import safeforhall.testutil.TypicalPersons;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("safeforhall.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validFetCommand_success() throws Exception {
        String listCommand = "list k/f d1/10-10-2021";
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS_FET, model);
    }

    @Test
    public void execute_validArtCommand_success() throws Exception {
        String listCommand = "list k/c d1/10-10-2021";
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS_ART, model);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ViewCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ViewCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_validFindCommand_success() throws Exception {
        String findCommand = "find n/alice v/t";
        assertCommandSuccess(findCommand,
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()), model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + ROOM_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + VACCSTATUS_DESC_AMY + FACULTY_DESC_AMY + FET_DESC_AMY + COLLECTION_DESC_AMY;
        Person expectedPerson = new PersonBuilder(TypicalPersons.AMY).build();
        ModelManager expectedModel = new ModelManager();
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
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
