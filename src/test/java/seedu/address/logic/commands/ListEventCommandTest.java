package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventDateTimePredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListEventCommand.
 */
public class ListEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListEventCommand(), model, ListEventCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.updateFilteredEventList(new EventDateTimePredicate(Arrays.asList("1-1-1")));
        assertCommandSuccess(new ListEventCommand(), model, ListEventCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
