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
        assertFalse(TaskTime.isValidTaskTime("30:59")); // invalid hour
        assertFalse(TaskTime.isValidTaskTime("22:70")); // invalid minutes
        assertFalse(TaskTime.isValidTaskTime("20-10")); // invalid seperators
        assertFalse(TaskTime.isValidTaskTime("30:2")); // no padding for numbers

        // valid time
        assertTrue(TaskTime.isValidTaskTime("23:59")); // valid time
    }
}
