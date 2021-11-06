package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.COSTPRICE_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.COUNT_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.SALESPRICE_DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListInventoryCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.BookKeeping;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.storage.JsonBookKeepingStorage;
import seedu.address.storage.JsonInventoryStorage;
import seedu.address.storage.JsonTransactionStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.ItemBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInventoryStorage inventoryStorage =
                new JsonInventoryStorage(temporaryFolder.resolve("inventory.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonBookKeepingStorage bookKeepingStorage = new JsonBookKeepingStorage(temporaryFolder
                .resolve("bookKeeping.json"));
        JsonTransactionStorage transactionStorage = new JsonTransactionStorage(temporaryFolder
                .resolve("transactions.json"));
        StorageManager storage = new StorageManager(inventoryStorage, userPrefsStorage,
                transactionStorage, bookKeepingStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String removeCommand = "remove apple pie";
        assertCommandException(removeCommand, String.format(RemoveCommand.MESSAGE_ITEM_NOT_FOUND, "apple pie"));
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListInventoryCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListInventoryCommand.MESSAGE_SUCCESS_INVENTORY, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonInventoryStorage inventoryStorage =
                new JsonInventoryIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonBookKeepingStorage bookKeepingStorage = new JsonBookKeepingStorage(temporaryFolder
                .resolve("bookKeeping.json"));
        JsonTransactionStorage transactionStorage = new JsonTransactionStorage(temporaryFolder
                .resolve("transactions.json"));
        StorageManager storage = new StorageManager(inventoryStorage, userPrefsStorage,
                transactionStorage, bookKeepingStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + " " + VALID_NAME_BAGEL + ID_DESC_BAGEL + COUNT_DESC_BAGEL
                + COSTPRICE_DESC_BAGEL + SALESPRICE_DESC_BAGEL;
        Item expectedItem = new ItemBuilder(BAGEL).withTags().build();
        BookKeeping bookKeeping = new BookKeeping();
        bookKeeping.addCost(BAGEL.getCostPrice(), 5);
        ModelManager expectedModel = new ModelManager(new Inventory(), new UserPrefs(),
                new TransactionList(), bookKeeping);
        expectedModel.addItem(expectedItem);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredDisplayList().remove(0));
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
        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
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
    private static class JsonInventoryIoExceptionThrowingStub extends JsonInventoryStorage {
        private JsonInventoryIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveInventory(ReadOnlyInventory addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
