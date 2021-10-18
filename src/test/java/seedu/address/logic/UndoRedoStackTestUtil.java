package seedu.address.logic;

import seedu.address.logic.commands.UndoableCommand;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoRedoStackTestUtil {
    /**
     * Asserts that {@code undoRedoStack#undoStack} equals {@code undoElements}, and {@code undoRedoStack#redoStack}
     * equals {@code redoElements}.
     */
    public static void assertStackStatus(List<UndoableCommand> undoElements, List<UndoableCommand> redoElements
            , UndoRedoStack undoRedoStack) {
        assertEquals(prepareStack(undoElements, redoElements), undoRedoStack);
    }

    /**
     * Prepare Undo Redo stack with required undo and redo commands.
     *
     * @param undoElements List of undo commands in desired sequence.
     * @param redoElements List of redo commands in desired redo sequence.
     * @return prepared UndoRedoStack.
     */
    public static UndoRedoStack prepareStack(List<UndoableCommand> undoElements,
                                       List<UndoableCommand> redoElements) {
        UndoRedoStack stack = new UndoRedoStack();
        undoElements.forEach(stack :: pushUndoableCommand);

        Collections.reverse(redoElements); //1, 2 -> 2, 1
        redoElements.forEach(stack :: pushUndoableCommand); //in undo stack, [2,1]
        redoElements.forEach(toRedo -> stack.popUndo()); //redo stack, [1, 2]

        return stack;
    }
}
