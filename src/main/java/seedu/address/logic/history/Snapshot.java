package seedu.address.logic.history;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.Copyable;

public class Snapshot<T extends Copyable<T>> {
    private final T object;

    protected Snapshot(T object) {
        requireNonNull(object);

        // Copy the object so that any subsequent mutations to the instance in {@code object} will not affect
        // the snapshot.
        this.object = object.copy();
    }

    /**
     * Gets a copy of the current object stored in the snapshot.
     *
     * @return The copy of the object stored in the snapshot.
     */
    public T getState() {
        return object.copy();
    }

    /**
     * Restores the object in snapshot to the last saved state.
     */
    public Snapshot<T> restore() {
        return new Snapshot<>(object);
    }

    /**
     * Replaces the object in the snapshot with a copy of a new instance.
     *
     * @param object The new object.
     */
    public Snapshot<T> edit(T object) {
        requireNonNull(object);

        return new EditedSnapshot<T>(this.object.copy(), object);
    }
}
