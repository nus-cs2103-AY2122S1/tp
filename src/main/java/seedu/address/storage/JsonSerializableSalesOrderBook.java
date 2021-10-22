package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySalesOrderBook;
import seedu.address.model.SalesOrderBook;
import seedu.address.model.order.Order;

/**
 * An Immutable TaskBook that is serializable to JSON format.
 */
@JsonRootName(value = "salesOrder")
class JsonSerializableSalesOrderBook {

    public static final String MESSAGE_DUPLICATE_TASK = "sales order contains duplicate task(s).";

    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskList} with the given task.
     */
    @JsonCreator
    public JsonSerializableSalesOrderBook(@JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableSalesOrderBook(ReadOnlySalesOrderBook source) {
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SalesOrderBook toModelType() throws IllegalValueException {
        SalesOrderBook orderBook = new SalesOrderBook();
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            orderBook.addOrder(order);
        }
        return orderBook;
    }

}
