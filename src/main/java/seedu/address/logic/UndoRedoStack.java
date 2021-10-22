package seedu.address.logic;

import java.util.Stack;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UndoableCommand;

//@@author Zhiyuan-Amos-reused
// Reused code from
// https://github.com/nus-cs2103-AY1718S2/addressbook-level4/
// blob/master/src/main/java/seedu/address/logic/UndoRedoStack.java
// with minor modifications

/**
 * Keeps track of Commands that have been called (undoStack)
 * and Commands that have been undone (redoStack).
 */
public class UndoRedoStack {
    private Stack<UndoableCommand> undoStack;
    private Stack<UndoableCommand> redoStack;

    /**
     * Constructor for UndoRedoStack to store Undoable commands.
     */
    public UndoRedoStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Adds Command to Undo Stack if command is undoable.
     *
     * @param command Command executed.
     */
    public void pushUndoableCommand(Command command) {
        if (!(command instanceof UndoableCommand)) {
            return;
        }

        undoStack.push((UndoableCommand) command);
    }

    /**
     * Returns true if there are commands to be undone.
     *
     * @return True if there are commands to be undone.
     */
    public boolean canUndo() {
        return !undoStack.empty();
    }

    /**
     * Gets the last executed UndoableCommand.
     *
     * @return Last executed UndoableCommand.
     */
    public UndoableCommand popUndo() {
        UndoableCommand toUndo = undoStack.pop();
        redoStack.push(toUndo);
        return toUndo;
    }

    /**
     * Checks if there are any commands that can be redone.

     * @return True if there are commands to be redone.
     */
    public boolean canRedo() {
        return !redoStack.empty();
    }

    /**
     * Moves the Command that has been redone to the undoStack so that command can be undone again.
     *
     * @return Last UndoableCommand that has been redone.
     */
    public UndoableCommand popRedo() {
        UndoableCommand toRedo = redoStack.pop();
        undoStack.push(toRedo);
        return toRedo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UndoRedoStack)) {
            return false;
        }

        UndoRedoStack stack = (UndoRedoStack) other;

        return undoStack.equals(stack.undoStack)
                && redoStack.equals(stack.redoStack);
    }
}
//@@author Zhiyuan-Amos-reused

