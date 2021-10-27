package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;

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
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(DISPLAY_INVENTORY), model,
                ListCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListCommand(DISPLAY_INVENTORY), model,
                ListCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }

    @Test
    public void execute_displayOrderMode_setDisplayInventoryMode() {
        model.setOrder(new Order());
        model.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);

        expectedModel.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        expectedModel.setOrder(new Order());
        assertCommandSuccess(new ListCommand(DISPLAY_INVENTORY), model,
                ListCommand.MESSAGE_SUCCESS_INVENTORY, expectedModel);
    }
}
