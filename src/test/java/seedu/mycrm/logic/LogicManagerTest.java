package seedu.mycrm.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.mycrm.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.mycrm.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalContacts.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.contacts.AddContactCommand;
import seedu.mycrm.logic.commands.contacts.ListContactCommand;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.storage.JsonMyCrmStorage;
import seedu.mycrm.storage.JsonUserPrefsStorage;
import seedu.mycrm.storage.StorageManager;
import seedu.mycrm.testutil.ContactBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonMyCrmStorage myCrmStorage =
                new JsonMyCrmStorage(temporaryFolder.resolve("myCrm.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(myCrmStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteContactCommand = "deleteContact 9";
        assertCommandException(deleteContactCommand, MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListContactCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListContactCommand.MESSAGE_SUCCESS_NOT_HIDDEN, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonMyCrmIoExceptionThrowingStub
        JsonMyCrmStorage myCrmStorage =
                new JsonMyCrmIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionMyCrm.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(myCrmStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addContactCommand = AddContactCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Contact expectedContact = new ContactBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addContact(expectedContact);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addContactCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredContactList().remove(0));
    }

    @Test
    public void getFilteredTemplateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTemplateList().remove(0));
    }

    @Test
    public void getFilteredMailList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredMailList().remove(0));
    }

    @Test
    public void getFilteredProductList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredProductList().remove(0));
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
        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
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
    private static class JsonMyCrmIoExceptionThrowingStub extends JsonMyCrmStorage {
        private JsonMyCrmIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveMyCrm(ReadOnlyMyCrm myCrm, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
