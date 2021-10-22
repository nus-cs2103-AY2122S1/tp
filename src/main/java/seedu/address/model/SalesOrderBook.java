package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;



/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SalesOrderBook implements ReadOnlySalesOrderBook {

    private final OrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        orders = new OrderList();
    }

    public SalesOrderBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public SalesOrderBook(ReadOnlySalesOrderBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the tasks list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlySalesOrderBook newData) {
        requireNonNull(newData);

        setOrders(newData.getOrderList());

    }

    /// order-level operations

    public void addOrder(Order toAdd) {
        orders.add(toAdd);
    }

    public void deleteOrder(Order toDelete) {
        orders.remove(toDelete);
    }

    public void markOrder(Order order) {
        orders.markComplete(order);
    }

    public void setOrder(Order target, Order editedOrder) {
        orders.setOrder(target, editedOrder);
    }

    public boolean hasOrder(Order order) {
        return orders.hasOrder(order);
    }


    //// util methods

    @Override
    public String toString() {
        return orders.asUnmodifiableObservableList().size() + " orders.";
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SalesOrderBook // instanceof handles nulls
                && orders.equals(((SalesOrderBook) other).orders));
    }

    @Override
    public int hashCode() {
        return orders.hashCode();
    }
}
