package seedu.unify.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code State} matches any of the state given.
 */
public class TaskIsDonePredicate implements Predicate<Task> {

    @Override
    public boolean test(Task task) {
        return task.getState().isMarkedDone();
    }
}
