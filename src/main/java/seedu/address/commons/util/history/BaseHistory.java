package seedu.address.commons.util.history;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * BaseHistory represents the History of a specified Class of objects.
 * Only wrapper classes should use this implementation of History.
 * Non wrapper classes should use {@code CloneableHistory}.
 *
 * @param <T> The class of the objects stored in this history.
 */
public abstract class BaseHistory<T> implements History<T> {
    private static final int DEFAULT_CAPACITY = 100;

    private final LinkedList<T> history;
    private final int capacity;

    /**
     * Creates a BaseHistory object with the specified capacity.
     *
     * @param capacity The specified capacity.
     */
    protected BaseHistory(int capacity) {
        this.capacity = capacity;
        this.history = new LinkedList<>();
    }

    /**
     * Creates a BaseHistory object with the default capacity.
     */
    protected BaseHistory() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Adds an object into the history.
     *
     * @param object The object to be added.
     */
    @Override
    public void add(T object) {
        requireNonNull(object);
        history.addFirst(object);
        // Limit number of objects in history to the specified capacity
        while (size() > capacity) {
            history.removeLast();
        }
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
        if (index >= size()) {
            throw new NoSuchElementException();
        }

        return history.get(index);
    }

    /**
     * Gets the current size of the history.
     *
     * @return Current size of the history.
     */
    @Override
    public int size() {
        return history.size();
    }
}
