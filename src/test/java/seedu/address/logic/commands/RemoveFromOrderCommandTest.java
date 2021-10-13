package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.Order;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

public class RemoveFromOrderCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_normalRemoval_itemRemoved() throws CommandException {
        ModelStubWithOrder modelStub = new ModelStubWithOrder();
        modelStub.setOrder(new Order());
        Item toRemove = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
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
        Item item = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Item toRemove = new Item(new Name("milk"), UUID.randomUUID().toString(), 12, new HashSet<>());
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
        Item item = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Item toRemove = new Item(new Name(StringUtil.generateRandomString()),
                "A0123", 12, new HashSet<>());
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
        Item item = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Item toRemove = new Item(new Name("banana"), "B0123", 10, new HashSet<>());
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
