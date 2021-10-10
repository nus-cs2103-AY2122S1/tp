package seedu.address.logic;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UndoableCommand;

import java.util.Stack;

/**
 * Keeps track of Commands that have been called (undoStack)
 * and Commands that have been undone (redoStack)
 */
public class UndoRedoStack {
    private Stack<UndoableCommand> undoStack;
    private Stack<UndoableCommand> redoStack;

    public UndoRedoStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Add Command to Undo Stack if command is undoable
     * @param command Command executed
     */
    public void pushUndoableCommand(Command command) {
        if (!(command instanceof UndoableCommand)) {
            return;
        }

        undoStack.push((UndoableCommand) command);
    }

    /**
     * @return if there are commands to be undone
     */
    public boolean canUndo() {
        return !undoStack.empty();
    }

    public UndoableCommand popUndo() {
        UndoableCommand toUndo = undoStack.pop();
        redoStack.push(toUndo);
        return toUndo;
    }
}
