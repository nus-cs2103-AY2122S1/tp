package seedu.tracker.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.tracker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.tracker.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.MC_DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.TITLE_DESC_CS2103T;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS2103T;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import seedu.tracker.logic.commands.AddCommand;
import seedu.tracker.logic.commands.CommandResult;
import seedu.tracker.logic.commands.ListCommand;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.module.McProgress;
import seedu.tracker.model.module.Module;
import seedu.tracker.storage.JsonModuleTrackerStorage;
import seedu.tracker.storage.JsonUserInfoStorage;
import seedu.tracker.storage.JsonUserPrefsStorage;
import seedu.tracker.storage.StorageManager;
import seedu.tracker.testutil.ModuleBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonModuleTrackerStorage moduleTrackerStorage =
                new JsonModuleTrackerStorage(temporaryFolder.resolve("moduleTracker.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonUserInfoStorage userInfoStorage = new JsonUserInfoStorage(temporaryFolder.resolve("userInfo.json"));
        StorageManager storage = new StorageManager(moduleTrackerStorage, userPrefsStorage, userInfoStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonModuleTrackerStorage moduleTrackerStorage =
                new JsonModuleTrackerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionModuleTracker.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonUserInfoStorage userInfoStorage =
                new JsonUserInfoStorage(temporaryFolder.resolve("ioExceptionUserInfo.json"));
        StorageManager storage = new StorageManager(moduleTrackerStorage, userPrefsStorage, userInfoStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + CODE_DESC_CS2103T + TITLE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T
                + MC_DESC_CS2103T;
        Module expectedModule = new ModuleBuilder(CS2103T).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addModule(expectedModule);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredModuleList().remove(0));
    }

    @Test
    public void getMcProgressList_returnsDefaultList() {
        ObservableList<McProgress> mcProgress = logic.getMcProgressList();
        boolean isValid = true;
        for (McProgress progress : mcProgress) {
            isValid = isValid && progress.getCompleted().value == 0;
        }
        assertTrue(isValid);
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
        Model expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs(), new UserInfo());
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
    private static class JsonModuleTrackerIoExceptionThrowingStub extends JsonModuleTrackerStorage {
        private JsonModuleTrackerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
