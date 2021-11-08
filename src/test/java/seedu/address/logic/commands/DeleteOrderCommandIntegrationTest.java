package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.address.logic.commands.SortOrdersCommandTest.AMOUNT_ASC;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalOrders.SALESORDER4;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.task.Task;


/**
 * Contains integration tests (interaction with the Model) for {@code DeleteOrderCommand}.
 */
public class DeleteOrderCommandIntegrationTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), model.getOrderBook(), new UserPrefs());
    }

    @Test
    public void execute_noLinkedOrders_success() throws Exception {
        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        DeleteOrderCommand deleteCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        expectedModel.deleteOrder(orderToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_linkedOrders_ordersDeleted() throws Exception {
        Order orderToDelete = model.getFilteredOrderList().get(INDEX_SECOND_ORDER.getZeroBased());
        DeleteOrderCommand deleteCommand = new DeleteOrderCommand(INDEX_SECOND_ORDER);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        expectedModel.deleteOrder(orderToDelete);

        //delete linked tasks
        Task linkedTask1 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Task linkedTask2 = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        expectedModel.deleteTask(linkedTask1);
        expectedModel.deleteTask(linkedTask2);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        DeleteOrderCommand deleteCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        expectedModel.deleteOrder(orderToDelete);
        showNoOrder(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Index outOfBoundIndex = INDEX_SECOND_TASK;

        // ensures that outOfBoundIndex is still in bounds of order book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getTaskList().size());

        DeleteOrderCommand deleteCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no order.
     */
    public static void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }

    @Test
    public void execute_sortedOrders_remainsSorted() {
        Order toDelete = SALESORDER4;
        DeleteOrderCommand command = new DeleteOrderCommand(Index.fromOneBased(5));

        model.sortOrderList(AMOUNT_ASC);

        expectedModel.deleteOrder(toDelete);
        expectedModel.sortOrderList(AMOUNT_ASC);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, toDelete);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
