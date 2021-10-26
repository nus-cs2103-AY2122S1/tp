package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.DisplayMode.DISPLAY_INVENTORY;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;
import seedu.address.model.order.TransactionTimeComparator;
import seedu.address.storage.BookKeepingStorage;
import seedu.address.storage.TransactionStorage;

/**
 * Represents the in-memory model of BogoBogo data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final String TRANSACTION_LOGGING_MSG = "Transacted successfully: ";

    private final Inventory inventory;
    private final UserPrefs userPrefs;
    private final DisplayList displayList;
    private Optional<Order> optionalOrder;
    private Set<TransactionRecord> transactions;
    private BookKeeping bookKeeping;

    private DisplayMode currentDisplay = DISPLAY_INVENTORY;

    /**
     * Initializes a ModelManager with the given inventory and userPrefs.
     */
    public ModelManager(ReadOnlyInventory inventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(inventory, userPrefs);

        logger.fine("Initializing with inventory: " + inventory + " and user prefs " + userPrefs);

        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        displayList = new DisplayList(this.inventory.getItemList());
        optionalOrder = Optional.empty();
        List<TransactionRecord> transactionRecordList = null;
        bookKeeping = new BookKeeping(0.0, 0.0, 0.0);
        try {
            transactionRecordList = new TransactionStorage()
                    .readTransaction(userPrefs.getTransactionFilePath()).orElse(null);
            bookKeeping = new BookKeepingStorage()
                    .readBookKeeping(userPrefs.getBookKeepingFilePath())
                    .orElse(new BookKeeping(0.0, 0.0, 0.0));
        } catch (DataConversionException e) {
            System.out.println(e);
        }

        transactions = transactionRecordList == null ? new HashSet<>() : new HashSet<>(transactionRecordList);
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
        updateFilteredItemList(DisplayMode.DISPLAY_INVENTORY,
                PREDICATE_SHOW_ALL_ITEMS);
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
        return displayList.getFilteredItemList();
    }

    @Override
    public void updateFilteredItemList(DisplayMode mode, Predicate<Item> predicate) {
        requireNonNull(predicate);

        // Switch display mode if needed
        if (currentDisplay != mode) {
            if (mode == DISPLAY_INVENTORY) {
                // Display inventory items
                displayList.setItems(this.inventory.getItemList());
            } else {
                // Display unopened order items
                displayList.setItems(
                    this.optionalOrder
                        .map(Order::getOrderItems)
                        .orElse(FXCollections.observableArrayList())
                );
            }

            currentDisplay = mode;
        }

        // Update predicate
        displayList.setPredicate(predicate);
    }

    @Override
    public DisplayMode getDisplayMode() {
        return currentDisplay;
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
                && displayList.equals(other.displayList);
    }

    // ============== Order related methods ========================

    @Override
    public void setOrder(Order order) {
        requireNonNull(order);

        optionalOrder = Optional.of(order);
    }

    public Order getOrder() {
        assert hasUnclosedOrder();

        return optionalOrder.get();
    }

    @Override
    public boolean hasUnclosedOrder() {
        return optionalOrder.isPresent();
    }

    @Override
    public void addToOrder(Item item) {
        requireNonNull(item);
        assert hasUnclosedOrder();

        optionalOrder.ifPresent(order -> order.addItem(item));
    }

    @Override
    public List<Item> getFromOrder(ItemDescriptor itemDescriptor) {
        requireNonNull(itemDescriptor);
        assert hasUnclosedOrder();

        return optionalOrder.map(order -> order.getItems(itemDescriptor)).get();
    }

    @Override
    public void removeFromOrder(Item item, int amount) {
        requireNonNull(item);
        assert hasUnclosedOrder();

        optionalOrder.ifPresent(order -> order.removeItem(item, amount));
    }

    @Override
    public void transactAndClearOrder() {
        transactAndClearOrder(userPrefs.getTransactionFilePath());
    }

    /**
     * Helper TransactAndClearOrder with given path (for testing purposes)
     *
     * @param path the path of the file
     */
    public void transactAndClearOrder(Path path) {
        assert hasUnclosedOrder();

        TransactionRecord transaction = inventory.transactOrder(optionalOrder.get());
        // Reset to no order status
        optionalOrder = Optional.empty();
        transactions.add(transaction);

        Double totalRevenue = transaction.getItems().stream()
                .map(i -> i.getCount() * i.getSalesPrice())
                .reduce(0.0, (subTotal, next) -> subTotal + next);

        addRevenueBookKeeping(totalRevenue);

        try {
            new TransactionStorage().saveTransaction(new ArrayList(transactions),
                    path);
        } catch (IOException e) {
            System.out.println(e);
        }


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

    //=========== BookKeeping ================================================================================

    public void saveBookKeeping(BookKeeping bookKeeping) {
        try {
            new BookKeepingStorage().saveTransaction(bookKeeping, userPrefs.getBookKeepingFilePath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addCostBookKeeping(Double cost) {
        bookKeeping.addCost(cost);
        saveBookKeeping(bookKeeping);
    }

    public void addRevenueBookKeeping(Double revenue) {
        bookKeeping.addRevenue(revenue);
        saveBookKeeping(bookKeeping);
    }
}
