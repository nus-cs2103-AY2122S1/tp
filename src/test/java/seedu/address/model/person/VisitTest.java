package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VisitTest {

    @Test
    public void equals() {
        Visit remark = new Visit("Hello");

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // same values -> returns true
        Visit remarkCopy = new Visit(remark.value);
        assertTrue(remark.equals(remarkCopy));

        // different types -> returns false
        assertFalse(remark.equals(1));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different visit -> returns false
        Visit differentVisit = new Visit("Bye");
        assertFalse(remark.equals(differentVisit));
    }
}
