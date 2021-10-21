package seedu.address.model.modulelesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LessonTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonTime(null));
    }

    @Test
    public void constructor_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> new LessonTime(""));
    }

    @Test
    public void invalidTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new LessonTime("15:00:15"));

        assertThrows(IllegalArgumentException.class, () -> new LessonTime("15:0"));
    }

    @Test
    public void isValidTime() {
        assertThrows(NullPointerException.class, () -> LessonTime.isValidTime(null));

        assertFalse(LessonTime.isValidTime("24:12")); //out of range
        assertFalse(LessonTime.isValidTime("23.12")); //invalid format
        assertFalse(LessonTime.isValidTime("13:0")); //invalid format
        assertFalse(LessonTime.isValidTime("15:61")); //out of range
        assertFalse(LessonTime.isValidTime("24:00")); //out of range
        assertFalse(LessonTime.isValidTime("0:00")); //LocalTime cannot parse this
        assertFalse(LessonTime.isValidTime("1:00")); //LocalTime cannot parse this
        assertFalse(LessonTime.isValidTime("101:00"));
        assertFalse(LessonTime.isValidTime("15:00:15"));

        assertTrue(LessonTime.isValidTime("00:00"));
        assertTrue(LessonTime.isValidTime("01:00"));
        assertTrue(LessonTime.isValidTime("15:00"));
        assertTrue(LessonTime.isValidTime("12:31"));
        assertTrue(LessonTime.isValidTime("23:59"));
    }

    @Test
    public void isEqualTime() {
        LessonTime lessonTime1 = new LessonTime("15:00");
        LessonTime lessonTime2 = new LessonTime("16:00");
        LessonTime lessonTime3 = new LessonTime("15:01");

        assertEquals(lessonTime1, lessonTime1);
        assertEquals(lessonTime2, lessonTime2);
        assertNotEquals(lessonTime1, lessonTime3);
    }

    @Test
    public void toStringTest() {
        LessonTime lessonTime1 = new LessonTime("15:00");
        LessonTime lessonTime2 = new LessonTime("00:00");
        assertEquals("15:00", lessonTime1.toString());
        assertEquals("00:00", lessonTime2.toString());
    }
}
