package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
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
     * Returns true if an item with the given {@code name} exists in the inventory.
     */
    public boolean hasItem(Name name) {
        requireNonNull(name);
        return items.contains(name);
    }

    /**
     * Returns true if an item with the given {@code id} exists in the inventory.
     */
    public boolean hasItem(String id) {
        requireNonNull(id);
        return items.contains(id);
    }

    /**
     * Returns the item with the same name as {@code item} that exists in the inventory.
     */
    public Item getItemWithName(String name) {
        requireNonNull(name);
        ObservableList<Item> ls = items.asUnmodifiableObservableList();
        for (Item item : ls) {
            if (item.getName().toString().equals(name)) {
                return item;
            }
        }
        throw new AssertionError("unreachable code (if implemented correctly)");
    }

    /**
     * Returns the item with the same id as {@code item} that exists in the inventory.
     */
    public Item getItemWithId(String id) {
        requireNonNull(id);
        ObservableList<Item> ls = items.asUnmodifiableObservableList();
        for (Item item : ls) {
            if (item.getId().toString().equals(id)) {
                return item;
            }
        }
        throw new AssertionError("unreachable code (if implemented correctly)");
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
     *
     * @return Number of items actually consumed in the transaction.
     */
    private int transactItem(Item toTransact) {
        requireNonNull(toTransact);

        int transactedQuantity = 0;
        for (Item item : items.asUnmodifiableObservableList()) {
            if (item.isSameItem(toTransact)) {
                transactedQuantity = Math.min(item.getCount(), toTransact.getCount());
                if (transactedQuantity == item.getCount()) {
                    items.remove(item);
                } else {
                    items.setItem(item, item.updateCount(item.getCount() - transactedQuantity));
                }
                break;
            }
        }

        return transactedQuantity;
    }

    /**
     * Updates {@code Inventory} according to {@code Order}, and save the transaction as {@code TransactionRecord}.
     *
     * @return A {@code Transaction} recording items transacted.
     */
    public TransactionRecord transactOrder(Order order) {
        requireNonNull(order);

        UniqueItemList transactedItems = new UniqueItemList();

        for (Item item : order.getOrderItems()) {
            int transactedQuantity = transactItem(item);
            if (transactedQuantity > 0) {
                transactedItems.add(new Item(item, transactedQuantity));
            }
        }

        return new TransactionRecord(transactedItems);
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
     * Removes the specified amount of item with the given {@code name} from this {@code Inventory}.
     * If amount of item drops to 0 or less, remove the entire item from inventory
     * The specified item must exist in the inventory.
     * @param name name of item to remove
     * @param amount number of the item to remove, set to -1 to remove all.
     * @returns the deleted item
     */
    public Item removeItem(Name name, Integer amount) {
        requireNonNull(name);

        Item existingItem = items.getItem(name).get();
        items.remove(existingItem);

        int amountDeleted = (amount == -1)
                ? existingItem.getCount() : Math.min(amount, existingItem.getCount());

        int newCount = existingItem.getCount() - amountDeleted;
        if (newCount > 0) {
            items.add(existingItem.updateCount(newCount));
        }

        return existingItem.updateCount(amountDeleted);
    }

    /**
     * Removes the specified amount of item with the given {@code id} from this {@code Inventory}.
     * If amount of item drops to 0 or less, remove the entire item from inventory
     * The specified item must exist in the inventory.
     * @param id id of item to remove
     * @param amount amount of the item to remove, set to -1 to remove all.
     * @returns the deleted item
     */
    public Item removeItem(String id, Integer amount) {
        requireNonNull(id);

        Item existingItem = items.getItem(id).get();
        items.remove(existingItem);

        int amountDeleted = (amount == -1)
                ? existingItem.getCount() : Math.min(amount, existingItem.getCount());

        int newCount = existingItem.getCount() - amountDeleted;
        if (newCount > 0) {
            items.add(existingItem.updateCount(newCount));
        }

        return existingItem.updateCount(amountDeleted);
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
