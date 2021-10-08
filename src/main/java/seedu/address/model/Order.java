package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;

/**
 * API of the Order component
 */
public interface Order {
    /**
     * Add an {@code Item} to the order.
     */
    void addItem(Item newItem);

    /**
     * Remove the specified {@code Item} from order.
     */
    void removeItem(Item toBeRemoved);

    /**
     * Get a list of items in the order.
     */
    ObservableList<Item> getOrderItems();
}
