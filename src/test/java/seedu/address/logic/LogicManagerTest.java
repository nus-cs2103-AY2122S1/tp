package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.PermissionException;
import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.status.LoginStatus;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.storage.JsonAccountStorage;
import seedu.address.storage.JsonEzFoodieStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.MemberBuilder;

/**
 * Tests the functionalities of
 * {@code LogicManager}.
 */
public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAccountStorage accountStorage =
                new JsonAccountStorage(temporaryFolder.resolve("account.json"));
        JsonEzFoodieStorage ezFoodieStorage =
                new JsonEzFoodieStorage(temporaryFolder.resolve("ezfoodie.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(accountStorage, ezFoodieStorage, userPrefsStorage);
        logic = new LogicManager(model, storage, ExecutionStatus.TEST);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        String deleteCommand = DeleteCommand.COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_INDEX + " 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        LoginStatus.setLoginStatus(LoginStatus.STAFF);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD + " " + PREFIX_MEMBER;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonEzFoodieIoExceptionThrowingStub
        JsonAccountStorage accountStorage =
                new JsonAccountIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAccount.json"));
        JsonEzFoodieStorage ezFoodieStorage =
                new JsonEzFoodieIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionEzFoodie.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(accountStorage, ezFoodieStorage, userPrefsStorage);
        logic = new LogicManager(model, storage, ExecutionStatus.TEST);

        // Execute add command
        String addMemberCommand = AddMemberCommand.COMMAND_WORD + MEMBER_DESC + NAME_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;
        Member expectedMember = new MemberBuilder(AMY).withCredit("0").withPoint("0").withTags()
                .withTransactions().withReservations().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addMember(expectedMember);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addMemberCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getUpdatedMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getUpdatedMemberList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, PermissionException {
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
        Model expectedModel = new ModelManager(model.getAccount(), model.getEzFoodie(), new UserPrefs());
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
    private static class JsonAccountIoExceptionThrowingStub extends JsonAccountStorage {
        private JsonAccountIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAccount(ReadOnlyAccount account, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonEzFoodieIoExceptionThrowingStub extends JsonEzFoodieStorage {
        private JsonEzFoodieIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveEzFoodie(ReadOnlyEzFoodie ezFoodie, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
