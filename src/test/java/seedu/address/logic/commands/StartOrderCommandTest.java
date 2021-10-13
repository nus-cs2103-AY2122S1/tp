package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.Order;
import seedu.address.model.item.Item;

public class StartOrderCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_modelHasNoUnclosedOrder_modelSetsOrder() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        StartOrderCommand command = new StartOrderCommand();
        command.execute(modelStub);

        assertTrue(modelStub.hasUnclosedOrder());
    }


    /**
     * A model stub that has only order related functionality.
     */
    private class ModelStubWithOrder extends ModelStub {
        private Optional<Order> optionalOrder;

        ModelStubWithOrder() {
            optionalOrder = Optional.empty();
        }

        @Override
        public void setOrder(Order order) {
            StartOrderCommandTest.ModelStubWithOrder.this.optionalOrder = Optional.of(order);
        }

        @Override
        public boolean hasUnclosedOrder() {
            return optionalOrder.isPresent();
        }

        @Override
        public void addToOrder(Item item) {
            assert hasUnclosedOrder();
            optionalOrder.get().addItem(item);;
        }
    }
}
