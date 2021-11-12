package seedu.teachbook.model;

import static seedu.teachbook.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.teachbook.commons.core.index.DefaultIndices;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.student.Student;

/**
 * @author yulonglim-reused
 * <p>
 * Code is adapted from AB4 which has implemented its own undo and redo function.
 */
public class VersionedTeachBook extends TeachBook {
    private final List<TeachbookDataState> teachBookStateList;
    private int currentStatePointer;

    /**
     * Constructor method for the VersionTeachBook
     *
     * @param initialState state during first launch of the application
     */
    public VersionedTeachBook(ReadOnlyTeachBook initialState) {
        super(initialState);

        teachBookStateList = new ArrayList<>();
        TeachbookDisplayState displaySettings;
        if (initialState.getClassList().size() >= 1) {
            displaySettings =
                    new TeachbookDisplayState(DefaultIndices.INDEX_DEFAULT_INITIAL_CLASS, PREDICATE_SHOW_ALL_STUDENTS);
        } else {
            displaySettings = new TeachbookDisplayState(DefaultIndices.INDEX_NO_CLASS, PREDICATE_SHOW_ALL_STUDENTS);
        }
        TeachbookDataState teachBookState =
                new TeachbookDataState(displaySettings, new TeachBook(initialState));
        teachBookStateList.add(teachBookState);
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code TeachBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit(GeneralIndex index, Predicate<Student> predicate) {
        removeStatesAfterCurrentPointer();
        TeachbookDisplayState displaySettings = new TeachbookDisplayState(index, predicate);
        TeachbookDataState teachBookState =
                new TeachbookDataState(displaySettings, new TeachBook(this));
        if (!teachBookState.equals(teachBookStateList.get(currentStatePointer))) {
            teachBookStateList.add(teachBookState);
            currentStatePointer++;
        }
    }

    private void removeStatesAfterCurrentPointer() {
        teachBookStateList.subList(currentStatePointer + 1, teachBookStateList.size()).clear();
    }

    /**
     * Restores the teachbook to its previous state.
     */
    public TeachbookDisplayState undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(teachBookStateList.get(currentStatePointer).getTeachBook());
        return teachBookStateList.get(currentStatePointer).getDisplayState();
    }

    /**
     * Restores the teachbook to its previously undone state.
     */
    public TeachbookDisplayState redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(teachBookStateList.get(currentStatePointer).getTeachBook());
        return teachBookStateList.get(currentStatePointer).getDisplayState();
    }

    /**
     * Returns true if {@code undo()} has teachbook states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has teachbook states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < teachBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedTeachBook)) {
            return false;
        }

        VersionedTeachBook otherVersionedTeachBook = (VersionedTeachBook) other;

        // state check
        return super.equals(otherVersionedTeachBook)
                && teachBookStateList.equals(otherVersionedTeachBook.teachBookStateList)
                && currentStatePointer == otherVersionedTeachBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of TeachBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of TeachBookState list, unable to redo.");
        }
    }
}
