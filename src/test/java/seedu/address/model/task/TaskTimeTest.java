package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTime(null));
    }

    @Test
    public void constructor_invalidTaskTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskTime(invalidTime));
    }

    @Test
    public void isValidTaskTime() {
        // null time
        assertThrows(NullPointerException.class, () -> TaskTime.isValidTaskTime(null));

        // invalid time
        assertFalse(TaskTime.isValidTaskTime("30:59:30.12")); // invalid hour
        assertFalse(TaskTime.isValidTaskTime("22:70:30.12")); // invalid minutes
        assertFalse(TaskTime.isValidTaskTime("23:59:99.12")); // invalid seconds
        assertFalse(TaskTime.isValidTaskTime("10:30:22.9999999999")); // invalid nanoseconds
        assertFalse(TaskTime.isValidTaskTime("20-10-10")); // invalid seperators
        assertFalse(TaskTime.isValidTaskTime("30:2:20")); // no padding for numbers

        // valid time
        assertTrue(TaskTime.isValidTaskTime("23:59:59")); // valid time
        assertTrue(TaskTime.isValidTaskTime("23:59")); // Hours and Minutes only
        assertTrue(TaskTime.isValidTaskTime("23:59:59")); // Hour, Min, Seconds
        assertTrue(TaskTime.isValidTaskTime("23:59:59.999")); // Hour, Min, Sec, NanoSec
    }
}
