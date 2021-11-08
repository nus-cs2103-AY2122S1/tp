package lingogo.logic;

import static lingogo.commons.core.Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX;
import static lingogo.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static lingogo.logic.commands.CommandTestUtil.CHINESE_PHRASE_DESC_HELLO;
import static lingogo.logic.commands.CommandTestUtil.ENGLISH_PHRASE_DESC_HELLO;
import static lingogo.logic.commands.CommandTestUtil.LANGUAGE_TYPE_DESC_CHINESE;
import static lingogo.testutil.Assert.assertThrows;
import static lingogo.testutil.TypicalFlashcards.HELLO_CHINESE_FLASHCARD;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lingogo.logic.commands.AddCommand;
import lingogo.logic.commands.CommandResult;
import lingogo.logic.commands.ListCommand;
import lingogo.logic.commands.exceptions.CommandException;
import lingogo.logic.parser.exceptions.ParseException;
import lingogo.model.Model;
import lingogo.model.ModelManager;
import lingogo.model.ReadOnlyFlashcardApp;
import lingogo.model.UserPrefs;
import lingogo.model.flashcard.Flashcard;
import lingogo.storage.JsonFlashcardAppStorage;
import lingogo.storage.JsonUserPrefsStorage;
import lingogo.storage.StorageManager;
import lingogo.testutil.FlashcardBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonFlashcardAppStorage flashcardAppStorage =
                new JsonFlashcardAppStorage(temporaryFolder.resolve("flashcardApp.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(flashcardAppStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFlashcardAppIoExceptionThrowingStub
        JsonFlashcardAppStorage flashcardAppStorage =
                new JsonFlashcardAppIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionFlashcardApp.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(flashcardAppStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + LANGUAGE_TYPE_DESC_CHINESE
                + ENGLISH_PHRASE_DESC_HELLO + CHINESE_PHRASE_DESC_HELLO;
        Flashcard expectedFlashcard = new FlashcardBuilder(HELLO_CHINESE_FLASHCARD).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addFlashcard(expectedFlashcard);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredFlashcardList().remove(0));
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
        Model expectedModel = new ModelManager(model.getFlashcardApp(), new UserPrefs());
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
    private static class JsonFlashcardAppIoExceptionThrowingStub extends JsonFlashcardAppStorage {
        private JsonFlashcardAppIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFlashcardApp(ReadOnlyFlashcardApp flashcardApp, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
