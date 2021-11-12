package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void validObjectPriorityCreation_success() {
        Priority.ObjectPriority priorityHigh = Priority.ObjectPriority.HIGH;
        Priority.ObjectPriority priorityLow = Priority.ObjectPriority.LOW;

        assertTrue(priorityHigh.getValue() == 3);
        assertTrue(priorityLow.getValue() == 1);
    }

    @Test
    public void constructor_validObjectPriority_success() {
        Priority.ObjectPriority priorityLow = Priority.ObjectPriority.LOW;

        Priority priority = new Priority(priorityLow);

        assertEquals(priority.getObjectPriority(), priorityLow);

    }

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
        Priority priorityCopy = new Priority(); // Default priority is LOW
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
        assertEquals(priorityLow.compareTo(priorityHigh), -2);
        assertEquals(priorityHigh.compareTo(priorityLow), 2);

        // same priorities
        assertEquals(priorityLow.compareTo(priorityLow), 0);
    }
}
