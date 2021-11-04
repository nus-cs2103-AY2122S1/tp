package seedu.address.model.order;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.scene.layout.Region;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.display.Displayable;
import seedu.address.model.display.TransactionCard;
import seedu.address.model.item.Item;
import seedu.address.ui.UiPart;

/**
 * Represents a transacted order.
 * Transacted orders are timestamped and given an id.
 * Order should be immutable (since it already has been transacted).
 */
public class TransactionRecord extends Order implements Displayable {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final Instant timestamp;
    private String id;

    /**
     * Instantiates a transaction record based off the given order.
     */
    public TransactionRecord(Order order) {
        super(order.getOrderItems());

        id = StringUtil.generateRandomString();
        timestamp = Instant.now();
    }

    /**
     * Instantiates a transaction record with the given list of items, id, and timestamp.
     */
    public TransactionRecord(List<Item> items, String id, Instant timestamp) {
        super(items);

        this.id = id;
        this.timestamp = timestamp;
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
     * Returns the timestamp of the transaction in a readable format.
     */
    public String getTimeString() {
        return DATE_TIME_FORMATTER.format(timestamp);
    }

    @Override
    public void addItem(Item item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeItem(Item target, int amount) {
        throw new UnsupportedOperationException();
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
            return id.equals(temp.id)
                    && timestamp.equals(temp.timestamp)
                    && getOrderItems().equals(temp.getOrderItems());
        }

        return false;
    }

    @Override
    public UiPart<Region> asDisplayCard(int index) {
        return new TransactionCard(this, index);
    }

    /**
     * Returns the string representation of the transaction.
     */
    @Override
    public String toString() {
        return String.format("%s %s", DATE_TIME_FORMATTER.format(timestamp), id);
    }
}
