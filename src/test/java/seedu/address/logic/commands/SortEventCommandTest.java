package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortEventCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortOneNotTheOther_notEquals() {
        // TODO: Once addEvents implemented, can update the test cases here
        SortEventCommand command = new SortEventCommand();
        command.execute(model);
        assertFalse(model.getAddressBook().getEventList().equals(expectedModel.getAddressBook().getEventList()));
    }

    @Test
    public void execute_sortBoth_equals() {
        // TODO: Once addEvents implemented, can update the test cases here
        SortEventCommand command = new SortEventCommand();
        command.execute(model);
        command.execute(expectedModel);
        assertTrue(model.getAddressBook().getEventList().equals(expectedModel.getAddressBook().getEventList()));
    }
}
