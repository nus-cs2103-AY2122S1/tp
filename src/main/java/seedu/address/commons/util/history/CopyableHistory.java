package seedu.address.commons.util.history;

import static java.util.Objects.requireNonNull;

import java.util.NoSuchElementException;

import seedu.address.commons.util.Copyable;

/**
 * CloneableHistory represents the History of objects of a Cloneable class.
 *
 * @param <T> The class of the objects stored in this history.
 */
public class CopyableHistory<T extends Copyable<T>> extends BaseHistory<T> {
    /**
     * Creates a CopyableHistory object with the specified capacity.
     *
     * @param capacity The specified capacity.
     */
    public CopyableHistory(int capacity) {
        super(capacity);
    }

    /**
     * Creates a CopyableHistory object with the default capacity.
     */
    public CopyableHistory() {
        super();
    }

    /**
     * Adds an object into the history.
     *
     * @param object The object to be added.
     */
    @Override
    public void add(T object) {
        requireNonNull(object);
        super.add(object.copy());
    }

    /**
     * Gets the object stored in the History at the specified index.
     *
     * @param index Index of object in History.
     * @return Object stored in History at the index.
     * @throws NoSuchElementException If there is no object stored at the index.
     */
    @Override
    public T get(int index) throws NoSuchElementException {
        return super.get(index).copy();
    }
}
