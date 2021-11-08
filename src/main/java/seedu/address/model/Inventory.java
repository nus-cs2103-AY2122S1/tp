package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.UniqueItemList;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;

/**
 * Wraps all data at the inventory level
 * Duplicates are aggregated by adjusting count (by .isSameItem comparison)
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

    public Inventory() {
    }

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
     * Returns true if an item with the same identity fields as {@code item} that exists in the inventory.
     * @see Item#isSameItem(Item)
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Returns true if an item with the same identity fields as {@code item} that exists in the inventory.
     * @see Item#isSameItem(Item)
     */
    public boolean hasId(Item item) {
        requireNonNull(item);
        return items.containsID(item);
    }

    /**
     * Returns true if an item with the same identity fields as {@code item} that exists in the inventory.
     * @see Item#isSameItem(Item)
     */
    public boolean hasName(Item item) {
        requireNonNull(item);
        return items.containsName(item);
    }

    /**
     * Returns list of items in the inventory that matches the given {@code ItemDescriptor}
     * @see ItemDescriptor#isMatch(Item)
     */
    public List<Item> getItems(ItemDescriptor descriptor) {
        requireNonNull(descriptor);
        return items.get(descriptor);
    }


    /**
     * Adds an item to the inventory.
     * If the item already exists in the inventory, increment its count.
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }

    /**
     * Sort items in the inventory using the given {@code comparator}
     */
    public void sortItems(Comparator<Item> comparator) {
        items.sortItems(comparator);
    }

    /**
     * Adds a list of items to the inventory.
     * If the item already exists in the inventory, increment its count.
     */
    public void addItems(List<Item> newItems) {
        for (Item newItem : newItems) {
            addItem(newItem);
        }
    }

    /**
     * Makes transaction of the item and update inventory accordingly.
     * Item to transact cannot be more than item in inventory.
     */
    private void transactItem(Item toTransact) {
        requireNonNull(toTransact);

        for (Item item : items.asUnmodifiableObservableList()) {
            if (item.isSameItem(toTransact)) {
                assert toTransact.getCount() <= item.getCount();
                items.setItem(item, item.updateCount(item.getCount() - toTransact.getCount()));
                break;
            }
        }

        return;
    }

    /**
     * Updates {@code Inventory} according to {@code Order}, and save the transaction as {@code TransactionRecord}.
     *
     * @return A {@code Transaction} recording items transacted.
     */
    public TransactionRecord transactOrder(Order order) {
        requireNonNull(order);

        for (Item item : order.getOrderItems()) {
            transactItem(item);
        }

        return new TransactionRecord(order);
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
     * Increments the count of the given {@code target} in the inventory by {@code amount}.
     * {@code target} must exist in the inventory.
     */
    public void restockItem(Item target, int amount) {
        requireNonNull(target);

        Item newItem = target.updateCount(target.getCount() + amount);
        items.setItem(target, newItem);
    }

    /**
     * Decrements the count of the given {@code target} in the inventory by {@code amount}.
     * {@code target} must exist in the inventory.
     * @throws IllegalArgumentException if {@code target}'s count less than amount.
     */
    public void removeItem(Item target, int amount) {
        requireNonNull(target);

        if (target.getCount() < amount) {
            throw new IllegalArgumentException();
        }

        Item newItem = target.updateCount(target.getCount() - amount);
        items.setItem(target, newItem);
    }

    /**
     * Deletes the specified item entirely from inventory.
     */
    public void deleteItem(Item toRemove) {
        requireNonNull(toRemove);

        items.remove(toRemove);
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
