package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTIONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalOrders;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs());
        expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
    }

    @Test
    public void executeInventory_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(DISPLAY_INVENTORY), model,
                ListCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void executeInventory_listIsFiltered_showsEverything() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListCommand(DISPLAY_INVENTORY), model,
                ListCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void executeInventory_displayingOrder_setDisplayInventoryMode() {
        model.setOrder(new Order());
        model.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        expectedModel.setOrder(new Order());
        assertCommandSuccess(new ListCommand(DISPLAY_INVENTORY), model,
                ListCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void executeOrder_noUnclosedOrder_failure() {
        assertCommandFailure(new ListCommand(DISPLAY_OPEN_ORDER), model,
                ListCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void executeOrder_displayInInventoryMode_success() {
        model.setOrder(TypicalOrders.getTypicalOrder());

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListCommand(DISPLAY_OPEN_ORDER), model,
                ListCommand.MESSAGE_SUCCESS_ORDER, expectedModel);
        assertEquals(model.getFilteredDisplayList(), TypicalOrders.getTypicalOrder().getOrderItems());
    }

    @Test
    public void executeOrder_displayInOrderMode_showsSameList() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListCommand(DISPLAY_OPEN_ORDER), model,
                ListCommand.MESSAGE_SUCCESS_ORDER, expectedModel);
        assertEquals(model.getFilteredDisplayList(),
                TypicalOrders.getTypicalOrder().getOrderItems());
    }

    @Test
    public void executeTransactions_displayInInventory_showsSameList() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_TRANSACTIONS, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListCommand(DISPLAY_TRANSACTIONS), model,
                ListCommand.MESSAGE_SUCCESS_TXNS, expectedModel);
        // TODO: compare transactions with typical transactions
    }

    @Test
    public void executeTransactions_displayTransactionMode_showsSameList() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredDisplayList(DISPLAY_TRANSACTIONS, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_TRANSACTIONS, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListCommand(DISPLAY_TRANSACTIONS), model,
                ListCommand.MESSAGE_SUCCESS_TXNS, expectedModel);
        // TODO: compare transactions with typical transactions
    }
}
