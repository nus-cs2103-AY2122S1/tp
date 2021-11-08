package seedu.gamebook.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.gamebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {
    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        // Not an integer
        assertThrows(IllegalArgumentException.class, () -> new Duration("123.4"));

        // Negative integer
        assertThrows(IllegalArgumentException.class, () -> new Duration("-1234"));

        // leading 0s
        assertThrows(IllegalArgumentException.class, () -> new Duration("00000001m"));

        // invalid minute
        assertThrows(IllegalArgumentException.class, () -> new Duration("12:60"));
    }

    @Test
    public void constructor_validAmount() {
        Duration duration;
        duration = new Duration("12:34");
        assertEquals(12 * 60 + 34, duration.getDurationMinutes());

        // With white space
        duration = new Duration("      12345");
        assertEquals(12345, duration.getDurationMinutes());
        duration = new Duration("12345       ");
        assertEquals(12345, duration.getDurationMinutes());
        duration = new Duration("   12345       ");
        assertEquals(12345, duration.getDurationMinutes());

        // Different formats
        duration = new Duration("12h 34m");
        assertEquals(12 * 60 + 34, duration.getDurationMinutes());
        duration = new Duration("12h");
        assertEquals(12 * 60, duration.getDurationMinutes());
        duration = new Duration("34m");
        assertEquals(34, duration.getDurationMinutes());
    }

    @Test
    public void equal_test() {
        // Same object
        Duration duration1 = new Duration("12:34");
        assertTrue(duration1.equals(duration1));

        // Same amount
        Duration duration2 = new Duration("12:34");
        assertTrue(duration1.equals(duration2));

        // Check reflexitivity
        assertTrue(duration2.equals(duration1));

        duration2 = new Duration("12h 34m");
        assertTrue(duration1.equals(duration2));
    }
}
