package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDate(null));
    }

    @Test
    public void constructor_invalidTaskDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDate(invalidDate));
    }

    @Test
    public void isValidTaskDate() {
        // null date
        assertThrows(NullPointerException.class, () -> TaskDate.isValidTaskDate(null));

        // invalid date
        assertFalse(TaskDate.isValidTaskDate("2020/01/01")); // invalid seperator
        assertFalse(TaskDate.isValidTaskDate("2020-02")); // no day
        assertFalse(TaskDate.isValidTaskDate("2020-21")); // no month
        assertFalse(TaskDate.isValidTaskDate("03-02")); // no year
        assertFalse(TaskDate.isValidTaskDate("2020-13-02")); // invalid month
        assertFalse(TaskDate.isValidTaskDate("2020-10-35")); // invalid day
        assertFalse(TaskDate.isValidTaskDate("10010-02-02")); // invalid year
        assertFalse(TaskDate.isValidTaskDate("2020-2-2")); // non padded date
        assertFalse(TaskDate.isValidTaskDate("2020-04-31")); // Invalid day for month with 30 days
        assertFalse(TaskDate.isValidTaskDate("2021-02-29")); // Invalid day for non leap year feb


        // valid date
        assertTrue(TaskDate.isValidTaskDate("2020-09-09")); // valid date
        assertTrue(TaskDate.isValidTaskDate("2020-02-29")); // leap year
        assertTrue(TaskDate.isValidTaskDate("2020-12-31")); // Month with 31 days
        assertTrue(TaskDate.isValidTaskDate("2020-11-30")); // Month with 30 days
        assertTrue(TaskDate.isValidTaskDate("2021-02-28")); // Non leap year feb
    }
}
