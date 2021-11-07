package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortOrdersCommandTest.DATE_DESC;
import static seedu.address.model.Model.PREDICATE_SHOW_INCOMPLETE_ORDERS;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OrderBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ShowIncompleteOrders}.
 */
class ShowIncompleteOrdersTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        //start with an empty order book
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new OrderBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new OrderBook(), new UserPrefs());
    }

    @Test
    public void execute_noOrders_noIncompleteOrderFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        ShowIncompleteOrders command = new ShowIncompleteOrders();
        expectedModel.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_oneIncompleteOrder_success() {
        Order incompleteOrder = new OrderBuilder().withIsComplete(false).build();
        model.addOrder(incompleteOrder);
        expectedModel.addOrder(incompleteOrder);

        ShowIncompleteOrders command = new ShowIncompleteOrders();
        expectedModel.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneCompletedOrder_noIncompleteOrderFound() {
        Order completeOrder = new OrderBuilder().withIsComplete(true).build();
        model.addOrder(completeOrder);
        expectedModel.addOrder(completeOrder);

        ShowIncompleteOrders command = new ShowIncompleteOrders();
        expectedModel.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }


    @Test
    public void execute_typicalOrders_success() {
        OrderBook typicalOrderBook = getTypicalOrderBook();
        model.setOrderBook(typicalOrderBook);
        expectedModel.setOrderBook(typicalOrderBook);

        ShowIncompleteOrders command = new ShowIncompleteOrders();
        expectedModel.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 2);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortedOrders_remainsSorted() {
        OrderBook typicalOrderBook = getTypicalOrderBook();
        model.setOrderBook(typicalOrderBook);
        expectedModel.setOrderBook(typicalOrderBook);

        model.sortOrderList(DATE_DESC);
        expectedModel.sortOrderList(DATE_DESC);
        expectedModel.updateFilteredOrderList(PREDICATE_SHOW_INCOMPLETE_ORDERS);

        ShowIncompleteOrders command = new ShowIncompleteOrders();
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 2);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

}

