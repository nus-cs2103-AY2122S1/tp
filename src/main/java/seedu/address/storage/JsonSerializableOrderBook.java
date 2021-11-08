package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.OrderBook;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.order.Order;

/**
 * An Immutable SalesBook that is serializable to JSON format.
 */
@JsonRootName(value = "orderBook")
class JsonSerializableOrderBook {

    public static final String MESSAGE_DUPLICATE_ORDER = "salesBook contains duplicate order(s).";
    public static final String MESSAGE_DUPLICATE_ORDER_ID = "salesBook contains duplicate order id(s).";

    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrderBook} with the given orders.
     */
    @JsonCreator
    public JsonSerializableOrderBook(@JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyOrderBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderBook}.
     */
    public JsonSerializableOrderBook(ReadOnlyOrderBook source) {
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this order book into the model's {@code OrderBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public OrderBook toModelType() throws IllegalValueException {
        OrderBook orderBook = new OrderBook();
        long localCount = 0;

        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();

            if (localCount < order.getId()) {
                localCount = order.getId();
            }
            if (orderBook.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }

            if (orderBook.hasOrder(order.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER_ID);
            }
            orderBook.addOrder(order);
        }

        Order.setCount(localCount + 1);
        return orderBook;
    }

}
