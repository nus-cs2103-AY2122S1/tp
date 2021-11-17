package socialite.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import socialite.commons.core.Messages;
import socialite.logic.commands.AddCommand;
import socialite.logic.commands.CommandResult;
import socialite.logic.commands.CommandTestUtil;
import socialite.logic.commands.ListCommand;
import socialite.logic.commands.exceptions.CommandException;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.CommandHistory;
import socialite.model.Model;
import socialite.model.ModelManager;
import socialite.model.ReadOnlyContactList;
import socialite.model.UserPrefs;
import socialite.model.person.Person;
import socialite.storage.JsonCommandHistoryStorage;
import socialite.storage.JsonContactListStorage;
import socialite.storage.JsonUserPrefsStorage;
import socialite.storage.ProfilePictureStorage;
import socialite.storage.ProfilePictureStorageManager;
import socialite.storage.StorageManager;
import socialite.testutil.Assert;
import socialite.testutil.PersonBuilder;
import socialite.testutil.TypicalPersons;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonContactListStorage contactListStorage =
                new JsonContactListStorage(temporaryFolder.resolve("contactList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonCommandHistoryStorage commandHistoryStorage =
                new JsonCommandHistoryStorage(temporaryFolder.resolve("commandHistory.json"));
        ProfilePictureStorage profilePictureStorage = ProfilePictureStorageManager.getInstance();
        StorageManager storage = new StorageManager(
                contactListStorage, userPrefsStorage, commandHistoryStorage, profilePictureStorage);
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
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonContactListIoExceptionThrowingStub
        JsonContactListStorage contactListStorage =
                new JsonContactListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionContactList.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonCommandHistoryStorage commandHistoryStorage =
                new JsonCommandHistoryStorage(temporaryFolder.resolve("ioExceptionCommandHistory.json"));
        ProfilePictureStorage profilePictureStorage = ProfilePictureStorageManager.getInstance();
        StorageManager storage = new StorageManager(
                contactListStorage, userPrefsStorage, commandHistoryStorage, profilePictureStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.REMARK_DESC_AMY
                + CommandTestUtil.FACEBOOK_DESC_AMY + CommandTestUtil.INSTAGRAM_DESC_AMY
                + CommandTestUtil.TELEGRAM_DESC_AMY + CommandTestUtil.TIKTOK_DESC_AMY
                + CommandTestUtil.TWITTER_DESC_AMY + CommandTestUtil.DATE_DESC_AMY;

        Person expectedPerson = new PersonBuilder(TypicalPersons.AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
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
        Model expectedModel = new ModelManager(
                model.getContactList(), new UserPrefs(), new CommandHistory());
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
        Assert.assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonContactListIoExceptionThrowingStub extends JsonContactListStorage {
        private JsonContactListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
