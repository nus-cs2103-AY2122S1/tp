package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        // empty string
        assertThrows(IllegalArgumentException.class, () -> new Time(""));
        // more than 4 digits
        assertThrows(IllegalArgumentException.class, () -> new Time("11111"));
        // not digits
        assertThrows(IllegalArgumentException.class, () -> new Time("@#$"));
        // invalid time format
        assertThrows(IllegalArgumentException.class, () -> new Time("2400"));
        assertThrows(IllegalArgumentException.class, () -> new Time("2360"));
        assertThrows(IllegalArgumentException.class, () -> new Time("11:00"));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // valid time
        assertTrue(Time.isValidTime("0000"));
        assertTrue(Time.isValidTime("2359"));
        assertTrue(Time.isValidTime("1234"));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime("11:00")); // invalid format
        assertFalse(Time.isValidTime("#$%%")); // not digits
    }

    @Test
    public void equals() {
        Time time = new Time("1130");
        Time timeCopy = new Time("1130");

        assertTrue(time.equals(timeCopy));
        assertTrue(time.equals(time));

        assertFalse(time.equals(null));
        Time differentTime = new Time("1520");
        assertFalse(time.equals(differentTime));

    }
}
