package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void equals() {
        Time time = new Time("11:30");
        Time timeCopy = new Time("11:30");

        assertTrue(time.equals(timeCopy));
        assertTrue(time.equals(time));

        assertFalse(time.equals(null));
        Time differentTime = new Time("15:20");
        assertFalse(time.equals(differentTime));

    }
}
