package seedu.address.model.module.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TaskDeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "1973/10/01 8:00";
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline(invalidTime));
    }

    @Test
    public void isValidTaskDeadline() {
        // null time
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidTaskDeadline(null));

        // invalid time
        assertFalse(TaskDeadline.isValidTaskDeadline("")); // empty string
        assertFalse(TaskDeadline.isValidTaskDeadline("  ")); // space only
        assertFalse(TaskDeadline.isValidTaskDeadline("1973/10/01 8:00")); // error format
        assertFalse(TaskDeadline.isValidTaskDeadline("09/08/2021")); // missing time
        assertFalse(TaskDeadline.isValidTaskDeadline("50/50/2021 23:59")); // invalid date
        assertFalse(TaskDeadline.isValidTaskDeadline("10/10/2021 50:50")); // invalid time

        // valid time
        assertTrue(TaskDeadline.isValidTaskDeadline("10/10/2021 12:00"));
    }
}
