package seedu.address.model;

import java.time.Instant;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Records a list of items transacted items in an order. Immutable.
 */
public class TransactionRecord {
    private final UniqueItemList items;
    private final long timestamp;

    public TransactionRecord(UniqueItemList items) {
        this.items = items;
        timestamp = Instant.now().getNano();
    }

    /**
     * Get a list of items in the transaction.
     */
    public ObservableList<Item> getItems() {
        return items.asUnmodifiableObservableList();
    }
}
