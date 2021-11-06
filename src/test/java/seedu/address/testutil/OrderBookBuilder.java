package seedu.address.testutil;

import seedu.address.model.OrderBook;
import seedu.address.model.order.Order;

/**
 * A utility class to help with building OrderBook objects.
 * Example usage: <br>
 *     {@code OrderBook ob = new OrderBuilder().withCustomer("Josh")
 *             .withDate("2021-09-19").withAmount("10").build();}
 */
public class OrderBookBuilder {

    private OrderBook orderBook;

    public OrderBookBuilder() {
        orderBook = new OrderBook();
    }

    public OrderBookBuilder(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    /**
     * Adds a new {@code Order} to the {@code OrderBookBuilder} that we are building.
     */
    public OrderBookBuilder withOrder(Order order) {
        orderBook.addOrder(order);
        return this;
    }

    public OrderBook build() {
        return orderBook;
    }
}
