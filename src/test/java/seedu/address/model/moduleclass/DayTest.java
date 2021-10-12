package seedu.address.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DayTest {

    private final Day tuesday = new Day("2");
    private final Day wednesday = new Day ("3");
    private final Day thursday = new Day("4");
    private final Day sunday = new Day("7");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void constructor_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> new Day(""));
    }

    @Test
    public void illegalDay_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Day("8"));
    }

    @Test
    public void isValidDay() {
        assertThrows(NullPointerException.class, () -> Day.isValidDay(null));

        assertFalse(Day.isValidDay("8")); //out of range
        assertFalse(Day.isValidDay("0")); //out of range
        assertFalse(Day.isValidDay("-1"));
        assertFalse(Day.isValidDay("1.0"));

        assertTrue(Day.isValidDay("1"));
        assertTrue(Day.isValidDay("5"));
        assertTrue(Day.isValidDay("7"));
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
