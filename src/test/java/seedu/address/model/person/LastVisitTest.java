package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LastVisitTest {
    
    @Test
    public void equals() {
        LastVisit lastVisit = new LastVisit("2021-11-11");

        // same object -> returns true
        assertTrue(lastVisit.equals(lastVisit));

        // same values -> returns true
        LastVisit visitCopy = new LastVisit(lastVisit.value);
        assertTrue(lastVisit.equals(visitCopy));

        // different types -> returns false
        assertFalse(lastVisit.equals(1));

        // null -> returns false
        assertFalse(lastVisit.equals(null));

        // different visit -> returns false
        LastVisit differentVisit = new LastVisit("2021-10-01");
        assertFalse(lastVisit.equals(differentVisit));
    }
}
