package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class TaskNameTest {

    private final TaskName taskName1 = new TaskName(VALID_TASK_NAME_0);
    private final TaskName taskName2 = new TaskName(VALID_TASK_NAME_1);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidName = " ";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidName));
    }

    @Test
    void isValidTaskName() {
        // contains only alphanumeric characters and spaces, not blank -> returns true
        assertTrue(TaskName.isValidTaskName(VALID_TASK_NAME_0));

        // blank -> returns false
        assertFalse(TaskName.isValidTaskName(" "));

        // starts with a space -> false
        assertFalse(TaskName.isValidTaskName(" task"));

        // contains other symbols -> returns false
        assertFalse(TaskName.isValidTaskName("!@#$"));
    }

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(taskName1.equals(taskName1));

        // not a TaskName object -> returns false
        assertFalse(taskName1.equals(VALID_TASK_NAME_0));

        // same String -> returns true
        assertTrue(taskName1.equals(new TaskName(VALID_TASK_NAME_0)));

        // null -> returns false
        assertFalse(taskName1.equals(null));

        // different String -> returns false
        assertFalse(taskName1.equals(taskName2));
    }
}
