package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.Order;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TransactionRecord;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

public class EndAndTransactOrderCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_normalTransaction_itemRemoved() throws CommandException {
        ModelStubWithOrderAndInventory modelStub = new ModelStubWithOrderAndInventory();

        Item item = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Inventory inventory = new Inventory();
        Order order = new Order();
        inventory.addItem(item);
        order.addItem(item);

        modelStub.setInventory(inventory);
        modelStub.setOrder(order);

        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        command.execute(modelStub);

        Inventory expectedInventory = new Inventory();

        assertEquals(modelStub.inventory, expectedInventory);
    }

    @Test
    public void execute_insufficientTransaction_itemRemoved() throws CommandException {
        // Order item has more quantity than item in inventory, inventory item should be removed
        ModelStubWithOrderAndInventory modelStub = new ModelStubWithOrderAndInventory();

        Item inventoryItem = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Item orderItem = new Item(new Name("milk"), "A0123", 15, new HashSet<>());
        Inventory inventory = new Inventory();
        Order order = new Order();
        inventory.addItem(inventoryItem);
        order.addItem(orderItem);

        modelStub.setInventory(inventory);
        modelStub.setOrder(order);

        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        command.execute(modelStub);

        Inventory expectedInventory = new Inventory();

        assertEquals(modelStub.inventory, expectedInventory);
    }

    @Test
    public void execute_moreQuantityInventory_itemCountDecreased() throws CommandException {
        // Order item has more quantity than item in inventory, inventory item should be removed
        ModelStubWithOrderAndInventory modelStub = new ModelStubWithOrderAndInventory();

        Item inventoryItem = new Item(new Name("milk"), "A0123", 15, new HashSet<>());
        Item orderItem = new Item(new Name("milk"), "A0123", 10, new HashSet<>());
        Inventory inventory = new Inventory();
        Order order = new Order();
        inventory.addItem(inventoryItem);
        order.addItem(orderItem);

        modelStub.setInventory(inventory);
        modelStub.setOrder(order);

        EndAndTransactOrderCommand command = new EndAndTransactOrderCommand();
        command.execute(modelStub);

        Item transactedItem = new Item(new Name("milk"), "A0123", 5, new HashSet<>());
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(transactedItem);

        assertEquals(modelStub.inventory, expectedInventory);
    }

    /**
     * A model stub that has only order related functionality.
     */
    private class ModelStubWithOrderAndInventory implements Model {
        private Optional<Order> optionalOrder;
        private Inventory inventory;

        ModelStubWithOrderAndInventory() {
            optionalOrder = Optional.empty();
            inventory = new Inventory();
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
            inventory.resetData(newData);
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
            EndAndTransactOrderCommandTest.ModelStubWithOrderAndInventory.this.optionalOrder = Optional.of(order);
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
            assert hasUnclosedOrder();
            TransactionRecord transaction = inventory.transactOrder(optionalOrder.get());
            // Reset to no order status
            optionalOrder = Optional.empty();
        }
    }
}
