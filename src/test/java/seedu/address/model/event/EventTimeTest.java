package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTimeTest {
    private EventTime firstEventTime = new EventTime("1000");
    private EventTime secondEventTime = new EventTime("1001");
    private EventTime defaultEventTime = new EventTime();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventTime(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new EventTime(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> EventTime.isValidTime(null));

        // invalid time
        assertFalse(EventTime.isValidTime("")); // empty string
        assertFalse(EventTime.isValidTime("9")); // wrong format
        assertFalse(EventTime.isValidTime("90")); // wrong format
        assertFalse(EventTime.isValidTime("123")); // wrong format
        assertFalse(EventTime.isValidTime("8pm")); // wrong format
        assertFalse(EventTime.isValidTime("00:00")); // wrong format
        assertFalse(EventTime.isValidTime("2500")); // no such time

        // valid time
        assertTrue(EventTime.isValidTime("0000"));
        assertTrue(EventTime.isValidTime("2359"));
    }

    @Test
    public void testEquals() {
        // same EventTime
        assertTrue(firstEventTime.equals(firstEventTime));

        // different EventTime, same time
        EventTime firstEventTimeCopy = new EventTime("1000");
        assertTrue(firstEventTime.equals(firstEventTimeCopy));

        // null -> returns false
        assertFalse(firstEventTime.equals(null));

        // different EventTime, one uses default constructor
        assertFalse(firstEventTime.equals(defaultEventTime));

        // different EventTime, different time
        assertFalse(firstEventTime.equals(secondEventTime));
    }

    @Test
    public void testCompareTo() {
        // both EventTime objects hasTime true, firstEventTime.time < secondEventTime.time
        assertTrue(firstEventTime.compareTo(secondEventTime) < 0);

        // this.hasTime true, o.hasTime false
        assertEquals(firstEventTime.compareTo(defaultEventTime), 1);

        // this.hasTime false, o.hasTime true
        assertEquals(defaultEventTime.compareTo(firstEventTime), -1);

        // both EventTime objects hasTime false
        assertEquals(defaultEventTime.compareTo(defaultEventTime), 0);
    }
}
