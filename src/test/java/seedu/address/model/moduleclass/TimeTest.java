package seedu.address.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void isValidTime() {
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        assertFalse(Time.isValidTime("24:12")); //out of range
        assertFalse(Time.isValidTime("23.12")); //invalid format
        assertFalse(Time.isValidTime("13:0")); //invalid format
        assertFalse(Time.isValidTime("15:61")); //out of range
        assertFalse(Time.isValidTime("24:00")); //out of range

        assertTrue(Time.isValidTime("15:00"));
        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime("12:31"));
        assertTrue(Time.isValidTime("23:59"));
    }
}
