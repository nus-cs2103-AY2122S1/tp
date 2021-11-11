package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.order.UniqueOrderList;


/**
 * Wraps all data at the order-book level
 * Duplicates are not allowed (by .isSameOrder comparison)
 */
public class OrderBook implements ReadOnlyOrderBook {

    private final UniqueOrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        orders = new UniqueOrderList();
    }

    public OrderBook() {}

    /**
     * Creates an OrderBook using the Orders in the {@code toBeCopied}
     */
    public OrderBook(ReadOnlyOrderBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the orders list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Updates the orders matching the predicate, according to the given function.
     */
    public void updateOrders(Predicate<Order> pred, Function<Order, Order> f) {
        ArrayList<Order> newList = new ArrayList<Order>();
        for (Order order : orders) {
            if (pred.test(order)) {
                // if it matches predicate, replace it by applying the given function
                newList.add(f.apply(order));
            } else {
                // else retain it.
                newList.add(order);
            }
        }
        this.setOrders(newList);
    }

    /**
     * Resets the existing data of this {@code OrderBook} with {@code newData}.
     */
    public void resetData(ReadOnlyOrderBook newData) {
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

    public void deleteOrderIf(Predicate<Order> pred) {
        orders.removeIf(pred);
    }

    public boolean markOrder(Order order) {
        return orders.markComplete(order);
    }

    public void setOrder(Order target, Order editedOrder) {
        orders.setOrder(target, editedOrder);
    }

    public boolean hasOrder(Order order) {
        return orders.hasOrder(order);
    }

    public boolean hasOrder(long id) {
        return orders.hasOrder(id);
    }

    public void sortOrders(Comparator<Order> comparator) {
        orders.sort(comparator);
    }

    //// util methods

    @Override
    public String toString() {
        return orders.asUnmodifiableObservableList().size() + " orders";
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderBook // instanceof handles nulls
                && orders.equals(((OrderBook) other).orders));
    }

    @Override
    public int hashCode() {
        return orders.hashCode();
    }
}
