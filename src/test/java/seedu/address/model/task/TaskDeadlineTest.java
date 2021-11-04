package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class TaskDeadlineTest {

    private final TaskDeadline taskDeadline1 = new TaskDeadline(VALID_TASK_DEADLINE_0);
    private final TaskDeadline taskDeadline2 = new TaskDeadline(VALID_TASK_DEADLINE_1);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline(invalidDeadline));
    }

    @Test
    void isValidTaskDeadline() {
        // only contains alphanumeric characters -> returns true
        assertTrue(TaskDeadline.isValidTaskDeadline(VALID_TASK_DEADLINE_0));

        // only contains alphanumeric characters and dashes -> returns true
        assertTrue(TaskDeadline.isValidTaskDeadline(VALID_TASK_DEADLINE_1));

        // different time format -> returns true
        assertTrue(TaskDeadline.isValidTaskDeadline(VALID_TASK_DEADLINE_2));

        // contains others symbols -> returns false
        assertFalse(TaskDeadline.isValidTaskDeadline(INVALID_TASK_DEADLINE_1));
    }

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(taskDeadline1.equals(taskDeadline1));

        // not a TaskDeadline object -> returns false
        assertFalse(taskDeadline1.equals(VALID_TASK_DEADLINE_0));

        // same String -> returns true
        assertTrue(taskDeadline1.equals(new TaskDeadline(VALID_TASK_DEADLINE_0)));

        // null -> returns false
        assertFalse(taskDeadline1.equals(null));

        // different String -> returns false
        assertFalse(taskDeadline1.equals(taskDeadline2));
    }
}
