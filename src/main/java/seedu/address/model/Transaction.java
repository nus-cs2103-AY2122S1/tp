package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Records a list of items transacted in an order. Immutable.
 */
public class Transaction {
    private final UniqueItemList items;

    public Transaction(UniqueItemList items) {
        this.items = items;
    }

    /**
     * Get a list of items in the transaction.
     */
    public ObservableList<Item> getItems() {
        return items.asUnmodifiableObservableList();
    }
}
