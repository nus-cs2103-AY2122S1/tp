package seedu.address.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.UndoableCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


//Solution adapted from
//https://github.com/CS2103JAN2018-W13-B1/main/blob/master/src/test/java/seedu/address/logic/UndoRedoStackTest.java
class UndoRedoStackTest {
    private DummyCommand dummyCommand = new DummyCommand();
    private DummyUndoableCommand dummyUndoableCommandOne = new DummyUndoableCommand();
    private DummyUndoableCommand dummyUndoableCommandTwo = new DummyUndoableCommand();

    private UndoRedoStack undoRedoStack = new UndoRedoStack();

    @Test
    public void pushUndoableCommand_CommandNotAdded() {
        //push on empty undo & redo stack
        undoRedoStack = prepareStack(Collections.emptyList(), Collections.emptyList());
        undoRedoStack.pushUndoableCommand(dummyCommand);
        assertStackStatus(Collections.emptyList(), Collections.emptyList());
        //push on non-empty undo stack
        undoRedoStack = prepareStack(Collections.singletonList(dummyUndoableCommandOne), Collections.emptyList());
        undoRedoStack.pushUndoableCommand(dummyCommand);
        assertStackStatus(Collections.singletonList(dummyUndoableCommandOne), Collections.emptyList());
    }

    @Test
    public void pushUndoableCommand_CommandAdded() {
        //push on empty undo & redo stack
        undoRedoStack = prepareStack(Collections.emptyList(), Collections.emptyList());
        undoRedoStack.pushUndoableCommand(dummyUndoableCommandOne);
        assertStackStatus(Collections.singletonList(dummyUndoableCommandOne), Collections.emptyList());
        //push on non-empty undo stack
        undoRedoStack = prepareStack(Collections.singletonList(dummyUndoableCommandOne), Collections.emptyList());
        undoRedoStack.pushUndoableCommand(dummyUndoableCommandTwo);
        assertStackStatus(Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo), Collections.emptyList());
    }

    @Test
    public void canUndo() {
        //empty undo stack
        undoRedoStack = prepareStack(Collections.emptyList(), Collections.emptyList());
        assertFalse(undoRedoStack.canUndo());

        //non-empty undo stack
        undoRedoStack = prepareStack(Collections.singletonList(dummyUndoableCommandOne), Collections.emptyList());
        assertTrue(undoRedoStack.canUndo());
    }

    @Test
    public void popUndo() {
        undoRedoStack = prepareStack(Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo),
                Collections.emptyList());

        //multiple commands in undo stack
        assertPopUndoSuccess(dummyUndoableCommandTwo, Collections.singletonList(dummyUndoableCommandOne),
                Collections.singletonList(dummyUndoableCommandTwo));

        //single command in undo stack
        assertPopUndoSuccess(dummyUndoableCommandOne, Collections.emptyList(),
                Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne));

        //no commands in undo stack
        assertPopUndoFailure(Collections.emptyList(), Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne));
    }

    @Test
    public void canRedo() {
        //empty redo stack
        undoRedoStack = prepareStack(Collections.emptyList(), Collections.emptyList());
        assertFalse(undoRedoStack.canRedo());

        //non-empty redo stack
        undoRedoStack = prepareStack(Collections.emptyList(), Collections.singletonList(dummyUndoableCommandOne));
        assertTrue(undoRedoStack.canRedo());
    }

    @Test
    public void popRedo() {
        undoRedoStack = prepareStack(Collections.emptyList(),
                Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo));

        //multiple commands in redo stack
        assertPopRedoSuccess(dummyUndoableCommandTwo, Collections.singletonList(dummyUndoableCommandTwo),
                Collections.singletonList(dummyUndoableCommandOne));

        //single command in redo stack
        assertPopRedoSuccess(dummyUndoableCommandOne, Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne),
                Collections.emptyList());

        //no commands in redo stack
        assertPopRedoFailure(Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne), Collections.emptyList());
    }

    @Test
    public void equals() {
        undoRedoStack = prepareStack(Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne),
                Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo));

        // same values -> returns true
        UndoRedoStack copy = prepareStack(Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne),
                Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo));
        assertTrue(undoRedoStack.equals(copy));

        // same object -> returns true
        assertTrue(undoRedoStack.equals(undoRedoStack));

        // null -> returns false
        assertFalse(undoRedoStack.equals(null));

        // different types -> returns false
        assertFalse(undoRedoStack.equals(1));

        // different undoStack -> returns false
        UndoRedoStack differentUndoStack = prepareStack(Collections.singletonList(dummyUndoableCommandTwo),
                Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo));
        assertFalse(undoRedoStack.equals(differentUndoStack));

        // different redoStack -> returns false
        UndoRedoStack differentRedoStack =
                prepareStack(Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne),
                        Collections.singletonList(dummyUndoableCommandTwo));
        assertFalse(undoRedoStack.equals(differentRedoStack));
    }

    /**
     * Asserts that the result of {@code undoRedoStack#popUndo()} equals {@code expectedCommand}.
     * Also asserts that the content of the {@code undoRedoStack#undoStack} equals {@code undoElements},
     * and {@code undoRedoStack#redoStack} equals {@code redoElements}.
     */
    private void assertPopUndoSuccess(UndoableCommand expectedCommand, List<UndoableCommand> expectedUndoElements,
                                      List<UndoableCommand> expectedRedoElements) {
        assertEquals(expectedCommand, undoRedoStack.popUndo());
        assertStackStatus(expectedUndoElements, expectedRedoElements);
    }

    /**
     * Asserts that the execution of {@code undoRedoStack#popUndo()} fails and {@code EmptyStackException} is thrown.
     * Also asserts that the content of the {@code undoRedoStack#undoStack} equals {@code undoElements},
     * and {@code undoRedoStack#redoStack} equals {@code redoElements}.
     */
    private void assertPopUndoFailure(List<UndoableCommand> expectedUndoElements,
                                      List<UndoableCommand> expectedRedoElements) {
        try {
            undoRedoStack.popUndo();
            fail("The expected EmptyStackException was not thrown.");
        } catch (EmptyStackException ese) {
            assertStackStatus(expectedUndoElements, expectedRedoElements);
        }
    }

    /**
     * Asserts that the result of {@code undoRedoStack#popRedo()} equals {@code expectedCommand}.
     * Also asserts that the content of the {@code undoRedoStack#undoStack} equals {@code undoElements},
     * and {@code undoRedoStack#redoStack} equals {@code redoElements}.
     */
    private void assertPopRedoSuccess(UndoableCommand expectedCommand, List<UndoableCommand> expectedUndoElements,
                                      List<UndoableCommand> expectedRedoElements) {
        assertEquals(expectedCommand, undoRedoStack.popRedo());
        assertStackStatus(expectedUndoElements, expectedRedoElements);
    }

    /**
     * Asserts that the execution of {@code undoRedoStack#popRedo()} fails and {@code EmptyStackException} is thrown.
     * Also asserts that the content of the {@code undoRedoStack#undoStack} equals {@code undoElements},
     * and {@code undoRedoStack#redoStack} equals {@code redoElements}.
     */
    private void assertPopRedoFailure(List<UndoableCommand> expectedUndoElements,
                                      List<UndoableCommand> expectedRedoElements) {
        try {
            undoRedoStack.popRedo();
            fail("The expected EmptyStackException was not thrown.");
        } catch (EmptyStackException ese) {
            assertStackStatus(expectedUndoElements, expectedRedoElements);
        }
    }


    /**
     * Asserts that {@code undoRedoStack#undoStack} equals {@code undoElements}, and {@code undoRedoStack#redoStack}
     * equals {@code redoElements}.
     */
    private void assertStackStatus(List<UndoableCommand> undoElements, List<UndoableCommand> redoElements) {
        assertEquals(prepareStack(undoElements, redoElements), undoRedoStack);
    }

    /**
     * Prepare Undo Redo stack with required undo and redo commands.
     *
     * @param undoElements List of undo commands in desired sequence.
     * @param redoElements List of redo commands in desired redo sequence.
     * @return prepared UndoRedoStack.
     */
    private UndoRedoStack prepareStack(List<UndoableCommand> undoElements,
           List<UndoableCommand> redoElements) {
        UndoRedoStack stack = new UndoRedoStack();
        undoElements.forEach(stack :: pushUndoableCommand);

        Collections.reverse(redoElements);
        redoElements.forEach(stack :: pushUndoableCommand);
        redoElements.forEach(toRedo -> stack.popUndo());

        return stack;
    }

    private class DummyCommand extends Command {
        @Override
        public CommandResult execute() {
            return new CommandResult("");
        }
    }

    private class DummyUndoableCommand extends UndoableCommand {
        @Override
        public CommandResult executeUndoableCommand() {
            return new CommandResult("");
        }
        @Override
        protected void undo() {}
        @Override
        protected void redo() {}
    }
}
