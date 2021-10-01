package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VisitTest {

    @Test
    public void equals() {
        Visit visit = new Visit("Hello");

        // same object -> returns true
        assertTrue(visit.equals(visit));

        // same values -> returns true
        Visit remarkCopy = new Visit(visit.value);
        assertTrue(visit.equals(remarkCopy));

        // different types -> returns false
        assertFalse(visit.equals(1));

        // null -> returns false
        assertFalse(visit.equals(null));

        // different visit -> returns false
        Visit differentVisit = new Visit("Bye");
        assertFalse(visit.equals(differentVisit));
    }
}
