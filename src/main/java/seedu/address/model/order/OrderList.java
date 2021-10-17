package seedu.address.model.order;


import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of orders supporting minimal list operations for v1.3.
 * Basic version does not support editing SalesOrders.
 */
public class OrderList implements Iterable<Order> {
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an order to the OrderList.
     * @param toAdd order to add
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes an order from the OrderList.
     * @param toRemove order to remove
     */
    public void remove(Order toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderNotFoundException();
        }
    }

    /**
     * Returns true if order is in the OrderList
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return internalList.stream().anyMatch(order::equals);
    }

    /**
     * Marks an order as complete, if it exists in the OrderList
     * @param toMark
     */
    public void markComplete(Order toMark) {
        requireNonNull(toMark);
        if (!hasOrder(toMark)) {
            throw new OrderNotFoundException();
        }
        int index = internalList.indexOf(toMark);
        toMark.setIsComplete(true);
        internalList.set(index, toMark);
    }

    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrderList
                && internalList.equals(((OrderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
