package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the order level
 * Duplicates are aggregated by adjusting count (by .isSameItem comparison)
 */
public class Order {
    private final UniqueItemList items;

    /**
     * Instantiates an empty order.
     */
    public Order() {
        items = new UniqueItemList();
    }

    /**
     * Instantiates an order with a list of ordered items.
     */
    public Order(List<Item> itemCollection) {
        requireNonNull(itemCollection);
        items = new UniqueItemList();
        items.setItems(itemCollection);
    }

    /**
     * Add an {@code Item} to the order.
     */
    public void addItem(Item newItem) {
        requireNonNull(newItem);

        if (!items.contains(newItem)) {
            items.add(newItem);
        }

        Item existingItem = items.get(new ItemDescriptor(newItem)).get(0);

        int newCount = existingItem.getCount() + newItem.getCount();
        items.setItem(existingItem, existingItem.updateCount(newCount));
    }

    /**
     * Remove the specified {@code Item} from order.
     */
    public void removeItem(Item toBeRemoved) {
        requireNonNull(toBeRemoved);

        for (Item item : items.asUnmodifiableObservableList()) {
            if (item.isSameItem(toBeRemoved)) { // Same name OR same id
                items.remove(item);
                break;
            }
            ;
        }
    }

    /**
     * Gets a list of items in the order.
     */
    public ObservableList<Item> getOrderItems() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return items.equals(otherOrder.items);
    }
}
