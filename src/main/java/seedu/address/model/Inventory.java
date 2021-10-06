package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the inventory level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class Inventory implements ReadOnlyInventory {

    private final UniqueItemList items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        items = new UniqueItemList();
    }

    public Inventory() {}

    /**
     * Creates an Inventory using the Items in the {@code toBeCopied}
     */
    public Inventory(ReadOnlyInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<Item> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code Inventory} with {@code newData}.
     */
    public void resetData(ReadOnlyInventory newData) {
        requireNonNull(newData);

        setItems(newData.getItemList());
    }

    //// item-level operations

    /**
     * Returns true if an item with the same id as {@code item} that exists in the inventory.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds an item to the inventory.
     * If the item already exists in the inventory, increment its count.
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the inventory.
     * The item identity of {@code editedItem} must not be the same as another existing item in the inventory.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);

        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code Inventory}.
     * {@code key} must exist in the inventory.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemList() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Inventory // instanceof handles nulls
                && items.equals(((Inventory) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
