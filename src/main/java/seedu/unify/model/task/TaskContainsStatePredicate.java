package seedu.unify.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code State} matches any of the state given.
 */
public class TaskContainsStatePredicate implements Predicate<Task> {
    private final State state;

    public TaskContainsStatePredicate(State state) {
        this.state = state;
    }

    @Override
    public boolean test(Task task) {
        return task.getState().equals(state);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsStatePredicate // instanceof handles nulls
                && state.equals(((TaskContainsStatePredicate) other).state)); // state check
    }

}
