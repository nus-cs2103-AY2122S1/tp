package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class TaskIDTest {
    @Test
    public void constructor_emptyTaskID_throwsIllegalArgumentException() {
        String emptyTaskID = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskID(emptyTaskID));
    }

    @Test
    public void constructor_invalidTaskID_throwsIllegalArgumentException() {
        String emptyTaskID = "abcd";
        assertThrows(IllegalArgumentException.class, () -> new TaskID(emptyTaskID));
    }

    @Test
    void isValidTaskID() {
        // null task id
        assertThrows(NullPointerException.class, () -> TaskID.isValidTaskID(null));

        // blank task id
        assertFalse(TaskID.isValidTaskID(""));
        assertFalse(TaskID.isValidTaskID(" "));

        // valid task id
        assertTrue(TaskID.isValidTaskID("12"));
        assertTrue(TaskID.isValidTaskID("1234"));
        assertTrue(TaskID.isValidTaskID("9999999"));
    }
}