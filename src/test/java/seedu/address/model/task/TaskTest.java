package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void constructor_invalidTask_throwsIllegalArgumentException() {
        String invalidTask = "";
        assertThrows(IllegalArgumentException.class, () -> new Task(invalidTask));
    }

    @Test
    void isValidTaskName() {
        // null task name
        assertThrows(NullPointerException.class, () -> Task.isValidTaskName(null));

        // blank task name
        assertFalse(Task.isValidTaskName(""));
        assertFalse(Task.isValidTaskName(" "));

        // valid task name
        assertTrue(Task.isValidTaskName("rehearsal"));
        assertTrue(Task.isValidTaskName("team meeting"));
        assertTrue(Task.isValidTaskName("I can do it 12345 67890 times"));
        assertTrue(Task.isValidTaskName("very-very-very-very-very-very-long-long-long long long long long long name"));
        assertTrue(Task.isValidTaskName("0 1 2 3 4 5 6 7 8 9"));
    }

    @Test
    void isSameTask() {
    }
}
