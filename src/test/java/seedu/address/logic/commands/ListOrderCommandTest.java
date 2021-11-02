package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortOrdersCommandTest.DATE_DESC;
import static seedu.address.model.Model.PREDICATE_SHOW_INCOMPLETE_ORDERS;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests for interaction with the model, and unit tests for ListOrderCommand.
 * Modeled after ListCommandTest
 */
class ListOrderCommandTest {

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
    public void execute_listNotFilteredNotSorted_showsSameList() {
        assertCommandSuccess(new ListOrderCommand(), model, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listFilteredNotSorted_showsEverything() {
        model.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        assertCommandSuccess(new ListOrderCommand(), model, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listNotFilteredSorted_showsEverything() {
        model.sortOrderList(DATE_DESC);
        assertCommandSuccess(new ListOrderCommand(), model, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listFilteredSorted_showsEverything() {
        model.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        model.sortOrderList(DATE_DESC);
        assertCommandSuccess(new ListOrderCommand(), model, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
