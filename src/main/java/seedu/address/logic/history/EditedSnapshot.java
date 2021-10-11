package seedu.address.logic.history;

import seedu.address.commons.util.Copyable;

public class EditedSnapshot<T extends Copyable<T>> extends Snapshot<T> {
    private T currentObject;

    /**
     * Stores the object to be saved and the object after changes have been made into an EditedSnapshot.
     *
     * @param savedObject The object to be saved.
     * @param editedObject The object after changes have been made.
     */
    protected EditedSnapshot(T savedObject, T editedObject) {
        super(savedObject.copy());
        currentObject = editedObject.copy();
    }

    /**
     * Gets a copy of the current edited object stored in the snapshot.
     *
     * @return The copy of the edited object stored in the snapshot.
     */
    @Override
    public T getState() {
        return currentObject.copy();
    }

    /**
     * Replaces the object in the snapshot with a copy of a new instance.
     *
     * @param object The new object.
     */
    @Override
    public Snapshot<T> edit(T object) {
        currentObject = object.copy();
        return this;
    }
}
