package seedu.address.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
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
}
