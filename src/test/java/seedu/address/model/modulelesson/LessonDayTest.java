package seedu.address.model.modulelesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LessonDayTest {

    private final LessonDay tuesday = new LessonDay("2");
    private final LessonDay wednesday = new LessonDay("3");
    private final LessonDay thursday = new LessonDay("4");
    private final LessonDay sunday = new LessonDay("7");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonDay(null));
    }

    @Test
    public void constructor_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> new LessonDay(""));
    }

    @Test
    public void illegalDay_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new LessonDay("8"));
    }

    @Test
    public void isValidDay() {
        assertThrows(NullPointerException.class, () -> LessonDay.isValidDay(null));

        assertFalse(LessonDay.isValidDay("8")); //out of range
        assertFalse(LessonDay.isValidDay("0")); //out of range
        assertFalse(LessonDay.isValidDay("-1"));
        assertFalse(LessonDay.isValidDay("1.0"));

        assertTrue(LessonDay.isValidDay("1"));
        assertTrue(LessonDay.isValidDay("5"));
        assertTrue(LessonDay.isValidDay("7"));
    }

    @Test
    public void isEqualDay() {
        assertEquals(tuesday, tuesday);
        assertEquals(wednesday, wednesday);
        assertNotEquals(tuesday, wednesday);
    }

    @Test
    public void getDayAsIntTest() {
        assertEquals(4, thursday.getDayAsInt());
        assertEquals(7, sunday.getDayAsInt());
    }

    @Test
    public void getDayAsIntString() {
        assertEquals("2", tuesday.getDayAsIntString());
        assertEquals("3", wednesday.getDayAsIntString());
    }

    @Test
    public void toStringTest() {
        assertEquals("Thursday", thursday.toString());
        assertEquals("Sunday", sunday.toString());
    }

}
