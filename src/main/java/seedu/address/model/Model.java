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
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Displayable> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getInventoryFilePath();

    /**
     * Sets the user prefs' inventory file path.
     */
    void setInventoryFilePath(Path inventoryFilePath);

    /**
     * Replaces inventory data with the data in {@code inventory}.
     */
    void setInventory(ReadOnlyInventory inventory);

    /**
     * Returns the Inventory
     */
    ReadOnlyInventory getInventory();

    /**
     * Returns true if an item with the given {@code id} exists in the inventory.
     *
     * @see Item#isSameItem
     */
    boolean hasItem(Item item);

    /**
     * Returns true if an item with the given {@code id} exists in the inventory.
     *
     * @see Item#isSameItem
     */
    boolean hasId(Item item);

    /**
     * Returns true if an item with the given {@code id} exists in the inventory.
     *
     * @see Item#isSameItem
     */
    boolean hasName(Item item);

    /**
     * Returns list of items in the inventory that matches the given {@code ItemDescriptor}
     */
    List<Item> getItems(ItemDescriptor descriptor);

    /**
     * Deletes {@code item} from the inventory .
     * The item must exist in the inventory.
     */
    void deleteItem(Item item);

    /**
     * Adds the given item into inventory
     */
    void addItem(Item item);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the inventory.
     * The item identity of {@code editedItem} must not be the same as another existing item in the inventory.
     */
    void setItem(Item target, Item editedItem);

    /**
     * Increments the count of the given {@code target} in the inventory by {@code amount}.
     * {@code target} must exist in the inventory.
     */
    void restockItem(Item target, int amount);

    /**
     * Decrements the count of the given {@code target} in the inventory by {@code amount}.
     * {@code target} must exist in the inventory.
     * {@code amount} must be less than {@code target}'s count.
     */
    void removeItem(Item target, int amount);

    /**
     * Sorts the item list using the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortItems(Comparator<Item> comparator);

    /**
     * Sets the current order of the model.
     */
    void setOrder(Order order);

    /**
     * Returns a boolean that the model has an unclosed order or not.
     */
    boolean hasUnclosedOrder();

    /**
     * Adds item to the current order list.
     */
    void addToOrder(Item item);

    /**
     * Get order.
     *
     * @return the order.
     */
    Order getOrder();

    /**
     * Close the current order. Does not save the order beforehand.
     * Model must have an unclosed order.
     */
    void closeOrder();

    /**
     * Decrements the count of the given {@code target} in the order by {@code amount}.
     * {@code target} must exist in the order.
     * {@code amount} must be less than {@code target}'s count.
     */
    void removeFromOrder(Item target, int amount);

    /**
     * Returns the items in the order that match the {@code itemDescriptor}.
     * @see ItemDescriptor#isMatch(Item)
     */
    public List<Item> getFromOrder(ItemDescriptor itemDescriptor);

    /**
     * Destroys the current order when ordering finish.
     */
    void transactAndCloseOrder();

    /**
     * Return a list of {@code TransactionRecord} sorted according to timestamp.
     */
    ReadOnlyTransactionList getTransactions();

    /**
     * Returns an unmodifiable view of the filtered list to be displayed.
     */
    ObservableList<Displayable> getFilteredDisplayList();

    /**
     * Updates the filter of the filtered display list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDisplayList(DisplayMode mode, Predicate<Displayable> predicate);

    /**
     * Updates the filter of the filtered display list to filter by the given {@code predicate}.
     * {@code mode} cannot be DISPLAY_TRANSACTIONS.
     * If displaying transactions, use {@code updateFilteredDisplayList} instead.
     *
     * @throws NullPointerException if {@code predicate} is null.
     * @throws ClassCastException if current displayed list is not displaying items.
     */
    void updateFilteredItemList(DisplayMode mode, Predicate<Item> predicate);

    /**
     * Opens the past transaction that has the given {@code id}.
     *
     * @Returns totalCost if transaction found, and -1.0 otherwise.
     */
    Double openTransaction(String id);

    /**
     * Returns the model's current {@code DisplayMode}.
     *
     * @see DisplayMode
     */
    DisplayMode getDisplayMode();

    /**
     * Initialise Transactions.
     */
    void initialiseTransactions();

    /**
     * Add a specified cost to bookKeeping.
     * @param cost of the item
     * @param amount of the item
     */
    void addCostBookKeeping(Double cost, int amount);

    /**
     * Add a specified revenue to bookKeeping.
     * @param revenue gained when buying the item
     * @param amount of the item
     */
    void addRevenueBookKeeping(Double revenue, int amount);

    /**
     * Return the current BookKeeping.
     * @return the current BookKeeping.
     */
    BookKeeping getBookKeeping();

    /**
     * Initialise BookKeeping.
     */
    void initialiseBookKeeping();
}
