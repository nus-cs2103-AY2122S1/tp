package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.SALESORDER1;
import static seedu.address.testutil.TypicalOrders.SALESORDER2;
import static seedu.address.testutil.TypicalOrders.SALESORDER3;
import static seedu.address.testutil.TypicalOrders.SALESORDER4;
import static seedu.address.testutil.TypicalOrders.SALESORDER5;
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
import seedu.address.testutil.TaskBuilder;

public class SortOrdersCommandTest {
    public static final SortDescriptor DATE_ASC = new SortDescriptorBuilder().onDateField().inAscOrder().build();
    public static final SortDescriptor DATE_DESC = new SortDescriptorBuilder().onDateField().inDescOrder().build();
    public static final SortDescriptor AMOUNT_ASC = new SortDescriptorBuilder().onAmountField().inAscOrder().build();
    public static final SortDescriptor AMOUNT_DESC = new SortDescriptorBuilder().onAmountField().inDescOrder().build();

    public static final SortOrdersCommand SORT_BY_DATE_ASC = new SortOrdersCommand(DATE_ASC);
    public static final SortOrdersCommand SORT_BY_DATE_DESC = new SortOrdersCommand(DATE_DESC);
    public static final SortOrdersCommand SORT_BY_AMOUNT_ASC = new SortOrdersCommand(AMOUNT_ASC);
    public static final SortOrdersCommand SORT_BY_AMOUNT_DESC = new SortOrdersCommand(AMOUNT_DESC);

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
    }

    @Test
    public void execute_filteredList_sortsFilteredList() {
        String expectedMessage = AMOUNT_ASC.generateSuccessMessage();

        OrderBook sortedOrderBook = new OrderBook();
        List<Order> sortedOrdersAmtAsc =
                new ArrayList<>(Arrays.asList(SALESORDER5, SALESORDER1, SALESORDER3, SALESORDER2, SALESORDER4));
        sortedOrderBook.setOrders(sortedOrdersAmtAsc);
        expectedModel.setOrderBook(sortedOrderBook);

        model.updateFilteredOrderList(Model.PREDICATE_SHOW_COMPLETED_ORDERS);
        expectedModel.updateFilteredOrderList(Model.PREDICATE_SHOW_COMPLETED_ORDERS);

        assertCommandSuccess(SORT_BY_AMOUNT_ASC, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unsortedList_sortsList() {
        String expectedMessage = AMOUNT_DESC.generateSuccessMessage();

        List<Order> sortedOrdersAmtDesc =
                new ArrayList<>(Arrays.asList(SALESORDER2, SALESORDER4, SALESORDER3, SALESORDER1, SALESORDER5));
        OrderBook sortedOrderBook = new OrderBook();
        sortedOrderBook.setOrders(sortedOrdersAmtDesc);
        expectedModel.setOrderBook(sortedOrderBook);

        assertCommandSuccess(SORT_BY_AMOUNT_DESC, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyList_success() {
        String expectedMessage = AMOUNT_DESC.generateSuccessMessage();
        model.setOrderBook(new OrderBook());
        expectedModel.setOrderBook(new OrderBook());
        assertCommandSuccess(SORT_BY_AMOUNT_DESC, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(SORT_BY_DATE_ASC.equals(SORT_BY_DATE_ASC));

        // same values -> returns true
        SortOrdersCommand sortByDateAscCommandCopy = new SortOrdersCommand(DATE_ASC);
        assertTrue(SORT_BY_DATE_ASC.equals(sortByDateAscCommandCopy));

        // different types -> returns false
        assertFalse(SORT_BY_DATE_ASC.equals(1));

        // null -> returns false
        assertFalse(SORT_BY_DATE_ASC.equals(null));

        // different ordering -> returns false
        assertFalse(SORT_BY_DATE_ASC.equals(SORT_BY_AMOUNT_DESC));
    }

    @Test
    public void sortDescriptorEquals() {
        final SortDescriptor dateDescCopy = new SortDescriptorBuilder().onDateField().inDescOrder().build();;

        // same object is equal
        assertTrue(DATE_DESC.equals(DATE_DESC));

        // same fields is equal
        assertTrue(DATE_DESC.equals(dateDescCopy));

        // not same fields is not equal
        assertFalse(DATE_DESC.equals(AMOUNT_ASC));

        // null is not equal
        assertFalse(DATE_DESC.equals(null));

        // other objects are not equal
        assertFalse(DATE_DESC.equals(new TaskBuilder().build()));
    }
}
