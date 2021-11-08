package seedu.address.model.order;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
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
        super(justTransactedDetails(order.getOrderItems()));

        timestamp = Instant.now();
        // id is a randomly generated string with timestamp as the seed (for testability)
        long seed = timestamp.getEpochSecond() / 5;
        id = StringUtil.generateRandomString(seed);
    }

    /**
     * Instantiates a transaction record with the given list of items, id, and timestamp.
     */
    public TransactionRecord(List<Item> items, String id, Instant timestamp) {
        super(justTransactedDetails(items));

        this.id = id;
        this.timestamp = timestamp;
    }

    private static List<Item> justTransactedDetails(List<Item> toCopy) {
        List<Item> newList = new ArrayList<>();
        for (Item item: toCopy) {
            newList.add(item.justTransactedDetails());
        }
        return newList;
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
     * Returns true if items in the transacted order matches with the ones in the {@code otherList}.
     * Only considers name, id, quantity and sales price. (i.e. information relevant to transaction).
     */
    public boolean isSameTransactionInfo(ObservableList<Item> otherList) {

        if (getOrderItems().size() != otherList.size()) {
            return false;
        }

        for (int i = 0; i < getOrderItems().size(); i++) {
            Item item1 = getOrderItems().get(i);
            Item item2 = otherList.get(i);

            if (!item1.getName().equals(item2.getName())
                || !item1.getId().equals(item2.getId())
                || !item1.getCount().equals(item2.getCount())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if two transactions have the same items, id and timestamp.
     * Does not consider the equality of items' cost since we have no interest in the cost of transacted items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof TransactionRecord) {
            TransactionRecord temp = (TransactionRecord) other;
            return id.equals(temp.id)
                    && timestamp.truncatedTo(ChronoUnit.MINUTES).equals(temp.timestamp.truncatedTo(ChronoUnit.MINUTES))
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
