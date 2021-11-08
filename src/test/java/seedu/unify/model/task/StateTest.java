package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.TypicalTasks.CS2101_DEMO_DONE;
import static seedu.unify.testutil.TypicalTasks.MATH_ASSIGNMENT;

import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    public void isMarkedDone() {
        State doneState = CS2101_DEMO_DONE.getState();
        State todoState = MATH_ASSIGNMENT.getState();

        assertTrue(doneState.isMarkedDone());
        assertFalse(todoState.isMarkedDone());

    }

    @Test
    public void equals() {
        State stateTodo = new State("TODO");
        State stateDone = new State("DONE");

        // same values -> returns true
        State stateCopy = new State("TODO");
        assertTrue(stateTodo.equals(stateCopy));

        // same object -> returns true
        assertTrue(stateTodo.equals(stateTodo));

        // null -> returns false
        assertFalse(stateTodo.equals(null));

        // different type -> returns false
        assertFalse(stateTodo.equals(3));

        //different values -> returns false
        assertFalse(stateTodo.equals(stateDone));
    }
}
