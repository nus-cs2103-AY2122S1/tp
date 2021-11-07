package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.display.DisplayMode;
import seedu.address.model.display.Displayable;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;

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
    public boolean hasId(Item item) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasName(Item item) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<Item> getItems(ItemDescriptor descriptor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteItem(Item target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void restockItem(Item target, int amount) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeItem(Item target, int amount) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortItems(Comparator<Item> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Displayable> getFilteredDisplayList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredDisplayList(DisplayMode mode, Predicate<Displayable> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredItemList(DisplayMode mode, Predicate<Item> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Double openTransaction(String id) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public DisplayMode getDisplayMode() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void initialiseTransactions() {
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
    public void closeOrder() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addToOrder(Item item) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<Item> getFromOrder(ItemDescriptor itemDescriptor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeFromOrder(Item item, int amount) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void transactAndCloseOrder() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCostBookKeeping(Double cost, int amount) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Order getOrder() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTransactionList getTransactions() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRevenueBookKeeping(Double revenue, int amount) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public BookKeeping getBookKeeping() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void initialiseBookKeeping() {
        throw new AssertionError("This method should not be called.");
    }
}
