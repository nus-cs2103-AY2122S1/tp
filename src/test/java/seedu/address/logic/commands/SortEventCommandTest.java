package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.CODE_FOR_SANITY;
import static seedu.address.testutil.TypicalEvents.MARATHON_HAS_TIME;
import static seedu.address.testutil.TypicalEvents.MARATHON_NO_TIME;
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
    public void execute_addEventsThenSortOneNotTheOther_notEquals() {
        SortEventCommand command = new SortEventCommand();
        model.addEvent(CODE_FOR_SANITY);
        model.addEvent(MARATHON_HAS_TIME);
        model.addEvent(MARATHON_NO_TIME);
        expectedModel.addEvent(CODE_FOR_SANITY);
        expectedModel.addEvent(MARATHON_HAS_TIME);
        expectedModel.addEvent(MARATHON_NO_TIME);
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

    @Test
    public void execute_addEventsThenSortBoth_equals() {
        SortEventCommand command = new SortEventCommand();
        model.addEvent(CODE_FOR_SANITY);
        model.addEvent(MARATHON_HAS_TIME);
        model.addEvent(MARATHON_NO_TIME);
        expectedModel.addEvent(CODE_FOR_SANITY);
        expectedModel.addEvent(MARATHON_HAS_TIME);
        expectedModel.addEvent(MARATHON_NO_TIME);
        command.execute(model);
        command.execute(expectedModel);
        assertTrue(model.getAddressBook().getEventList().equals(expectedModel.getAddressBook().getEventList()));
    }

    @Test
    public void equals() {
        SortEventCommand command = new SortEventCommand();
        assertTrue(new SortEventCommand().equals(command));
    }
}
