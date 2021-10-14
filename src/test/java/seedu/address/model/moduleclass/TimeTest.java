package seedu.address.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> new Time(""));
    }

    @Test
    public void invalidTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Time("15:00:15"));

        assertThrows(IllegalArgumentException.class, () -> new Time ("15:0"));
    }

    @Test
    public void isValidTime() {
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        assertFalse(Time.isValidTime("24:12")); //out of range
        assertFalse(Time.isValidTime("23.12")); //invalid format
        assertFalse(Time.isValidTime("13:0")); //invalid format
        assertFalse(Time.isValidTime("15:61")); //out of range
        assertFalse(Time.isValidTime("24:00")); //out of range
        assertFalse(Time.isValidTime("0:00")); //LocalTime cannot parse this
        assertFalse(Time.isValidTime("1:00")); //LocalTime cannot parse this
        assertFalse(Time.isValidTime("101:00"));

        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime("01:00"));
        assertTrue(Time.isValidTime("15:00"));
        assertTrue(Time.isValidTime("12:31"));
        assertTrue(Time.isValidTime("23:59"));
        assertTrue(Time.isValidTime("15:00:15"));
    }

    @Test
    public void isEqualTime() {
        Time time1 = new Time("15:00");
        Time time2 = new Time("16:00");
        Time time3 = new Time("15:01");

        assertEquals(time1, time1);
        assertEquals(time2, time2);
        assertNotEquals(time1, time3);
    }

    @Test
    public void toStringTest() {
        Time time1 = new Time("15:00");
        Time time2 = new Time("00:00");
        assertEquals("15:00", time1.toString());
        assertEquals("00:00", time2.toString());
    }
}
