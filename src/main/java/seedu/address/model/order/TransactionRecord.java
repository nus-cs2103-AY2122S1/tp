package seedu.address.model.order;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Records a list of items transacted items in an order. Immutable.
 */
public class TransactionRecord {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final UniqueItemList items;
    private final Instant timestamp;
    private String id;

    /**
     * Instantiates a transaction record with a list of items.
     */
    public TransactionRecord(UniqueItemList items) {
        this.items = items;
        id = StringUtil.generateRandomString();
        timestamp = Instant.now();
    }

    /**
     * Get a list of items in the transaction.
     */
    public ObservableList<Item> getItems() {
        return items.asUnmodifiableObservableList();
    }

    /**
     * Returns the id of the transaction.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the {@code Instance} timestamp of the transaction.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Returns true if two transactions have the same id and timestamp.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof TransactionRecord) {
            TransactionRecord temp = (TransactionRecord) other;
            return temp.id.equals(id) && temp.timestamp.equals(timestamp);
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the transaction.
     */
    @Override
    public String toString() {
        return String.format("%s %s", DATE_TIME_FORMATTER.format(timestamp), id);
    }
}
