package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the Completion entity.
 */
public class CompletionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Completion(null));
    }

    @Test
    public void constructor_invalidCompletion_throwsIllegalArgumentException() {
        String invalidCompletion = "done";
        assertThrows(IllegalArgumentException.class, () -> new Completion(invalidCompletion));
    }

    @Test
    public void isValidCompletion() {
        // null completion
        assertThrows(NullPointerException.class, () -> Completion.isValidCompletion(null));

        // invalid completion
        assertFalse(Completion.isValidCompletion("")); // empty string
        assertFalse(Completion.isValidCompletion("       ")); // spaces only
        assertFalse(Completion.isValidCompletion("done")); // not a valid completion value
        assertFalse(Completion.isValidCompletion("coMpLEting")); // not a valid completion value

        // valid completion
        assertTrue(Completion.isValidCompletion("Completed"));
        assertTrue(Completion.isValidCompletion("Uncompleted"));
    }

    @Test
    public void toDisplayString() {
        // completed
        assertEquals("✔", new Completion("Completed").toDisplayString());

        // uncompleted
        assertEquals("❌", new Completion("Uncompleted").toDisplayString());
    }

    @Test
    public void equals() {
        Completion c = new Completion("Completed");

        // same object -> return true
        assertTrue(c.equals(c));

        // null -> return false
        assertFalse(c.equals(null));

        // different value -> return false
        assertFalse(c.equals(new Completion("Uncompleted")));

        // same value -> return true
        assertTrue(c.equals(new Completion("Completed")));
    }

    @Test
    public void toHashCode_success() {
        String completedString = "Uncompleted";
        assertEquals(completedString.hashCode(), AMAZON.getCompletion().hashCode());
    }
}
