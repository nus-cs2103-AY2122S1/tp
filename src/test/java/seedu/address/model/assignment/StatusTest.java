package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void equals() {
        Status completedStatus = Status.createCompletedStatus();
        Status pendingStatus = Status.createPendingStatus();

        // check null -> returns false
        assertFalse(completedStatus.equals(null));
        assertFalse(pendingStatus.equals(null));

        // check same status -> returns true
        assertTrue(completedStatus.equals(completedStatus));
        assertTrue(pendingStatus.equals(pendingStatus));

        // check different status -> return false
        assertFalse(completedStatus.equals(pendingStatus));
    }

    @Test
    public void compareTo() {
        Status completedStatus = Status.createCompletedStatus();
        Status pendingStatus = Status.createPendingStatus();

        // comparing status: completed status
        assertTrue(completedStatus.compareTo(completedStatus) == 0); // equal weight as completed status
        assertTrue(completedStatus.compareTo(pendingStatus) > 0); // more weight than pending status

        // comparing status: pending status
        assertTrue(pendingStatus.compareTo(completedStatus) < 0); // less weight than completed status
        assertTrue(pendingStatus.compareTo(pendingStatus) == 0); // equal weight as pending status
    }
}
