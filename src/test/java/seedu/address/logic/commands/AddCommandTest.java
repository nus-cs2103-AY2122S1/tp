package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.Order;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.itemsAdded);
    }

    //    @Test
    //    public void execute_duplicateItem_throwsCommandException() {
    //        Item validItem = new ItemBuilder().build();
    //        AddCommand addCommand = new AddCommand(validItem);
    //        ModelStub modelStub = new ModelStubWithItem(validItem);
    //
    //        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ITEM,
    //        () -> addCommand.execute(modelStub));
    //    }
    // TODO: TEST FOR DUPLICATE ITEMS

    @Test
    public void equals() {
        Item bagel = new ItemBuilder().withName("Bagel").build();
        Item donut = new ItemBuilder().withName("Donut").build();
        AddCommand addBagelCommand = new AddCommand(bagel);
        AddCommand addDonutCommand = new AddCommand(donut);

        // same object -> returns true
        assertTrue(addBagelCommand.equals(addBagelCommand));

        // same values -> returns true
        AddCommand addBagelCommandCopy = new AddCommand(bagel);
        assertTrue(addBagelCommand.equals(addBagelCommandCopy));

        // different types -> returns false
        assertFalse(addBagelCommand.equals(1));

        // null -> returns false
        assertFalse(addBagelCommand.equals(null));

        // different item -> returns false
        assertFalse(addBagelCommand.equals(addDonutCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUnclosedOrder() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addToOrder(Item item) {
            throw new AssertionError("This method should not be called.");
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

    /**
     * A Model stub that contains a single item.
     */
    private class ModelStubWithItem extends ModelStub {
        private final Item item;

        ModelStubWithItem(Item item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return this.item.isSameItem(item);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public ReadOnlyInventory getInventory() {
            return new Inventory();
        }
    }

}
