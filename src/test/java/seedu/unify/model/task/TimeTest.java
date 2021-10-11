package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

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
    public void isValidTime() {
        // null time number
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time numbers
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("9:1")); // less than 3 numbers
        assertFalse(Time.isValidTime("time")); // non-numeric
        assertFalse(Time.isValidTime("9011p041")); // alphabets within digits
        assertFalse(Time.isValidTime("9312 1534")); // spaces within digits
        assertFalse(Time.isValidTime("24:30")); // hour not in range
        assertFalse(Time.isValidTime("23:60")); // minute not in range

        // valid time numbers
        assertTrue(Time.isValidTime("16:40")); // proper
        assertTrue(Time.isValidTime("11:23"));
        assertTrue(Time.isValidTime("00:00"));
    }
}
