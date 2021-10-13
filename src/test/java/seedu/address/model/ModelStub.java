package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

/**
 * A default model stub that have all of its methods failing.
 */
public class ModelStub implements Model {

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
    public boolean hasItem(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasItem(String id) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Item deleteItem(Name name, int count) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Item deleteItem(String id, int count) {
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
    public Item getItemWithId(String id) {
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
