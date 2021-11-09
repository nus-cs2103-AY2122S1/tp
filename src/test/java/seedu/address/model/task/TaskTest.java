package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalTasks.TASK1;
import static seedu.address.testutil.TypicalTasks.TASK2;
import static seedu.address.testutil.TypicalTasks.TASK3;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

class TaskTest {
    private static final String DIFFERENT_MODULE_NAME = "CS2105";

    @Test
    public void isComplete() {
        // TASK3 is initialised to be completed -> returns true
        assertTrue(TASK3.isComplete());

        // TASK1 is initialised without specifying status, by default it is incomplete -> return false
        assertFalse(TASK1.isComplete());
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK1.isSameTask(TASK1));

        // null -> returns false
        assertFalse(TASK1.isSameTask(null));

        // different task -> returns false
        assertFalse(TASK1.isSameTask(TASK2));

        // same module but different ID -> return false
        Task taskDifferentId = new TaskBuilder()
                .withModule(MODULE_NAME_0)
                .withId("T111")
                .withName(VALID_TASK_NAME_0)
                .withDeadline(VALID_TASK_DEADLINE_0)
                .build();
        assertFalse(TASK1.isSameTask(taskDifferentId));

        // same ID but different module -> return false
        Task taskDifferentModule = new TaskBuilder()
                .withModule(DIFFERENT_MODULE_NAME)
                .withId(VALID_TASK_ID_0)
                .withName(VALID_TASK_NAME_0)
                .withDeadline(VALID_TASK_DEADLINE_0)
                .build();
        assertFalse(TASK1.isSameTask(taskDifferentModule));

        // both module and ID are the same -> returns true
        Task taskSame = new TaskBuilder().withModule(MODULE_NAME_0).withId(VALID_TASK_ID_0)
                .withName(VALID_TASK_NAME_0).withDeadline(VALID_TASK_DEADLINE_0).build();
        assertTrue(TASK1.isSameTask(taskSame));

    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(TASK1.equals(TASK1));

        // not a Task object -> returns false
        assertFalse(TASK1.equals(MODULE_NAME_0));

        // same attributes -> returns true
        Task taskSame = new TaskBuilder()
                .withModule(MODULE_NAME_0)
                .withId(VALID_TASK_ID_0)
                .withName(VALID_TASK_NAME_0)
                .withDeadline(VALID_TASK_DEADLINE_0)
                .build();
        assertTrue(TASK1.equals(taskSame));

        // null -> returns false
        assertFalse(TASK1.equals(null));

        // different task -> returns false
        assertFalse(TASK1.equals(TASK2));

        // different module name -> returns false
        Task taskDifferentModule = new TaskBuilder()
                .withModule(DIFFERENT_MODULE_NAME)
                .withId(VALID_TASK_ID_0)
                .withName(VALID_TASK_NAME_0)
                .withDeadline(VALID_TASK_DEADLINE_0)
                .build();
        assertFalse(TASK1.equals(taskDifferentModule));

        // different task name -> returns false
        Task taskDifferentName = new TaskBuilder().withModule(MODULE_NAME_0).withId(VALID_TASK_ID_0)
                .withName("task").withDeadline(VALID_TASK_DEADLINE_0).build();
        assertFalse(TASK1.equals(taskDifferentName));

        // different task ID -> returns false
        Task taskDifferentId = new TaskBuilder().withModule(MODULE_NAME_0).withId("T111")
                .withName(VALID_TASK_NAME_0).withDeadline(VALID_TASK_DEADLINE_0).build();
        assertFalse(TASK1.equals(taskDifferentId));
    }
}
