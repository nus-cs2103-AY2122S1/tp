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
import seedu.address.model.Model;
import seedu.address.model.Order;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyUserPrefs;
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
        public void setOrder(Order order) {
            ModelStubWithOrder.this.optionalOrder = Optional.of(order);
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void transactAndClearOrder() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
