package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(0, 0, null, null),
                model, ClearCommand.MESSAGE_CLEAR_PERSON_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(0, 0, null, null),
                model, ClearCommand.MESSAGE_CLEAR_PERSON_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ClearCommand clearCommand = new ClearCommand(0, 0, null, null);
        // same object -> returns true
        assertTrue(clearCommand.equals(clearCommand));

        // same values -> returns true
        ClearCommand clearCommandcopy = new ClearCommand(0, 0, null, null);
        assertTrue(clearCommand.equals(clearCommandcopy));

        // different types -> returns false
        assertFalse(clearCommand.equals(1));

        // null -> returns false
        assertFalse(clearCommand.equals(null));
    }
}
