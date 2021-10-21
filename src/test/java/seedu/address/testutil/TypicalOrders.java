package seedu.address.testutil;

import java.util.List;
import java.util.UUID;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.order.Order;

public class TypicalOrders {

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
}
