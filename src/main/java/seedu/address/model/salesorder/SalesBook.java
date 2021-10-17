package seedu.address.model.salesorder;


import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of sales orders supporting minimal list operations for v1.3.
 * Basic version does not support editing SalesOrders
 */
public class SalesBook implements Iterable<SalesOrder> {
    private final ObservableList<SalesOrder> internalList = FXCollections.observableArrayList();
    private final ObservableList<SalesOrder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void add(SalesOrder toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    public void remove(SalesOrder toRemove) {
        requireNonNull(toRemove);
        if(!internalList.remove(toRemove)) {
            throw new SalesOrderNotFoundException();
        }
    }

    public boolean hasOrder(SalesOrder order) {
        requireNonNull(order);
        return internalList.stream().anyMatch(order::equals);
    }

    public void markComplete(SalesOrder toMark) {
        requireNonNull(toMark);
        if (!hasOrder(toMark)) {
            throw new SalesOrderNotFoundException();
        }
        int index = internalList.indexOf(toMark);
        toMark.setIsComplete(true);
        internalList.set(index, toMark);
    }

    public ObservableList<SalesOrder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<SalesOrder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SalesBook
                && internalList.equals(((SalesBook) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
