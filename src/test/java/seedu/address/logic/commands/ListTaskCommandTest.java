package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests for interaction with the model, and unit tests for ListTaskCommand.
 * Modeled after ListCommandTest
 */
class ListTaskCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), model.getOrderBook(), new UserPrefs());

    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListTaskCommand(), model, ListTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
