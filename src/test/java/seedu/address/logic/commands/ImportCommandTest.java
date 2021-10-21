package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ImportCommandTest {

    private final String NONEXISTENT_FILE = "random.json";
    private final String INCORRECT_DATA_FORMAT_FILE = "incorrect.json";
    private final String INCORRECT_FILE_EXTENSION = "import.txt";
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_correctFile_success() throws CommandException {
        CommandResult commandResult = new ImportCommand("import.json").execute(model);

        assertEquals(commandResult.getFeedbackToUser(), String.format(ImportCommand.MESSAGE_SUCCESS, 2));
    }

    @Test
    public void execute_fileNotFound_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(NONEXISTENT_FILE);

        assertThrows(CommandException.class, ImportCommand.MESSAGE_FILE_NOT_FOUND, () -> importCommand.execute(model));
    }

    @Test
    public void execute_incorrectFormat_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(INCORRECT_DATA_FORMAT_FILE);

        assertThrows(CommandException.class, ImportCommand.MESSAGE_INCORRECT_FORMAT,
                () -> importCommand.execute(model));
    }

    @Test
    public void execute_incorrectFileExtension_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(INCORRECT_FILE_EXTENSION);

        assertThrows(CommandException.class, ImportCommand.MESSAGE_INCORRECT_FORMAT,
                () -> importCommand.execute(model));
    }
}
