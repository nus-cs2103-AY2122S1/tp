package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTION_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeping;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalOrders;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListInventoryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs(), new TransactionList(), new BookKeeping());
        expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
    }

    @Test
    public void executeInventory_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListInventoryCommand(DISPLAY_INVENTORY), model,
                ListInventoryCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void executeInventory_listIsFiltered_showsEverything() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListInventoryCommand(DISPLAY_INVENTORY), model,
                ListInventoryCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void executeInventory_displayingOrder_setDisplayInventoryMode() {
        model.setOrder(new Order());
        model.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        expectedModel.setOrder(new Order());
        assertCommandSuccess(new ListInventoryCommand(DISPLAY_INVENTORY), model,
                ListInventoryCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void executeOrder_noUnclosedOrder_failure() {
        assertCommandFailure(new ListInventoryCommand(DISPLAY_OPEN_ORDER), model,
                ListInventoryCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void executeOrder_displayInInventoryMode_success() {
        model.setOrder(TypicalOrders.getTypicalOrder());

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListInventoryCommand(DISPLAY_OPEN_ORDER), model,
                ListInventoryCommand.MESSAGE_SUCCESS_ORDER, expectedModel);
        assertEquals(model.getFilteredDisplayList(), TypicalOrders.getTypicalOrder().getOrderItems());
    }

    @Test
    public void executeOrder_displayInOrderMode_showsSameList() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ListInventoryCommand(DISPLAY_OPEN_ORDER), model,
                ListInventoryCommand.MESSAGE_SUCCESS_ORDER, expectedModel);
        assertEquals(model.getFilteredDisplayList(),
                TypicalOrders.getTypicalOrder().getOrderItems());
    }

    @Test void constructor_invalidDisplayMode_throwAssertion() {
        assertThrows(AssertionError.class, () -> new ListInventoryCommand(DISPLAY_TRANSACTION_LIST));
    }
}
