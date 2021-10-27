package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.display.DisplayMode;
import seedu.address.model.order.Order;

public class EndAndTransactOrderCommandTest {

    private Model modelWithoutOrder = new ModelManager(getTypicalInventory(), new UserPrefs());
    private Model modelWithOrder = getModelWithOrderedDonut();

    /**
     * Returns a model with 5 donuts in its unclosed order
     */
    private Model getModelWithOrderedDonut() {
        Model model = new ModelManager(getTypicalInventory(), new UserPrefs());
        model.addItem(DONUT.updateCount(5));
        model.setOrder(new Order());
        model.addToOrder(DONUT.updateCount(1));

        return model;
    }

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_noUnclosedOrder_failure() {
        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        assertCommandFailure(command, modelWithoutOrder, EndAndTransactOrderCommand.MESSAGE_NO_UNCLOSED_ORDER);
    }

    @Test
    public void execute_normalTransaction_itemRemoved() {
        String expectedMessage = EndAndTransactOrderCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs());
        expectedModel.addItem(DONUT.updateCount(4));

        assertCommandSuccess(new EndAndTransactOrderCommand(), modelWithOrder, expectedMessage, expectedModel);
    }

    @Test
    public void execute_insufficientItemInInventory_failure() {
        // TODO: Behaviour not supported yet. Change and update accordingly
    }

    @Test
    public void execute_orderIsEmpty_failure() {
        // TODO: Behaviour not supported yet. Change and update accordingly
    }

    @Test
    public void execute_displayingOrder_itemRemovedAndDisplayInventory() {
        modelWithOrder.updateFilteredDisplayList(DisplayMode.DISPLAY_OPEN_ORDER, Model.PREDICATE_SHOW_ALL_ITEMS);

        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        String expectedMessage = EndAndTransactOrderCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs());
        expectedModel.addItem(DONUT.updateCount(4));

        assertCommandSuccess(new EndAndTransactOrderCommand(), modelWithOrder, expectedMessage, expectedModel);
    }
}