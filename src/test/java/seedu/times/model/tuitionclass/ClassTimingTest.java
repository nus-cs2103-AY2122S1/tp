package seedu.times.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.testutil.Assert.assertThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ClassTimingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassTiming(null));
    }

    @Test
    public void constructor_invalidClassTiming_throwsIllegalArgumentException() {
        //Todo
        String invalidClassTiming = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassTiming(invalidClassTiming));
    }

    @Test
    public void isValidClassTiming() {
        // null classTiming
        assertThrows(NullPointerException.class, () -> ClassTiming.isValidClassTiming(null));

        // invalid classTiming
        assertFalse(ClassTiming.isValidClassTiming("")); // empty string
        assertFalse(ClassTiming.isValidClassTiming(" ")); // spaces only
        assertFalse(ClassTiming.isValidClassTiming("mon 9:00-10:00")); // incorrect timing format
        assertFalse(ClassTiming.isValidClassTiming("mon 0900-1000")); // incorrect timing format
        assertFalse(ClassTiming.isValidClassTiming("mon 10:00")); // missing end time
        assertFalse(ClassTiming.isValidClassTiming("09:00-10:00")); // missing day time
        assertFalse(ClassTiming.isValidClassTiming("monday 10:00")); // incorrect day format
        assertFalse(ClassTiming.isValidClassTiming("THUR 0900-1000")); // incorrect day format
        assertFalse(ClassTiming.isValidClassTiming("THU 09:10-10:00")); // does not start on the hour or 30 min mark
        assertFalse(ClassTiming.isValidClassTiming("THU 09:00-10:15")); // does not end on the hour or 30 min mark
        assertFalse(ClassTiming.isValidClassTiming("THU 09:00-23:58")); // does not end at 23:59


        // valid classTiming
        assertTrue(ClassTiming.isValidClassTiming("Mon 01:00-02:00"));
        assertTrue(ClassTiming.isValidClassTiming("mON 01:00-23:30")); // DAY in weird caps
        assertTrue(ClassTiming.isValidClassTiming("mON 01:00-23:59")); // DAY in weird caps

    }


    @Test
    public void formatClassTiming() {
        // todo private method
    }

    @Test
    public void getDayToInt() {
        assertEquals(1, new ClassTiming("Mon 01:00-02:00").getDayToInt());
        assertEquals(2, new ClassTiming("Tue 01:00-02:00").getDayToInt());
        assertEquals(3, new ClassTiming("Wed 01:00-02:00").getDayToInt());
        assertEquals(4, new ClassTiming("Thu 01:00-02:00").getDayToInt());
        assertEquals(5, new ClassTiming("Fri 01:00-02:00").getDayToInt());
        assertEquals(6, new ClassTiming("Sat 01:00-02:00").getDayToInt());
        assertEquals(7, new ClassTiming("Sun 01:00-02:00").getDayToInt());

    }

    @Test
    public void isEarlier() {
        // same day earlier time
        assertTrue(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("wed 15:00-16:00")));
        // earlier day earlier time
        assertTrue(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("thu 15:00-16:00")));
        // earlier day later time
        assertTrue(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("fri 09:00-10:00")));
        // same day later time
        assertFalse(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("wed 09:00-10:00")));
        // later day earlier time
        assertFalse(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("mon 15:00-16:00")));
        // later day later time
        assertFalse(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("tue 09:00-10:00")));
        // same day same time slot
        assertFalse(new ClassTiming("wed 12:00-13:00").isEarlier(new ClassTiming("wed 12:00-13:00")));
    }

    @Test
    public void isAfter() {
        assertTrue(new ClassTiming("Sat 11:00-12:00")
                .isAfter(LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm"))));
        assertFalse(new ClassTiming("Sat 11:00-12:00")
                .isAfter(LocalTime.parse("11:00", DateTimeFormatter.ofPattern("HH:mm"))));
        assertFalse(new ClassTiming("Sat 11:00-12:00")
                .isAfter(LocalTime.parse("12:00", DateTimeFormatter.ofPattern("HH:mm"))));
        assertFalse(new ClassTiming("Sat 11:00-12:00")
                .isAfter(LocalTime.parse("15:00", DateTimeFormatter.ofPattern("HH:mm"))));
    }

    @Test
    public void getStartTime() {
        assertEquals(LocalTime.parse("11:00", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 11:00-12:00").getStartTime());

        assertNotEquals(LocalTime.parse("11:01", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 11:00-12:00").getStartTime());

        assertNotEquals(LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 11:00-12:00").getStartTime());

        assertEquals(LocalTime.parse("00:00", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 00:00-23:30").getStartTime());
    }

    @Test
    public void getEndTime() {
        assertEquals(LocalTime.parse("12:00", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 11:00-12:00").getEndTime());

        assertNotEquals(LocalTime.parse("12:01", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 11:00-12:00").getEndTime());

        assertNotEquals(LocalTime.parse("15:00", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 11:00-12:00").getEndTime());

        assertEquals(LocalTime.parse("23:30", DateTimeFormatter.ofPattern("HH:mm")),
                new ClassTiming("Sat 00:00-23:30").getEndTime());
    }

    @Test
    public void getClassTiming() {
        assertEquals("00:00-23:59", new ClassTiming("moN 00:00-23:59").getClassTiming());
        assertEquals("00:00-23:59", new ClassTiming("Tue 00:00-23:59").getClassTiming());
        assertEquals("00:00-23:59", new ClassTiming("wEd 00:00-23:59").getClassTiming());
        assertEquals("00:00-23:30", new ClassTiming("THU 00:00-23:30").getClassTiming());
        assertEquals("00:00-23:30", new ClassTiming("fri 00:00-23:30").getClassTiming());
        assertEquals("00:00-23:30", new ClassTiming("sAT 00:00-23:30").getClassTiming());
        assertEquals("00:00-23:30", new ClassTiming("SuN 00:00-23:30").getClassTiming());

        assertNotEquals("00:01-23:30", new ClassTiming("MON 00:00-23:59").getClassTiming());
    }

    @Test
    public void compareTo() {
        // same day earlier time
        assertEquals(-1, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("wed 15:00-16:00")));
        // earlier day earlier time
        assertEquals(-1, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("thu 15:00-16:00")));
        // earlier day later time
        assertEquals(-1, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("fri 09:00-10:00")));
        // same day later time
        assertEquals(1, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("wed 09:00-10:00")));
        // later day earlier time
        assertEquals(1, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("mon 15:00-16:00")));
        // later day later time
        assertEquals(1, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("tue 09:00-10:00")));
        // same day same time slot
        assertEquals(0, new ClassTiming("wed 12:00-13:00").compareTo(new ClassTiming("wed 12:00-13:00")));
    }
}
