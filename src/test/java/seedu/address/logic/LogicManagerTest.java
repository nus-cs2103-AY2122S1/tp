package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.encryption.Encryption;
import seedu.address.encryption.EncryptionKeyGenerator;
import seedu.address.encryption.EncryptionManager;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private static final String PASSWORD = "password1234";
    private static final String WRONG_PASSWORD = "password123";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ADDRESS_BOOK_ENCRYPTED_FILENAME = "addressBook.enc";
    private static final String ADDRESS_BOOK_JSON_FILENAME = "addressBook.json";
    private static final String USER_PREFERENCE_FILENAME = "userPrefs.json";

    private static final Path TEST_PATH = Paths.get("src", "test", "data", "LogicManagerTest");
    private static final Path MAIN_TEST_PATH = TEST_PATH.resolve("MainDirectory");
    private static final Path MAIN_ENCRYPTED_FILE_PATH = MAIN_TEST_PATH.resolve(ADDRESS_BOOK_ENCRYPTED_FILENAME);
    private static final Path MAIN_JSON_FILE_PATH = MAIN_TEST_PATH.resolve(ADDRESS_BOOK_JSON_FILENAME);
    private static final Path MAIN_PREF_FILE_PATH = MAIN_TEST_PATH.resolve(USER_PREFERENCE_FILENAME);
    private static final Path IO_EXCEPTION_TEST_PATH = TEST_PATH.resolve("IoExceptionDirectory");
    private static final Path IO_EXCEPTION_JSON_FILE_PATH = IO_EXCEPTION_TEST_PATH.resolve(ADDRESS_BOOK_JSON_FILENAME);
    private static final Path IO_EXCEPTION_ENCRYPTED_FILE_PATH =
            IO_EXCEPTION_TEST_PATH.resolve(ADDRESS_BOOK_ENCRYPTED_FILENAME);
    private static final Path IO_EXCEPTION_PREF_FILE_PATH = IO_EXCEPTION_TEST_PATH.resolve(USER_PREFERENCE_FILENAME);

    private static final Model MODEL = new ModelManager();
    private static final AddressBook ADDRESS_BOOK = new AddressBook();
    private static final UserPrefs USER_PREFS = new UserPrefs();
    private static final Model MODEL_WITH_CONTENT = new ModelManager(ADDRESS_BOOK, USER_PREFS);
    private static final LogicManager LOGIC_MANAGER = new LogicManager(MODEL_WITH_CONTENT, null, null, null);

    private Logic logic;
    private Encryption token;

    @BeforeEach
    public void setUp()
            throws UnsupportedPasswordException, NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(MAIN_JSON_FILE_PATH);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(MAIN_PREF_FILE_PATH);
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        token = new EncryptionManager(EncryptionKeyGenerator.generateKey(PASSWORD), CIPHER_TRANSFORMATION);
        FileUtil.createFile(MAIN_ENCRYPTED_FILE_PATH);
        logic = new LogicManager(MODEL, storage, token, MAIN_ENCRYPTED_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        FileUtil.deleteFile(MAIN_JSON_FILE_PATH);
        FileUtil.deleteFile(MAIN_PREF_FILE_PATH);
        FileUtil.deleteFile(MAIN_ENCRYPTED_FILE_PATH);
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
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, MODEL);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() throws IOException {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(IO_EXCEPTION_JSON_FILE_PATH);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(IO_EXCEPTION_PREF_FILE_PATH);
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        FileUtil.createFile(IO_EXCEPTION_ENCRYPTED_FILE_PATH);
        logic = new LogicManager(MODEL, storage, token, IO_EXCEPTION_ENCRYPTED_FILE_PATH);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);

        FileUtil.deleteFile(IO_EXCEPTION_ENCRYPTED_FILE_PATH); // Cleanup
        FileUtil.deleteFile(IO_EXCEPTION_JSON_FILE_PATH); // Cleanup
    }

    @Test
    public void execute_correctPasswordCommand_returnsEmptyOptional() throws IOException, InvalidKeyException {
        FileUtil.createFile(MAIN_JSON_FILE_PATH);
        token.encrypt(MAIN_JSON_FILE_PATH, MAIN_ENCRYPTED_FILE_PATH);
        assertTrue(logic.executePasswordCommand(new PasswordCommand(PASSWORD, WRONG_PASSWORD)).isEmpty());
    }

    @Test
    public void execute_incorrectPasswordCommand_returnsOptionalCommandResult() throws IOException,
            InvalidKeyException {
        FileUtil.createFile(MAIN_JSON_FILE_PATH);
        token.encrypt(MAIN_JSON_FILE_PATH, MAIN_ENCRYPTED_FILE_PATH);
        assertFalse(logic.executePasswordCommand(new PasswordCommand(WRONG_PASSWORD, WRONG_PASSWORD)).isEmpty());
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void getAddressBookReturns_true() {
        assertTrue(LOGIC_MANAGER.getAddressBook().equals(MODEL_WITH_CONTENT.getAddressBook()));
    }

    @Test
    public void getSelectedPersonList_true() {
        assertTrue(LOGIC_MANAGER.getSelectedPersonList().equals(MODEL_WITH_CONTENT.getSelectedPersonList()));
    }

    @Test
    public void getAddressBookFilePath_true() {
        assertTrue(LOGIC_MANAGER.getAddressBookFilePath().equals(MODEL_WITH_CONTENT.getAddressBookFilePath()));
    }

    @Test
    public void getGuiSetting_true() {
        assertTrue(LOGIC_MANAGER.getGuiSettings().equals(MODEL_WITH_CONTENT.getGuiSettings()));
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
        assertEquals(expectedModel, MODEL);
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
        Model expectedModel = new ModelManager(MODEL.getAddressBook(), new UserPrefs());
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
        assertEquals(expectedModel, MODEL);
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
