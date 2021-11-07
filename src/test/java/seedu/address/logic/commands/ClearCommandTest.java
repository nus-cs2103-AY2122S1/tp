package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        ClearCommand clearCommand = new ClearCommand();
        String expectedMessage = ClearCommand.MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateFilteredPersonList(unused -> false);
        expectedModel.displayFilteredTaskList(unused -> false);
        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ClearCommand clearCommand = new ClearCommand();

        // same object -> returns true
        assertEquals(clearCommand, clearCommand);

        // different types -> returns false
        Command otherCommand = new DeleteCommand();
        assertNotEquals(otherCommand, clearCommand);

        // null -> returns false
        assertNotEquals(null, clearCommand);
    }
}
