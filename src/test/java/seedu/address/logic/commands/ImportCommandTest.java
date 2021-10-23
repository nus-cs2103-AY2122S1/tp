package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ImportCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_correctFile_success() throws CommandException {
        String properFile = "import.json";
        CommandResult commandResult = new ImportCommand(properFile).execute(model);

        assertEquals(commandResult.getFeedbackToUser(), String.format(ImportCommand.MESSAGE_SUCCESS, 2));
    }

    @Test
    public void execute_fileNotFound_throwsCommandException() {
        String nonexistentFile = "random.json";
        ImportCommand importCommand = new ImportCommand(nonexistentFile);

        assertThrows(CommandException.class, ImportCommand.MESSAGE_FILE_NOT_FOUND, () -> importCommand.execute(model));
    }

    @Test
    public void execute_incorrectFormat_throwsCommandException() {
        String incorrectDataFormatFile = "incorrect.json";
        ImportCommand importCommand = new ImportCommand(incorrectDataFormatFile);

        assertThrows(CommandException.class,
            ImportCommand.MESSAGE_INCORRECT_FORMAT, () -> importCommand.execute(model));
    }

    @Test
    public void execute_incorrectFileExtension_throwsCommandException() {
        String incorrectFileExtension = "import.txt";
        ImportCommand importCommand = new ImportCommand(incorrectFileExtension);

        assertThrows(CommandException.class,
            ImportCommand.MESSAGE_INCORRECT_FORMAT, () -> importCommand.execute(model));
    }
}
