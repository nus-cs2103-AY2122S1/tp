package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GUEST_PASSPORT_NUMBER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_VENDORID;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ALICE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_UNUSED;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_UNUSED;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.guest.CheckInNewGuestCommand;
import seedu.address.logic.commands.guest.ListGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.archive.JsonArchiveStorage;
import seedu.address.storage.guest.JsonGuestBookStorage;
import seedu.address.storage.vendor.JsonVendorBookStorage;
import seedu.address.testutil.guest.GuestBuilder;


public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonGuestBookStorage guestBookStorage =
                new JsonGuestBookStorage(temporaryFolder.resolve("guests.json"));
        JsonVendorBookStorage vendorBookStorage = new JsonVendorBookStorage(temporaryFolder.resolve("vendors.json"));
        JsonArchiveStorage archiveStorage = new JsonArchiveStorage(temporaryFolder.resolve("archive.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage =
                new StorageManager(guestBookStorage, vendorBookStorage, userPrefsStorage, archiveStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_vendorIdCommandExecutionError_throwsCommandException() {
        // checks if deleting an unused vendor id is possible
        String deleteCommand = "deletevendor vid/" + VENDOR_ID_UNUSED.toString();
        assertCommandException(deleteCommand, MESSAGE_INVALID_VENDORID);
    }

    @Test
    public void execute_passportNumberCommandExecutionError_throwsCommandException() {
        // checks if deleting an unused passport number is possible
        String deleteCommand = "deleteguest pn/" + PASSPORT_NUMBER_UNUSED.toString();
        assertCommandException(deleteCommand, MESSAGE_INVALID_GUEST_PASSPORT_NUMBER);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListGuestCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListGuestCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonGuestBookStorage guestBookStorage =
                new JsonGuestBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionGuestBook.json"));
        JsonVendorBookStorage vendorBookStorage =
                new JsonVendorBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionVendorBook.json"));
        JsonArchiveStorage archiveStorage =
                new JsonArchiveIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionArchive.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage =
                new StorageManager(guestBookStorage, vendorBookStorage, userPrefsStorage, archiveStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand =
                CheckInNewGuestCommand.COMMAND_WORD + PASSPORT_NUMBER_DESC_ALICE + NAME_DESC_ALICE
                        + ROOM_NUMBER_DESC_ALICE + EMAIL_DESC_ALICE + TAG_DESC_ALICE;
        Guest expectedGuest = new GuestBuilder(ALICE_GUEST).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addGuest(expectedGuest);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredGuestList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel =
                new ModelManager(model.getGuestBook(), model.getVendorBook(), new UserPrefs(), model.getArchive());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
    private static class JsonGuestBookIoExceptionThrowingStub extends JsonGuestBookStorage {
        private JsonGuestBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveGuestBook(ReadOnlyGuestBook guestBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonVendorBookIoExceptionThrowingStub extends JsonVendorBookStorage {
        private JsonVendorBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveVendorBook(ReadOnlyVendorBook vendorBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonArchiveIoExceptionThrowingStub extends JsonArchiveStorage {
        private JsonArchiveIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveArchive(ReadOnlyGuestBook archive, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }


}
