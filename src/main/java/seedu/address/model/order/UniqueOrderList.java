package seedu.address.model.order;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.exceptions.DuplicateOrderException;
import seedu.address.model.order.exceptions.OrderNotFoundException;

/**
 * A list of orders supporting minimal list operations for v1.3.
 * Basic version does not support editing SalesOrders.
 */
public class UniqueOrderList implements Iterable<Order> {

    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if order is in the UniqueOrderList
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return internalList.stream().anyMatch(order::isSameOrder);
    }

    /**
     * Returns true if an order in the UniqueOrderList have the id
     */
    public boolean hasOrder(long id) {
        System.out.println(id);
        return internalList.stream().anyMatch(order -> order.getId() == id);
    }

    /**
     * Adds an order to the UniqueOrderList.
     * @param toAdd order to add
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        if (hasOrder(toAdd)) {
            throw new DuplicateOrderException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the order list.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderNotFoundException();
        }

        if (!target.equals(editedOrder) && hasOrder(editedOrder)) {
            throw new DuplicateOrderException();
        }

        internalList.set(index, editedOrder);
    }

    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        if (!(ordersAreUnique(orders))) {
            throw new DuplicateOrderException();
        }
        internalList.setAll(orders);
    }

    /**
     * Removes an order from the UniqueOrderList.
     * @param toRemove order to remove
     */
    public void remove(Order toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderNotFoundException();
        }
    }

    /**
     * Removes all orders matching predicate from the list.
     */
    public void removeIf(Predicate<Order> pred) {
        requireNonNull(pred);
        internalList.removeIf(pred);
    }

    /**
     * Marks an order as complete, if it exists in the UniqueOrderList. Returns a boolean indicating
     * whether or not an actual change has been made.
     * @param toMark
     */
    public boolean markComplete(Order toMark) {
        requireNonNull(toMark);
        if (!hasOrder(toMark)) {
            throw new OrderNotFoundException();
        }
        int index = internalList.indexOf(toMark);
        boolean hasChanged = toMark.markCompleted();
        internalList.set(index, toMark);
        return hasChanged;
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
                || (other instanceof UniqueOrderList
                && internalList.equals(((UniqueOrderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public void sort(Comparator<Order> comparator) {
        internalList.sort(comparator);
    }

    /**
     * Returns true if {@code orders} contains only unique tasks.
     */
    private boolean ordersAreUnique(List<Order> orders) {
        for (int i = 0; i < orders.size() - 1; i++) {
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(i).isSameOrder(orders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
