package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VisitTest {

    @Test
    public void equals() {
        Visit visit = new Visit("2021-11-11");

        // same object -> returns true
        assertTrue(visit.equals(visit));

        // same values -> returns true
        Visit visitCopy = new Visit(visit.value);
        assertTrue(visit.equals(visitCopy));

        // different types -> returns false
        assertFalse(visit.equals(1));

        // null -> returns false
        assertFalse(visit.equals(null));

        // different visit -> returns false
        Visit differentVisit = new Visit("2021-10-01");
        assertFalse(visit.equals(differentVisit));

        // non-empty visit -> returns true
        assertTrue(visit.hasVisit());

        // empty visit -> returns false
        Visit emptyVisit = new Visit("");
        assertFalse(emptyVisit.hasVisit());
    }
}
