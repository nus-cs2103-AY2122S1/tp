package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListTransactionCommand.MESSAGE_TXN_NOT_FOUND;
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
import seedu.address.testutil.TypicalTransactions;

public class ListTransactionCommandTest {

    private Model model;
    private Model expectedModel;

    private String addMessage;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(TypicalTransactions.getTypicalTransactionList()), new BookKeeping());
        expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(TypicalTransactions.getTypicalTransactionList()), new BookKeeping());
        model.setOrder(TypicalOrders.getTypicalOrder());

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());

        String cost = String.format("%.2f", model.getBookKeeping().getCost());
        String revenue = String.format("%.2f", model.getBookKeeping().getRevenue());
        String profit = String.format("%.2f", model.getBookKeeping().getProfit());

        addMessage = "Total costs: " + cost + ", Total revenue: "
                + revenue + ", Total profit: " + profit;
    }

    @Test
    public void execute_displayNotInTransactionMode_displayTransactions() {
        model.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListTransactionCommand(""), model,
                ListTransactionCommand.MESSAGE_SUCCESS_ALL + "\n" + addMessage, expectedModel);

        assertEquals(model.getTransactions(), TypicalTransactions.getTypicalTransactionList());
    }

    @Test
    public void execute_displayTransactionMode_showsSameList() {
        model.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.updateFilteredDisplayList(DISPLAY_TRANSACTION_LIST, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListTransactionCommand(""), model,
                ListTransactionCommand.MESSAGE_SUCCESS_ALL + "\n" + addMessage, expectedModel);

        assertEquals(model.getTransactions(), TypicalTransactions.getTypicalTransactionList());
    }

    @Test
    public void execute_displaySpecificTransaction() {

        String id = model.getTransactions().getTransactionRecordList().get(0).getId();

        Double totalCost = model.openTransaction(id);

        String addMessage = ", total cost: " + String.format("%.2f", totalCost);

        expectedModel.openTransaction(id);

        String expectedMessage = String.format(ListTransactionCommand.MESSAGE_SUCCESS, id) + addMessage;

        assertCommandSuccess(new ListTransactionCommand(id), model,
                expectedMessage, expectedModel);

        Command noIdCommand = new ListTransactionCommand("a" + id.substring(1));
        String expectedMessageFail = String.format(MESSAGE_TXN_NOT_FOUND, "a" + id.substring(1));

        assertCommandFailure(noIdCommand, model, expectedMessageFail);
    }

}
