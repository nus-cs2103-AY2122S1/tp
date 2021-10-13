package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

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

    /** Returns the Inventory */
    ReadOnlyInventory getInventory();

    /**
     * Returns true if an item with the given {@code id} exists in the inventory.
     * @see Item#isSameItem
     */
    boolean hasItem(Item item);

    /**
     * Returns true if an item with the given {@code name} exists in the inventory.
     */
    boolean hasItem(Name name);

    /**
     * Returns true if an item with the given {@code id} exists in the inventory.
     */
    boolean hasItem(String id);

    /**
     * Deletes item in the inventory with the given name.
     * The item must exist in the inventory.
     * @param name name of the item to remove
     * @param count count of the item to remove, set to -1 to remove all
     * @returns the deleted item
     */
    Item deleteItem(Name name, int count);

    /**
     * Deletes item in the inventory with the given id.
     * The item must exist in the inventory.
     * @param id id of the item to remove
     * @param count count of the item to remove, set to -1 to remove all
     * @returns the deleted item
     */
    Item deleteItem(String id, int count);

    /**
     * Adds the given item.
     * If {@code item} must not already exist in the address book, increment its count accordingly.
     */
    void addItem(Item item);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the inventory.
     * The item identity of {@code editedItem} must not be the same as another existing item in the inventory.
     */
    void setItem(Item target, Item editedItem);

    /**
     * Sorts the item list using the given {@code comparator}.
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
     * Removes the item from the current order list.
     * @param item
     */
    void removeFromOrder(Item item);

    /**
     * Destroys the current order when ordering finish.
     */
    void transactAndClearOrder();

    /** Returns an unmodifiable view of the filtered item list */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    /**
     * Gets Item with the same name.
     *
     * @param name The name of the item that wants to be searched
     * @return the Item with the same name.
     */
    Item getItemWithName(String name);

    /**
     * Gets Item with the same id.
     *
     * @param id The name of the item that wants to be searched
     * @return the Item with the same name.
     */
    Item getItemWithId(String id);
}
