package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeleteOrderCommandIntegrationTest.showNoOrder;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OrderBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

class MarkOrderCommandTest {

    private Order testOrder;

    @BeforeEach
    public void setUp() {
        testOrder = new OrderBuilder().withLabel("test label").build();
    }

    @Test
    public void execute_validIndexMarkOrder_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();

        assertFalse(testOrder.getIsComplete());
        CommandResult commandResult = new MarkOrderCommand(targetIndex).execute(modelStub);

        assertTrue(testOrder.getIsComplete());
        assertEquals(String.format(MarkOrderCommand.MESSAGE_MARK_ORDER_SUCCESS, testOrder),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();
        MarkOrderCommand markOrderCommand = new MarkOrderCommand(outOfBoundIndex);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, (
        ) -> markOrderCommand.execute(modelStub));

    }

    @Test
    public void execute_markAlreadyCompleted_notification() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();

        // mark the order twice, verify that notification is given.
        new MarkOrderCommand(targetIndex).execute(modelStub);
        CommandResult commandResult = new MarkOrderCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(MarkOrderCommand.MESSAGE_ORDER_ALREADY_MARKED, testOrder),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_filterIncompleteOrders_removesOrder() throws Exception {
        OrderBook orderBookWithIncompleteOrder = new OrderBook();
        orderBookWithIncompleteOrder.addOrder(testOrder);

        Model model = new ModelManager(getTypicalAddressBook(),
                getTypicalTaskBook(), orderBookWithIncompleteOrder, new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(),
                getTypicalTaskBook(), orderBookWithIncompleteOrder, new UserPrefs());

        model.updateFilteredOrderList(order -> !order.getIsComplete());
        showNoOrder(expectedModel);

        CommandResult commandResult = new MarkOrderCommand(INDEX_FIRST_ORDER).execute(model);
        assertTrue(testOrder.getIsComplete());
        assertEquals(String.format(MarkOrderCommand.MESSAGE_MARK_ORDER_SUCCESS, testOrder),
                commandResult.getFeedbackToUser());
    }

    private class ModelStubWithOneOrder extends ModelStub {
        private final ObservableList<Order> listWithOneOrder = FXCollections.observableArrayList(testOrder);

        @Override
        public boolean markOrder(Order order) {
            return listWithOneOrder.get(0).markCompleted();
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            return listWithOneOrder;
        }
    }

}
