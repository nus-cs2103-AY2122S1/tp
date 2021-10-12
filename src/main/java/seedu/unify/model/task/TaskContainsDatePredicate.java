package seedu.unify.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.unify.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskContainsDatePredicate implements Predicate<Task> {
    private final Date date;

    public TaskContainsDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsDatePredicate // instanceof handles nulls
                && date.equals(((TaskContainsDatePredicate) other).date)); // state check
    }

}