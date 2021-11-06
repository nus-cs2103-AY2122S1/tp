package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void isValidEventDate() {
        // null dates
        assertThrows(NullPointerException.class, () -> new EventDate(null));

        // invalid dates
        assertFalse(EventDate.isValidEventDate(""));

        // valid dates
        assertTrue(EventDate.isValidEventDate("21-10-2021"));
        assertTrue(EventDate.isValidEventDate("10.10.2021"));
        assertTrue(EventDate.isValidEventDate("10/10/2021"));
    }

    @Test
    public void isPast() {
        EventDate past = new EventDate("01-01-2021");
        assertTrue(past.isPast());

        EventDate past2 = new EventDate("10-12-2000");
        assertTrue(past2.isPast());
    }

    @Test
    public void checkCompareTo() {
        EventDate e1 = new EventDate("01-10-2020");
        EventDate e2 = new EventDate("03-10-2020");
        EventDate e3 = new EventDate("01-10-2020");

        assertEquals(e1.compareTo(e2), -2);
        assertEquals(e2.compareTo(e1), 2);
        assertEquals(e3.compareTo(e1), 0);
    }
}
