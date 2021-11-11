package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the Status entity.
 */
public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "done";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidCompletion() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid status
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus("       ")); // spaces only
        assertFalse(Status.isValidStatus("done")); // not a valid Status value
        assertFalse(Status.isValidStatus("random")); // not a valid Status value

        // valid status
        assertTrue(Status.isValidStatus("Pending"));
        assertTrue(Status.isValidStatus("Accepted"));
        assertTrue(Status.isValidStatus("Rejected"));
    }

    @Test
    public void equals() {
        Status s = new Status("Accepted");

        // same object -> return true
        assertTrue(s.equals(s));

        // null -> return false
        assertFalse(s.equals(null));

        // different value -> return false
        assertFalse(s.equals(new Status("Rejected")));

        // same value -> return true
        assertTrue(s.equals(new Status("Accepted")));
    }

    @Test
    public void toHashCode_success() {
        String statusString = "Pending";
        assertEquals(statusString.hashCode(), AMAZON.getStatus().hashCode());
    }
}
