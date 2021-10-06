package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.unify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid times
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("1:30")); // hour not in HH
        assertFalse(Time.isValidTime("time")); // non-numeric
        assertFalse(Time.isValidTime("1215")); // missing colon
        assertFalse(Time.isValidTime("24:30")); // hour not in range
        assertFalse(Time.isValidTime("23:60")); // minute not in range

        // valid times
        assertTrue(Time.isValidTime("00:00")); // first minute of day
        assertTrue(Time.isValidTime("23:59")); // last minute of day
        assertTrue(Time.isValidTime("09:30"));
    }
}
