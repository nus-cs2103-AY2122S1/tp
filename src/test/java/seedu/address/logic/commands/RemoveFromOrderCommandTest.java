package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Order;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyUserPrefs;
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
    private class ModelStubWithOrder implements Model {
        private Optional<Order> optionalOrder;

        ModelStubWithOrder() {
            optionalOrder = Optional.empty();
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInventoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInventoryFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInventory(ReadOnlyInventory newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInventory getInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(Item target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(Item target, Item editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sorts the item list using the given {@code comparator}.
         */
        @Override
        public void sortItems(Comparator<Item> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Item getItemWithName(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Item getItemWithId(String id) {
            throw new AssertionError("This method should not be called.");
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

        @Override
        public void transactAndClearOrder() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
