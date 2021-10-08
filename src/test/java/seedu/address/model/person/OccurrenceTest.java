package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OccurrenceTest {
    @Test
    public void constructor_invalidOccurrence_throwsIllegalArgumentException() {
        int invalidOccurrence = -1;
        assertThrows(IllegalArgumentException.class, () -> new Occurrence(invalidOccurrence));
    }

    @Test
    public void getNext_validOccurrence_success() {
        Occurrence test = new Occurrence(3);
        Occurrence expected = new Occurrence(2);
        assertEquals(expected, test.getNext());
    }

    @Test
    public void isValidOccurrence() {
        // invalid occurrence
        assertFalse(Occurrence.isValidOccurrence(-1));
        assertFalse(Occurrence.isValidOccurrence(0));

        // valid occurrence
        assertTrue(Occurrence.isValidOccurrence(1));
    }
}
