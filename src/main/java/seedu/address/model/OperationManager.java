package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Stack;

import seedu.address.model.exceptions.OperationException;

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
    int redo() throws OperationException {
        if (!canRedo()) {
            throw new OperationException("Unable to redo");
        }
        Operation op = redoStack.pop();
        op.redo();
        undoStack.push(op);
        return redoStack.size();
    }

    /**
     * Reverses the latest operation executed
     * @throws OperationException when there are no operations to be undid
     */
    int undo() throws OperationException {
        if (!canUndo()) {
            throw new OperationException("Unable to undo");
        }
        Operation op = undoStack.pop();
        op.undo();
        redoStack.push(op);
        return undoStack.size();
    }

    /**
     * Runs an operation for the first time, taking a snapshot of Model.
     * @param redo operation to be run
     */
    void run(Runnable redo) {
        requireNonNull(redo);

        Operation op = new Operation(redo, model.getState());
        op.redo();

        undoStack.push(op);
        redoStack.clear();
    }

    /**
     * Register an operation without running it. Used for operations which have already
     * been executed but still requires storing for undo/redo
     * @param redo operation to be run
     * @param beforeState state of model before op was executed
     */
    void registerOperation(Runnable redo, ModelManagerState beforeState) {
        requireAllNonNull(redo, beforeState);

        Operation op = new Operation(redo, beforeState);

        undoStack.push(op);
        redoStack.clear();
    }

    /**
     * Represents a single reversible operation on Model that modifies data.
     */
    private class Operation {
        private final Runnable redo;
        private final ModelManagerState modelManagerState;

        Operation(Runnable redo, ModelManagerState modelManagerState) {
            requireAllNonNull(redo, modelManagerState);
            this.redo = redo;
            this.modelManagerState = modelManagerState;
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
        public void undo() {
            requireNonNull(model);
            model.restoreState(modelManagerState);
        }
    }
}
