package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.order.Order;

public class RemoveFromOrderCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_normalRemoval_itemRemoved() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        modelStub.setOrder(new Order());
        Item toRemove = new Item(new Name("milk"), 120123, 10, new HashSet<>(), 1.1, 2.2);
        modelStub.addToOrder(toRemove);
        Order expectedOrder = new Order();

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemove);
        command.execute(modelStub);

        assertEquals(expectedOrder, modelStub.optionalOrder.get());
    }

    @Test
    public void execute_removeWithOnlyNameMatch_itemRemoved() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        modelStub.setOrder(new Order());
        Item item = new Item(new Name("milk"), 120123, 10, new HashSet<>(), 1.1, 2.2);
        Item toRemove = new Item(new Name("milk"), 431521, 12, new HashSet<>(), 1.1, 2.2);
        modelStub.addToOrder(item);
        Order expectedOrder = new Order();

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemove);
        command.execute(modelStub);

        assertEquals(expectedOrder, modelStub.optionalOrder.get());
    }

    @Test
    public void execute_removeWithOnlyIdMatch_itemRemoved() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        modelStub.setOrder(new Order());
        Item item = new Item(new Name("milk"), 120123, 10, new HashSet<>(), 1.1, 2.2);
        Item toRemove = new Item(new Name(StringUtil.generateRandomString()),
                120123, 12, new HashSet<>(), 1.1, 2.2);
        modelStub.addToOrder(item);
        Order expectedOrder = new Order();

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemove);
        command.execute(modelStub);

        assertEquals(expectedOrder, modelStub.optionalOrder.get());
    }

    @Test
    public void execute_removeNonExistentialItem_orderUnchanged() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        modelStub.setOrder(new Order());
        Item item = new Item(new Name("milk"), 120125, 10, new HashSet<>(), 1.1, 2.2);
        Item toRemove = new Item(new Name("banana"), 120123, 10, new HashSet<>(), 1.1, 2.2);
        modelStub.addToOrder(item);
        Order expectedOrder = new Order();
        expectedOrder.addItem(item);

        RemoveFromOrderCommand command = new RemoveFromOrderCommand(toRemove);
        command.execute(modelStub);

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
            RemoveFromOrderCommandTest.ModelStubWithOrder.this.optionalOrder = Optional.of(order);
        }

        @Override
        public boolean hasUnclosedOrder() {
            return optionalOrder.isPresent();
        }

        @Override
        public void addToOrder(Item item) {
            assert hasUnclosedOrder();
            optionalOrder.get().addItem(item);
        }

        @Override
        public void removeFromOrder(Item item) {
            assert hasUnclosedOrder();
            optionalOrder.get().removeItem(item);
        }

    }
}
