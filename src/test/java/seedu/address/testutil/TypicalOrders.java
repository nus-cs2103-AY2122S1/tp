package seedu.address.testutil;

import java.time.Instant;
import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.TransactionList;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;

public class TypicalOrders {

    // Default values for typical transaction
    private static final String DEFAULT_ID = "kjagajnoar";
    private static final Instant DEFAULT_TIMESTAMP = Instant.ofEpochMilli(1000);

    /**
     * Returns an {@code order} with all the typical items.
     */
    public static Order getTypicalOrder() {
        return new Order(TypicalItems.getTypicalItems());
    }

    /**
     * Returns an {@code order} with one item which has count equals to {@code Integer.MAX_VALUE}.
     */
    public static Order getOrderWithItemWithLargeCount() {
        return new Order(List.of(
                new ItemBuilder().withName("Apple Pie")
                        .withId("222222")
                        .withCount(String.valueOf(Integer.MAX_VALUE))
                        .withTags("baked").build()));
    }

    /**
     * Returns an {@code order} with one item which has count equals to -1.
     */
    public static Order getOrderWithItemWithNegativeCount() {
        return new Order(List.of(
                new ItemBuilder().withName("Apple Pie")
                        .withId("222222")
                        .withCount(String.valueOf(-1))
                        .withTags("baked").build()));
    }

    /**
     * Returns an {@code order} with one item which has random name.
     */
    public static Order getOrderWithUnexistingItem() {
        return new Order(List.of(
                new ItemBuilder().withName(StringUtil.generateRandomString())
                        .withId(String.valueOf(164582))
                        .withCount(String.valueOf(10))
                        .withTags("baked").build()));
    }

    /**
     * Returns a {@code TransactionRecord} containing all typical items in {@code getTypicalItems()}
     * All items in the list has a defaulted cost price of $0 since TransactionRecord is not concerned
     * with the cost of an item.
     */
    public static TransactionRecord getTypicalTransaction() {
        return new TransactionRecord(getTypicalOrder().getOrderItems(), DEFAULT_ID, DEFAULT_TIMESTAMP);
    }

    /**
     * Returns a list of transaction containing a typical transaction.
     */
    public static TransactionList getTypicalTransactionList() {
        TransactionList typicalList = new TransactionList();

        typicalList.add(getTypicalTransaction());

        return typicalList;
    }
}
