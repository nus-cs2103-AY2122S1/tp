package seedu.address.logic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CustomerCommandTestUtil.ALLERGY_DESC_NONSENSE;
import static seedu.address.logic.commands.CustomerCommandTestUtil.LP_DESC_AMY;
import static seedu.address.logic.commands.CustomerCommandTestUtil.SPECIAL_REQUEST_DESC_LIVE_BAND;
import static seedu.address.logic.commands.CustomerCommandTestUtil.VALID_ALLERGY_NONSENSE;
import static seedu.address.logic.commands.CustomerCommandTestUtil.VALID_SPECIAL_REQUEST_LIVE_BAND;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.LEAVES_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SHIFTS_DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DELIVERY_DETAILS_DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.SUPPLY_TYPE_DESC_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_AMY;
import static seedu.address.testutil.TypicalEmployees.AMY_EMPLOYEE;
import static seedu.address.testutil.TypicalSuppliers.BOB;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddEmployeeCommand;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.storage.JsonRhrhStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.SupplierBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonRhrhStorage rhrhStorage =
                new JsonRhrhStorage(temporaryFolder.resolve("rhrhStorage.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(rhrhStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCustomerCommand = "deleteC 9";
        assertCommandException(deleteCustomerCommand, MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void executeAddCustomer_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonRhrhIoExceptionThrowingStub
        JsonRhrhStorage rhrhStorage =
                new JsonRhrhIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionRhrh.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(rhrhStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCustomerCommand = AddCustomerCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + LP_DESC_AMY + ALLERGY_DESC_NONSENSE
                + SPECIAL_REQUEST_DESC_LIVE_BAND + TAG_DESC_FRIEND;
        Customer expectedCustomer = new CustomerBuilder(CUSTOMER_AMY).withAllergies(VALID_ALLERGY_NONSENSE)
                .withSpecialRequests(VALID_SPECIAL_REQUEST_LIVE_BAND).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addCustomer(expectedCustomer);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCustomerCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void executeAddEmployee_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonRhrhIoExceptionThrowingStub
        JsonRhrhStorage rhrhStorage =
                new JsonRhrhIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionRhrh.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(rhrhStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addEmployeeCommand = AddEmployeeCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + LEAVES_DESC_AMY + SALARY_DESC_AMY + JOB_TITLE_DESC_AMY + SHIFTS_DESC_AMY
                + TAG_DESC_FRIEND;
        Employee expectedEmployee = new EmployeeBuilder(AMY_EMPLOYEE).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addEmployee(expectedEmployee);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addEmployeeCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void executeAddSupplier_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonRhrhIoExceptionThrowingStub
        JsonRhrhStorage rhrhStorage =
                new JsonRhrhIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionRhrh.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(rhrhStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addSupplierCommand = AddSupplierCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + SUPPLY_TYPE_DESC_BOB
                + DELIVERY_DETAILS_DESC_BOB;
        Supplier expectedSupplier = new SupplierBuilder(BOB).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addSupplier(expectedSupplier);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addSupplierCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCustomerList().remove(0));
    }

    @Test
    public void getFilteredEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEmployeeList().remove(0));
    }

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredSupplierList().remove(0));
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
        Model expectedModel = new ModelManager(model.getRhrh(), new UserPrefs());
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
    private static class JsonRhrhIoExceptionThrowingStub extends JsonRhrhStorage {
        private JsonRhrhIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveRhrh(ReadOnlyRhrh rhrh, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
