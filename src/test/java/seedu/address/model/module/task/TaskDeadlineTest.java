package seedu.address.model.module.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.module.task.TaskDeadline.DEADLINE_DATE_TIME_FORMATTER;
import static seedu.address.model.module.task.TaskDeadline.isValidTaskDeadline;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

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
    public void isValidTaskDeadline_false() {
        // null time
        assertThrows(NullPointerException.class, () -> isValidTaskDeadline(null));

        // invalid time
        assertFalse(isValidTaskDeadline("")); // empty string
        assertFalse(isValidTaskDeadline("  ")); // space only
        assertFalse(isValidTaskDeadline("1973/10/01 8:00")); // error format
        assertFalse(isValidTaskDeadline("09/08/2021")); // missing time
        assertFalse(isValidTaskDeadline("50/50/2021 23:59")); // invalid date
        assertFalse(isValidTaskDeadline("31/12/3000 24:00")); // invalid date
        assertFalse(isValidTaskDeadline("31/12/1969 00:00")); // invalid date
        assertFalse(isValidTaskDeadline("29/02/-1999 12:00")); // invalid date
        assertFalse(isValidTaskDeadline("10/10/2021 50:50")); // invalid time
    }

    @Test
    public void isValidTaskDeadline_true() {
        assertTrue(isValidTaskDeadline("10/10/2021 12:00"));
        assertTrue(isValidTaskDeadline("31/11/1999 12:00"));
        assertTrue(isValidTaskDeadline("29/02/1999 12:00"));
        assertTrue(isValidTaskDeadline("17/09/1999 24:00"));
        assertTrue(isValidTaskDeadline("01/01/1970 00:00"));
        assertTrue(isValidTaskDeadline("31/12/3000 23:59"));
    }

    @Test
    public void test_deadlineMethod() {
        LocalDateTime testDateTime = LocalDateTime.parse("01/01/1970 00:00", DEADLINE_DATE_TIME_FORMATTER);
        assertEquals(testDateTime, new TaskDeadline("01/01/1970 00:00").getDeadline());
    }
}
