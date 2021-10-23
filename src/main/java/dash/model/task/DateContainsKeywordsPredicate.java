package dash.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Task> {
    private final TaskDate taskDate;

    public DateContainsKeywordsPredicate(TaskDate taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public boolean test(Task task) {
        boolean dateEqual = false;
        boolean timeEqual = false;
        if (task.getTaskDate().hasDate() && this.taskDate.hasDate()) {
            dateEqual = task.getTaskDate().toDateString().equals(this.taskDate.toDateString());
        }
        if (task.getTaskDate().hasTime() && this.taskDate.hasTime()) {
            timeEqual = task.getTaskDate().toTimeString().equals(this.taskDate.toTimeString());
        }
        return dateEqual || timeEqual;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && taskDate.equals(((DateContainsKeywordsPredicate) other).taskDate)); // state check
    }

}
