package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.SALESORDER1;
import static seedu.address.testutil.TypicalOrders.SALESORDER2;
import static seedu.address.testutil.TypicalOrders.SALESORDER3;
import static seedu.address.testutil.TypicalOrders.SALESORDER4;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OrderBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.testutil.SortDescriptorBuilder;

class SortOrdersCommandTest {
    private Model model;
    private Model expectedModel;

    private final SortDescriptor dateAsc = new SortDescriptorBuilder().onDateField().inAscendingOrder().build();
    private final SortDescriptor dateDesc = new SortDescriptorBuilder().onDateField().inDescendingOrder().build();
    private final SortDescriptor amountAsc = new SortDescriptorBuilder().onAmountField().inAscendingOrder().build();
    private final SortDescriptor amountDesc = new SortDescriptorBuilder().onAmountField().inDescendingOrder().build();

    private final SortOrdersCommand sortByDateAsc = new SortOrdersCommand(dateAsc);
    private final SortOrdersCommand sortByDateDesc = new SortOrdersCommand(dateDesc);
    private final SortOrdersCommand sortByAmountAsc = new SortOrdersCommand(amountAsc);
    private final SortOrdersCommand sortByAmountDesc = new SortOrdersCommand(amountDesc);

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
    }

    @Test
    public void execute_filteredList_sortsFilteredList() {
        String expectedMessage = amountAsc.generateSuccessMessage();

        OrderBook sortedOrderBook = new OrderBook();
        List<Order> sortedOrdersAmtAsc =
                new ArrayList<>(Arrays.asList(SALESORDER1, SALESORDER3, SALESORDER2, SALESORDER4));
        sortedOrderBook.setOrders(sortedOrdersAmtAsc);
        expectedModel.setOrderBook(sortedOrderBook);

        model.updateFilteredOrderList(Model.PREDICATE_SHOW_COMPLETED_ORDERS);
        expectedModel.updateFilteredOrderList(Model.PREDICATE_SHOW_COMPLETED_ORDERS);

        assertCommandSuccess(sortByAmountAsc, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unsortedList_sortsList() {
        String expectedMessage = amountDesc.generateSuccessMessage();

        List<Order> sortedOrdersAmtDesc =
                new ArrayList<>(Arrays.asList(SALESORDER2, SALESORDER4, SALESORDER3, SALESORDER1));
        OrderBook sortedOrderBook = new OrderBook();
        sortedOrderBook.setOrders(sortedOrdersAmtDesc);
        expectedModel.setOrderBook(sortedOrderBook);

        assertCommandSuccess(sortByAmountDesc, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyList_success() {
        String expectedMessage = amountDesc.generateSuccessMessage();
        model.setOrderBook(new OrderBook());
        expectedModel.setOrderBook(new OrderBook());
        assertCommandSuccess(sortByAmountDesc, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(sortByDateAsc.equals(sortByDateAsc));

        // same values -> returns true
        SortOrdersCommand sortByDateAscCommandCopy = new SortOrdersCommand(dateAsc);
        assertTrue(sortByDateAsc.equals(sortByDateAscCommandCopy));

        // different types -> returns false
        assertFalse(sortByDateAsc.equals(1));

        // null -> returns false
        assertFalse(sortByDateAsc.equals(null));

        // different ordering -> returns false
        assertFalse(sortByDateAsc.equals(sortByAmountDesc));

    }
}
