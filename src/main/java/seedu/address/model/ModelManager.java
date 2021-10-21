package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;
import seedu.address.model.order.TransactionTimeComparator;

/**
 * Represents the in-memory model of BogoBogo data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final String TRANSACTION_LOGGING_MSG = "Transacted successfully: ";

    private final Inventory inventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;
    private Optional<Order> optionalOrder;
    private Set<TransactionRecord> transactions;

    /**
     * Initializes a ModelManager with the given inventory and userPrefs.
     */
    public ModelManager(ReadOnlyInventory inventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(inventory, userPrefs);

        logger.fine("Initializing with inventory: " + inventory + " and user prefs " + userPrefs);

        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.inventory.getItemList());
        optionalOrder = Optional.empty();
        transactions = new HashSet<>();
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
    public List<Item> getItems(ItemDescriptor descriptor) {
        requireNonNull(descriptor);
        return inventory.getItems(descriptor);
    }

    @Override
    public void deleteItem(Item item) {
        inventory.deleteItem(item);
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
    public void restockItem(Item target, int amount) {
        requireNonNull(target);
        inventory.restockItem(target, amount);
    }

    @Override
    public void removeItem(Item target, int amount) {
        requireNonNull(target);
        inventory.removeItem(target, amount);
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
        requireNonNull(item);
        assert hasUnclosedOrder();

        optionalOrder.ifPresent(order -> order.addItem(item));
    }

    /**
     * Returns the items in the order that match the {@code itemDescriptor}.
     * @see ItemDescriptor#isMatch(Item) 
     */
    @Override
    public List<Item> getFromOrder(ItemDescriptor itemDescriptor) {
        requireNonNull(itemDescriptor);
        assert hasUnclosedOrder();

        optionalOrder.map(order -> order.getItems(itemDescriptor)).get();
    }

    /**
     * Removes the item from the current order list.
     */
    @Override
    public void removeFromOrder(Item item) {
        requireNonNull(item);
        assert hasUnclosedOrder();

        optionalOrder.ifPresent(order -> order.removeItem(item));
    }

    /**
     * Updates the inventory according to current order and exit ordering mode, the transaction is recorded as well.
     */
    @Override
    public void transactAndClearOrder() {
        assert hasUnclosedOrder();

        TransactionRecord transaction = inventory.transactOrder(optionalOrder.get());
        // Reset to no order status
        optionalOrder = Optional.empty();
        transactions.add(transaction);
        logger.fine(TRANSACTION_LOGGING_MSG + transaction.toString());
    }

    /**
     * Return a list of {@code TransactionRecord} sorted according to given comparator.
     */
    public List<TransactionRecord> getTransactions(Comparator<TransactionRecord> comparator) {
        requireNonNull(comparator);

        List<TransactionRecord> resultList = new ArrayList<>(transactions);
        resultList.sort(comparator);

        return resultList;
    }

    /**
     * Return a list of {@code TransactionRecord} sorted according to timestamp.
     */
    public List<TransactionRecord> getTransactions() {
        return getTransactions(new TransactionTimeComparator());
    }
}
