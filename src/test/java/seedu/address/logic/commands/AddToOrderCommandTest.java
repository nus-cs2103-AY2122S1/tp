package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.Order;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

public class AddToOrderCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_modelHasNoUnclosedOrder_giveNoUnclosedOrderMessage() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        Item tobeAdded = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        AddToOrderCommand command = new AddToOrderCommand(tobeAdded);
        CommandResult expectedResult = new CommandResult("Please use `sorder` to enter ordering mode first.");

        assertEquals(command.execute(modelStub), expectedResult);
    }

    @Test
    public void execute_modelHasOrder_itemAddedToOrder() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        Item tobeAdded = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Order expectedOrder = new Order();
        modelStub.setOrder(new Order());

        AddToOrderCommand command = new AddToOrderCommand(tobeAdded);
        CommandResult result = command.execute(modelStub);

        expectedOrder.addItem(tobeAdded);

        assertEquals(expectedOrder, modelStub.optionalOrder.get());
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
            this.optionalOrder = Optional.of(order);
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
