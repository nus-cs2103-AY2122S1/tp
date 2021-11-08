package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.unify.testutil.TaskBuilder;

public class TaskIsDonePredicateTest {

    @Test
    public void test_taskIsDone_returnsTrue() {
        TaskIsDonePredicate predicate = new TaskIsDonePredicate();

        assertTrue(predicate.test(new TaskBuilder().withName("Quiz").withState("DONE").build()));
    }

    @Test
    public void test_taskIsDone_returnsFalse() {
        TaskIsDonePredicate predicate = new TaskIsDonePredicate();

        assertFalse(predicate.test(new TaskBuilder().withName("Quiz").withState("TODO").build()));
    }
}
