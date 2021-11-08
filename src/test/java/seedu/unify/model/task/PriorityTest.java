package seedu.unify.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.Assert.assertThrows;

public class PriorityTest {

    @Test
    public void isValidPriority() {
        // null priority level
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority levels
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("9011p041")); // alphabets within digits

        // valid priority levels
        assertTrue(Priority.isValidPriority("LOW")); // proper
        assertTrue(Priority.isValidPriority("HIGH"));

    }

    @Test
    public void equals() {
        Priority priorityLow = new Priority("LOW");
        Priority priorityHigh = new Priority("HIGH");

        // same values -> returns true
        Priority priorityCopy = new Priority();
        assertTrue(priorityLow.equals(priorityCopy));

        // same object -> returns true
        assertTrue(priorityLow.equals(priorityLow));

        // null -> returns false
        assertFalse(priorityLow.equals(null));

        // different type -> returns false
        assertFalse(priorityLow.equals(3));

        //different values -> returns false
        assertFalse(priorityLow.equals(priorityHigh));
    }

    @Test
    public void compareTo() {
        Priority priorityLow = new Priority("LOW");
        Priority priorityHigh = new Priority("HIGH");

        // the difference in priority levels
        assertEquals(priorityLow.compareTo(priorityHigh),-2);
        assertEquals(priorityHigh.compareTo(priorityLow), 2);

        // same priorities
        assertEquals(priorityLow.compareTo(priorityLow),0);
    }
}
