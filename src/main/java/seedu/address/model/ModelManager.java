package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTION;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTION_LIST;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.display.DisplayList;
import seedu.address.model.display.DisplayMode;
import seedu.address.model.display.Displayable;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;

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
    private TransactionList transactions;
    private BookKeeping bookKeeping;

    private DisplayMode currentDisplay = DISPLAY_INVENTORY;

    /**
     * Initializes a ModelManager with the given inventory and userPrefs.
     */
    public ModelManager(ReadOnlyInventory inventory, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyTransactionList transactionList, ReadOnlyBookKeeping bookKeeping) {
        super();
        requireAllNonNull(inventory, userPrefs, transactionList, bookKeeping);

        logger.fine("Initializing with inventory: " + inventory + ", user prefs " + userPrefs
            + ", transaction list: " + transactionList + ", bookkeeping: " + bookKeeping);

        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        this.transactions = new TransactionList(transactionList);
        displayList = new DisplayList(this.inventory.getItemList());
        optionalOrder = Optional.empty();
        this.bookKeeping = new BookKeeping(bookKeeping);
    }

    public ModelManager() {
        this(new Inventory(), new UserPrefs(), new TransactionList(), new BookKeeping());
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

    /**
     * Returns true if an item with the same identity fields as {@code item} that exists in the inventory.
     * @see Item#isSameItem(Item)
     */
    @Override
    public boolean hasId(Item item) {
        requireNonNull(item);
        return inventory.hasId(item);
    }

    /**
     * Returns true if an item with the same identity fields as {@code item} that exists in the inventory.
     * @see Item#isSameItem(Item)
     */
    @Override
    public boolean hasName(Item item) {
        requireNonNull(item);
        return inventory.hasName(item);
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
        updateFilteredDisplayList(DisplayMode.DISPLAY_INVENTORY,
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
     * Returns an unmodifiable view of the list of {@code Displayable} backed by the internal list of
     * {@code versionedInventory}
     */
    @Override
    public ObservableList<Displayable> getFilteredDisplayList() {
        return displayList.getFilteredDisplayList();
    }

    @Override
    public void updateFilteredDisplayList(DisplayMode mode, Predicate<Displayable> predicate) {
        requireNonNull(predicate);

        // Switch display mode if needed
        if (currentDisplay != mode) {
            if (mode == DISPLAY_INVENTORY) {
                // Display inventory items
                displayList.setItems(this.inventory.getItemList());
            } else if (mode == DISPLAY_OPEN_ORDER) {
                // Display unopened order items
                assert(hasUnclosedOrder());
                displayList.setItems(
                    this.optionalOrder.map(Order::getOrderItems).get()
                );
            } else {
                displayList.setItems(transactions.getTransactionRecordList());
            }

            currentDisplay = mode;
        }

        // Update predicate
        displayList.setPredicate(predicate);
    }



    @Override
    public void updateFilteredItemList(DisplayMode mode, Predicate<Item> predicate) {

        if (mode == DISPLAY_TRANSACTION_LIST) {
            throw new ClassCastException("Cannot filter transactions with Predicate<Item>");
        }

        updateFilteredDisplayList(mode, x -> predicate.test((Item) x));
    }

    @Override
    public Double openTransaction(String id) {
        // Attempt to find transaction with matching id
        Optional<TransactionRecord> transactionOptional = transactions.getTransactionRecordList().stream()
                .filter(txn -> txn.getId().equals(id))
                .findFirst();

        // If transaction found, return false
        if (transactionOptional.isEmpty()) {
            return -1.0;
        }

        Double totalCost = transactionOptional.get().getOrderItems().stream()
                .map(item -> item.getSalesPrice() * item.getCount())
                .reduce((a, b) -> a + b).get();

        // Display transaction
        currentDisplay = DISPLAY_TRANSACTION;
        displayList.setItems(transactionOptional.get().getOrderItems());
        return totalCost;
    }
    public DisplayList getDisplayList() {
        return this.displayList;
    }

    @Override
    public DisplayMode getDisplayMode() {
        return currentDisplay;
    }

    @Override
    public BookKeeping getBookKeeping() {
        return this.bookKeeping;
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
                && displayList.equals(other.displayList)
                && optionalOrder.equals(other.optionalOrder)
                && transactions.equals(other.transactions)
                && bookKeeping.equals(other.bookKeeping);
    }


    // ============== Order related methods ========================

    @Override
    public void setOrder(Order order) {
        requireNonNull(order);

        optionalOrder = Optional.of(order);
    }

    @Override
    public Order getOrder() {
        assert hasUnclosedOrder();

        return optionalOrder.get();
    }

    @Override
    public void closeOrder() {
        assert hasUnclosedOrder();
        optionalOrder = Optional.empty();
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
    public void transactAndCloseOrder() {
        transactAndClearOrder();
    }

    /**
     * Helper TransactAndClearOrder with given path (for testing purposes)
     * Model must have an unclosed order. The order must have at least 1 item.
     */
    public void transactAndClearOrder() {
        assert hasUnclosedOrder();
        assert !optionalOrder.get().isEmpty();

        TransactionRecord transaction = inventory.transactOrder(optionalOrder.get());

        transactions.add(transaction);
        transaction.getOrderItems().stream()
                .forEach(item -> addRevenueBookKeeping(item.getSalesPrice(), item.getCount()));

        closeOrder();

        logger.fine(TRANSACTION_LOGGING_MSG + transaction);
    }

    @Override
    public ReadOnlyTransactionList getTransactions() {
        return transactions;
    }


    @Override
    public void initialiseTransactions() {
        transactions = new TransactionList();
    }

    //=========== BookKeeping ================================================================================

    @Override
    public void addCostBookKeeping(Double revenue, int amount) {
        bookKeeping.addCost(revenue, amount);
    }

    @Override
    public void addRevenueBookKeeping(Double revenue, int amount) {
        bookKeeping.addRevenue(revenue, amount);
    }

    @Override
    public void initialiseBookKeeping() {
        bookKeeping.initialise();
    }

    /**
     * set bookKeeping.
     * @param bookKeeping the bookKeeping it will be set to.
     */
    public void setBookKeeping(BookKeeping bookKeeping) {
        this.bookKeeping = bookKeeping;
    }
}
