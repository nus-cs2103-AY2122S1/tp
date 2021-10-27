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
        String invalidTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }

    @Test
    public void isValidTaskName() {
        // null TaskName
        assertThrows(NullPointerException.class, () -> TaskName.isValidName(null));

        // invalid TaskName
        assertFalse(TaskName.isValidName("")); // empty string
        assertFalse(TaskName.isValidName(" ")); // spaces only
        assertFalse(TaskName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid TaskName
        assertTrue(TaskName.isValidName("peter jack")); // alphabets only
        assertTrue(TaskName.isValidName("12345")); // numbers only
        assertTrue(TaskName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(TaskName.isValidName("Capital Tan")); // with capital letters
        assertTrue(TaskName.isValidName("David Roger Jackson Ray Jr 2nd")); // long TaskNames
    }
}
