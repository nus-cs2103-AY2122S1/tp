package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.getTypicalApplications;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the Priority entity.
 */
public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "higher";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("prioritised")); // not a valid priority value
        assertFalse(Priority.isValidPriority("HiGher")); // not a valid priority value

        // valid priority
        assertTrue(Priority.isValidPriority("High")); // only first letter upper case
        assertTrue(Priority.isValidPriority("low")); // all lower case
        assertTrue(Priority.isValidPriority("MEDIUM")); // all upper case
        assertTrue(Priority.isValidPriority("HiGh")); // mix of lower and upper case
    }

    @Test
    public void toDisplayString() {
        // low priority
        assertEquals("❗", new Priority("low").toDisplayString());

        // medium priority
        assertEquals("❗❗", new Priority("medium").toDisplayString());

        // high priority
        assertEquals("❗❗❗", new Priority("high").toDisplayString());
    }

    @Test
    public void equals() {
        Priority p = new Priority("low");

        // same object -> return true
        assertTrue(p.equals(p));

        // null -> return false
        assertFalse(p.equals(null));

        // different value -> return false
        assertFalse(p.equals(new Priority("medium")));

        // same value -> return true
        assertTrue(p.equals(new Priority("low")));
    }

    @Test
    public void getComparator_success() {
        List<Application> applicationList = getTypicalApplications();
        applicationList.sort(Priority.getComparator());
    }

    @Test
    public void toHashCode_success() {
        String priorityString = "Medium";
        assertEquals(priorityString.hashCode(), AMAZON.getPriority().hashCode());
    }

}
