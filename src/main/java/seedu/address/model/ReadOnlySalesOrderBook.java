package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySalesOrderBook {

    /**
     * Returns an unmodifiable view of the order list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();

}
