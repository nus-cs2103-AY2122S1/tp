package seedu.address.logic.history;

import java.util.LinkedList;

import seedu.address.commons.util.Copyable;

public class History<T extends Copyable<T>> implements Historyable<T> {
    private final LinkedList<Snapshot<T>> snapshots = new LinkedList<>();
    private int currentSnapshotIndex = 0;

    /**
     * Gets the size of the history stack.
     *
     * @return The size of the history stack.
     */
    @Override
    public int size() {
        return snapshots.size();
    }

    /**
     * Loads a String from the InputHistory, identified by its relative location.
     *
     * @param delta Relative location from current snapshot.
     * @return The specified snapshot.
     */
    @Override
    public T go(int delta) {
        int newSnapshotIndex = currentSnapshotIndex - delta;

        int minIndex = 0;
        if (newSnapshotIndex < minIndex) {
            newSnapshotIndex = minIndex;
        }

        int maxIndex = size() - 1;
        if (newSnapshotIndex > maxIndex) {
            newSnapshotIndex = maxIndex;
        }

        currentSnapshotIndex = newSnapshotIndex;
        return snapshots.get(newSnapshotIndex).getState();
    }

    /**
     * Push an object into the history stack.
     *
     * @param object Object to be added onto the history stack.
     */
    @Override
    public void push(T object) {
        Snapshot<T> newSnapshot = new Snapshot<T>(object);
        snapshots.addFirst(newSnapshot);
    }

    /**
     * Pop an object from the history stack.
     *
     * @return Object popped from the history stack.
     */
    @Override
    public T pop() {
        Snapshot<T> poppedSnapshot = snapshots.removeFirst();
        return poppedSnapshot.getState();
    }

    /**
     * Gets the current state saved in the current snapshot.
     *
     * @return The current state.
     */
    @Override
    public T getCurrentState() {
        Snapshot<T> currentSnapshot = snapshots.get(currentSnapshotIndex);
        return currentSnapshot.getState();
    }

    /**
     * Sets the current state in the current snapshot to the state in the provided object.
     *
     * @param object The provided object.
     */
    @Override
    public void setCurrentState(T object) {
        Snapshot<T> currentSnapshot = snapshots.get(currentSnapshotIndex);
        Snapshot<T> newSnapshot = currentSnapshot.edit(object);
        snapshots.set(currentSnapshotIndex, newSnapshot);
    }

    /**
     * Restores all the snapshots in the history stack to the last saved state.
     */
    @Override
    public void restore() {
        currentSnapshotIndex = 0;
        for (int index = 0; index < size(); index++) {
            Snapshot<T> snapshot = snapshots.get(index);
            Snapshot<T> restoredSnapshot = snapshot.restore();
            snapshots.set(index, restoredSnapshot);
        }
    }

}
