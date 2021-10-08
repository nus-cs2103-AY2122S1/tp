package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

public class OrderManager implements Order {
    private UniqueItemList items;

    public OrderManager() {
        items = new UniqueItemList();
    }

    /**
     * Add an {@code Item} to the order.
     */
    @Override
    public void addItem(Item newItem) {
        items.add(newItem);

    }

    /**
     * Remove the specified {@code Item} from order.
     */
    @Override
    public void removeItem(Item toBeRemoved) {
        items.remove(toBeRemoved);
    }

    @Override
    public ObservableList getOrderItems() {
        return items.asUnmodifiableObservableList();
    }
}
