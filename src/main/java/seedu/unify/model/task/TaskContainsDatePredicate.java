package seedu.unify.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskContainsDatePredicate implements Predicate<Task> {
    private final LocalDate date;

    public TaskContainsDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getDate().getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsDatePredicate // instanceof handles nulls
                && date.equals(((TaskContainsDatePredicate) other).date)); // state check
    }

}
