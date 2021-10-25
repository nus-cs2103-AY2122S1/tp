package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Stack;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.exceptions.OperationException;
import seedu.address.model.person.Person;

/**
 * Represents the recent history of Model that allows undoing and redoing.
 */
class OperationManager {

    /**
     * Stores the recently executed operations
     */
    private final Stack<Operation> redoStack = new Stack<>();

    /**
     * Stores the recently reversed operations.
     */
    private final Stack<Operation> undoStack = new Stack<>();

    /**
     * Model to be operated on.
     */
    private final Model model;

    OperationManager(Model model) {
        this.model = model;
    }

    boolean canUndo() {
        return !undoStack.isEmpty();
    }

    boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Reruns the last operation that was reversed.
     * @throws OperationException when there are no operations to be redid
     */
    void redo() throws OperationException {
        if (!canRedo()) {
            throw new OperationException("Unable to redo");
        }
        Operation op = redoStack.pop();
        op.redo();
        undoStack.push(op);
    }

    /**
     * Reverses the latest operation executed
     * @throws OperationException when there are no operations to be undid
     */
    void undo() throws OperationException {
        if (!canUndo()) {
            throw new OperationException("Unable to undo");
        }
        Operation op = undoStack.pop();
        op.undo();
        redoStack.push(op);
    }

    /**
     * Runs an operation for the first time, taking a snapshot of Model.
     * @param redo operation to be run
     */
    void run(Runnable redo) {
        requireNonNull(redo);

        Operation op = new Operation(redo);
        op.redo();

        undoStack.push(op);
        redoStack.clear();
    }

    /**
     * Represents a single reversible operation on Model that modifies data.
     */
    private class Operation {
        private final Runnable redo;

        private final AddressBook addressBookSnapshot;
        private final UserPrefs userPrefsSnapshot;
        private final FilteredList<Person> filteredPersonsSnapshot;

        Operation(Runnable redo) {
            requireNonNull(redo);
            this.redo = redo;
            this.addressBookSnapshot = new AddressBook(model.getAddressBook());
            this.userPrefsSnapshot = new UserPrefs(model.getUserPrefs());
            this.filteredPersonsSnapshot = new FilteredList<>(this.addressBookSnapshot.getPersonList());
            this.filteredPersonsSnapshot.setPredicate((
                    (FilteredList<Person>) model.getFilteredPersonList()).getPredicate());
        }

        /**
         * Runs the operation.
         */
        public void redo() {
            redo.run();
        }

        /**
         * Reverses the operation.
         */
        @SuppressWarnings("unchecked")
        public void undo() {
            requireNonNull(model);
            model.setAddressBook(new AddressBook(addressBookSnapshot));
            model.setUserPrefs(new UserPrefs(userPrefsSnapshot));
            if (filteredPersonsSnapshot.getPredicate() != null) {
                model.updateFilteredPersonList((Predicate<Person>) filteredPersonsSnapshot.getPredicate());
            }
        }
    }
}
