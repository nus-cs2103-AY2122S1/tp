package manageme.model.task;


import static manageme.testutil.TypicalTasks.TASK_A;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageme.testutil.TaskBuilder;

public class TaskIsDoneTest {
    @Test
    public void constructor_empty_returnsFalseTaskIsDone() {
        assertFalse(new TaskIsDone().value);
    }

    @Test
    public void equals() {
        // both done, returns true
        assertTrue(TASK_A.isDone().equals(TASK_A.isDone()));

        // one done while the other not done, returns false
        Task notDoneA = new TaskBuilder(TASK_A).withIsDone(true).build();
        assertFalse(notDoneA.isDone().equals(TASK_A.isDone()));
    }

}
