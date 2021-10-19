package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "2021/12/12";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // Invalid deadline
        assertFalse(Deadline.isValidDeadline(""));
        assertFalse(Deadline.isValidDeadline("2020"));
        assertFalse(Deadline.isValidDeadline("2020/12/12"));
        assertFalse(Deadline.isValidDeadline("Dec 12 2020"));
        assertFalse(Deadline.isValidDeadline("2020-1-1"));

        // Valid deadline
        assertTrue(Deadline.isValidDeadline("2020-12-12"));
    }

    @Test
    public void equals() {
        Deadline deadline1 = new Deadline("2020-12-12");
        Deadline deadline2 = new Deadline("2020-12-12");
        Deadline deadline3 = new Deadline("2021-12-12");

        assertNotEquals(deadline1, deadline3);
        assertEquals(deadline1, deadline2);
    }
}

