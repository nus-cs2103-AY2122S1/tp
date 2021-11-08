package nurseybook.model;

import java.util.ArrayList;
import java.util.List;

import nurseybook.logic.commands.CommandResult;

// Solution below adapted from https://github.com/se-edu/addressbook-level4
/**
 * Tracks the state of the nurseybook
 */
public class VersionedNurseyBook extends NurseyBook {

    private static final CommandResult INITIAL_COMMAND_RESULT = new CommandResult("Initialise NurseyBook");
    private List<NurseyBookState> nurseyBookStateList;
    private int currentStateIndex;

    /**
     * Creates a VersionedNurseyBook using the data in the {@code initialNurseyBook}.
     */
    public VersionedNurseyBook(ReadOnlyNurseyBook initialNurseyBook) {
        super(initialNurseyBook);
        nurseyBookStateList = new ArrayList<>();
        nurseyBookStateList.add(new NurseyBookState(new NurseyBook(initialNurseyBook),
                INITIAL_COMMAND_RESULT));
        currentStateIndex = 0;
    }

    /**
     * Saves a new NurseyBookState with a copy of this {@code VersionedNurseyBook} and {@code commandResult}
     * and increases the {@code currentStateIndex}.
     */
    public void commit(CommandResult commandResult) {
        deleteUndoneStates();
        nurseyBookStateList.add(new NurseyBookState(new NurseyBook(this), commandResult));
        currentStateIndex++;
    }

    /**
     * Decreases the {@code currentStateIndex} and resets data in this {@code VersionedNurseyBook}
     * to the version saved in the previous {@code NurseyBookState} based on the {@code currentStateIndex}.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStateIndex--;
        resetData(nurseyBookStateList.get(currentStateIndex).getNurseyBook());
    }

    /**
     * Returns true if there is a previous {@code NurseyBookState} saved.
     */
    public boolean canUndo() {
        return currentStateIndex > 0;
    }

    /**
     * Returns the {@code CommandResult} saved in the {@code NurseyBookState}
     * at the {@code currentStateIndex}.
     */
    public CommandResult getCurrentCommandResult() {
        return nurseyBookStateList.get(currentStateIndex).getCommandResult();
    }

    private void deleteUndoneStates() {
        nurseyBookStateList.subList(currentStateIndex + 1, nurseyBookStateList.size()).clear();
    }

    /**
     * Returns true if there is a next {@code NurseyBookState} saved.
     */
    public boolean canRedo() {
        return currentStateIndex < nurseyBookStateList.size() - 1;
    }

    /**
     * Increases the {@code currentStateIndex} and resets data in this {@code VersionedNurseyBook}
     * to the version saved in the next {@code NurseyBookState} based on the {@code currentStateIndex}.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStateIndex++;
        resetData(nurseyBookStateList.get(currentStateIndex).getNurseyBook());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        if (!(other instanceof VersionedNurseyBook)) {
            return false;
        }

        VersionedNurseyBook otherVersionedNurseyBook = (VersionedNurseyBook) other;
        return super.equals(otherVersionedNurseyBook)
                && nurseyBookStateList.equals(otherVersionedNurseyBook.nurseyBookStateList)
                && currentStateIndex == otherVersionedNurseyBook.currentStateIndex;
    }

    /**
     * Thrown when trying to {@code undo()} but there is no previous state.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state index is at the initial state, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but there is no next state.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state index is at the most recent state, unable to redo.");
        }
    }
}
