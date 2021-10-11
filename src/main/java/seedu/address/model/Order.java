package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

public class Order {
    private final UniqueItemList items;

    public Order() {
        items = new UniqueItemList();
    }

    /**
     * Add an {@code Item} to the order.
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }

    /**
     * Remove the specified {@code Item} from order.
     */
    public void removeItem(Item toBeRemoved) {
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
