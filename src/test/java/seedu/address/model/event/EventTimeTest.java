package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTimeTest {

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
}
