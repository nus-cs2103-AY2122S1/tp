package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalOrders;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ViewOrderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs());
        expectedModel = new ModelManager(model.getInventory(), new UserPrefs());
    }

    @Test
    public void execute_noUnclosedOrder_failure() {
        assertCommandFailure(new ViewOrderCommand(), model, ViewOrderCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void execute_displayInventoryMode_success() {
        model.setOrder(TypicalOrders.getTypicalOrder());

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredItemList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ViewOrderCommand(), model, ViewOrderCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(model.getFilteredItemList(), TypicalOrders.getTypicalOrder().getOrderItems());
    }

    @Test
    public void execute_displayOrderMode_showsSameList() {
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.updateFilteredItemList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.setOrder(TypicalOrders.getTypicalOrder());
        expectedModel.updateFilteredItemList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        assertCommandSuccess(new ViewOrderCommand(), model, ViewOrderCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(model.getFilteredItemList(), TypicalOrders.getTypicalOrder().getOrderItems());
    }
}
