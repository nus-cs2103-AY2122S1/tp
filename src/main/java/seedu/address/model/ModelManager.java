package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Inventory inventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;
    private Optional<Order> optionalOrder;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInventory inventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(inventory, userPrefs);

        logger.fine("Initializing with address book: " + inventory + " and user prefs " + userPrefs);

        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.inventory.getItemList());
        optionalOrder = Optional.empty();
    }

    public ModelManager() {
        this(new Inventory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInventoryFilePath() {
        return userPrefs.getInventoryFilePath();
    }

    @Override
    public void setInventoryFilePath(Path inventoryFilePath) {
        requireNonNull(inventoryFilePath);
        userPrefs.setInventoryFilePath(inventoryFilePath);
    }

    //=========== Inventory ================================================================================

    @Override
    public void setInventory(ReadOnlyInventory inventory) {
        this.inventory.resetData(inventory);
    }

    @Override
    public ReadOnlyInventory getInventory() {
        return inventory;
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return inventory.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        inventory.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        inventory.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        inventory.setItem(target, editedItem);
    }

    @Override
    public void sortItems(Comparator<Item> comparator) {
        requireNonNull(comparator);
        inventory.sortItems(comparator);
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedInventory}
     */
    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return inventory.equals(other.inventory)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }

    @Override
    public Item getItemWithName(String name) {
        requireNonNull(name);
        return inventory.getItemWithName(name);
    }

    @Override
    public Item getItemWithId(String id) {
        requireNonNull(id);
        return inventory.getItemWithId(id);
    }

    // ============== Order related methods ========================

    /**
     * Sets the current order of the model.
     */
    @Override
    public void setOrder(Order order) {
        requireNonNull(order);

        optionalOrder = Optional.of(order);
    }

    public Order getOrder() {
        assert hasUnclosedOrder();

        return optionalOrder.get();
    }

    /**
     * Returns a boolean that the model has an unclosed order or not.
     */
    @Override
    public boolean hasUnclosedOrder() {
        return optionalOrder.isPresent();
    }

    /**
     * Adds item to the current order list.
     */
    @Override
    public void addToOrder(Item item) {
        assert hasUnclosedOrder();

        optionalOrder.get().addItem(item);
    }

    /**
     * Removes the item from the current order list.
     */
    @Override
    public void removeFromOrder(Item item) {
        assert hasUnclosedOrder();

        optionalOrder.get().removeItem(item);
    }

    /**
     * Destroys the current order when ordering finish.
     */
    @Override
    public void transactAndClearOrder() {
        assert hasUnclosedOrder();

        TransactionRecord transaction = inventory.transactOrder(optionalOrder.get());
        // Reset to no order status
        optionalOrder = Optional.empty();
    }
}
