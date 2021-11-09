package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class TaskIdTest {

    private final TaskId taskId1 = new TaskId(VALID_TASK_ID_0);
    private final TaskId taskId2 = new TaskId(VALID_TASK_ID_1);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskId(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = " ";
        assertThrows(IllegalArgumentException.class, () -> new TaskId(invalidId));
    }

    @Test
    void isValidTaskId() {
        // capital T followed by a number -> return true
        assertTrue(TaskId.isValidTaskId(VALID_TASK_ID_0));

        // not start with capital T -> return false
        assertFalse(TaskId.isValidTaskId("01"));

        // starts with capital T but not followed by a number -> return false
        assertFalse(TaskId.isValidTaskId("Tt"));

        // has a space between the capital T and the number -> return false
        assertFalse(TaskId.isValidTaskId("T 1"));
    }

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(taskId1.equals(taskId1));

        // not a TaskId object -> returns false
        assertFalse(taskId1.equals(VALID_TASK_ID_0));

        // same String -> returns true
        assertTrue(taskId1.equals(new TaskId(VALID_TASK_ID_0)));

        // null -> returns false
        assertFalse(taskId1.equals(null));

        // different String -> returns false
        assertFalse(taskId1.equals(taskId2));
    }
}
