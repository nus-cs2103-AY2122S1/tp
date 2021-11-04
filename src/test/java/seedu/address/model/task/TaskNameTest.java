package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidName));
    }

    @Test
    public void isValidTaskName() {
        // null task name
        assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid task name
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only
        assertFalse(TaskName.isValidTaskName(" peter*")); // first character is white space

        // valid task name
        assertTrue(TaskName.isValidTaskName("peter jack")); // alphabets only
        assertTrue(TaskName.isValidTaskName("12345")); // numbers only
        assertTrue(TaskName.isValidTaskName("peter the 2nd")); // alphanumeric characters
        assertTrue(TaskName.isValidTaskName("Capital Tan")); // with capital letters
        assertTrue(TaskName.isValidTaskName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(TaskName.isValidTaskName("Task@Idaho")); // special characters included
    }
}
