package seedu.address.logic.history;

import seedu.address.commons.util.Copyable;

/**
 * History allows the manipulation of previously saved snapshots of a class.
 * Solution below adapted from https://developer.mozilla.org/en-US/docs/Web/API/History.
 *
 * @param <T> The class to be saved.
 */
public interface Historyable<T extends Copyable<T>> {
    /**
     * Gets the size of the history stack.
     *
     * @return The size of the history stack.
     */
    int size();

    /**
     * Loads a snapshot from the History, identified by its relative location.
     *
     * @param delta Relative location from current snapshot.
     * @return The specified snapshot.
     */
    T go(int delta);

    /**
     * Retrieves the previous snapshot in the History.
     * Calling this method to go back beyond the first snapshot in the History will return the first snapshot
     * in the History stack.
     *
     * @return The previous snapshot.
     */
    default T back() {
        return go(-1);
    }

    /**
     * Retrieves the next snapshot in the History.
     * Calling this method to go back beyond the most recent snapshot in the History will return the most
     * recent snapshot in the History stack.
     *
     * @return The next snapshot.
     */
    default T forward() {
        return go(1);
    }

    /**
     * Push an object into the history stack.
     *
     * @param object Object to be added onto the history stack.
     */
    void push(T object);

    /**
     * Pop an object from the history stack.
     *
     * @return Object popped from the history stack.
     */
    T pop();

    /**
     * Gets the current state saved in the current snapshot.
     *
     * @return The current state.
     */
    T getCurrentState();

    /**
     * Sets the current state in the current snapshot to the state in the provided object.
     *
     * @param object The provided object.
     */
    void setCurrentState(T object);

    /**
     * Restores all the snapshots in the history stack to the last saved state.
     */
    void restore();
}
