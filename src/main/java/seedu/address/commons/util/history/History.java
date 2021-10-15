package seedu.address.commons.util.history;

import java.util.NoSuchElementException;

/**
 * History is a collection of saved objects of a class.
 *
 * @param <T> The class to be saved.
 */
public interface History<T> {
    /**
     * Adds an object into the history.
     *
     * @param object The object to be added.
     */
    void add(T object);

    /**
     * Gets the object stored in the History at the specified index.
     *
     * @param index Index of object in History.
     * @return Object stored in History at the index.
     * @throws NoSuchElementException If there is no object stored at the index.
     */
    T get(int index) throws NoSuchElementException;

    /**
     * Gets the current size of the history.
     *
     * @return Current size of the history.
     */
    int size();
}
