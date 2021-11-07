package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTION_LIST;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeping;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalOrders;

public class ListTransactionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs(), new TransactionList(), new BookKeeping());
        expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
    }

    @Test
    public void execute_displayNotInTransactionMode_displayTransactions() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);

        String cost = String.format("%.2f", model.getBookKeeping().getCost());
        String revenue = String.format("%.2f", model.getBookKeeping().getRevenue());
        String profit = String.format("%.2f", model.getBookKeeping().getProfit());

        String addMessage = "Total costs: " + cost + ", Total revenue: "
                + revenue + ", Total profit: " + profit;

        assertCommandSuccess(new ListTransactionCommand(""), model,
                ListTransactionCommand.MESSAGE_SUCCESS_ALL + "\n" + addMessage, expectedModel);
        // TODO: compare transactions with typical transactions
    }

    @Test
    public void execute_displayTransactionMode_showsSameList() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);

        String cost = String.format("%.2f", model.getBookKeeping().getCost());
        String revenue = String.format("%.2f", model.getBookKeeping().getRevenue());
        String profit = String.format("%.2f", model.getBookKeeping().getProfit());

        String addMessage = "Total costs: " + cost + ", Total revenue: "
                + revenue + ", Total profit: " + profit;

        assertCommandSuccess(new ListTransactionCommand(""), model,
                ListTransactionCommand.MESSAGE_SUCCESS_ALL + "\n" + addMessage, expectedModel);
        // TODO: compare transactions with typical transactions
    }

}
