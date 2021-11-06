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
        TaskDate taskDate = task.getTaskDate();
        boolean dateEqual = taskDate.hasDate();
        boolean timeEqual = taskDate.hasTime();
        if (taskDate.hasDate() && this.taskDate.hasDate()) {
            dateEqual = taskDate.toDateString().equals(this.taskDate.toDateString());
        } else if (!taskDate.hasDate() && !this.taskDate.hasDate()) {
            dateEqual = true;
        }
        if (taskDate.hasTime() && this.taskDate.hasTime()) {
            timeEqual = taskDate.toTimeString().equals(this.taskDate.toTimeString());
        } else if (!taskDate.hasTime() && !this.taskDate.hasTime()) {
            timeEqual = true;
        }
        if (!taskDate.hasDate() && this.taskDate.hasDate() && this.taskDate.hasTime()) {
            return taskDate.toTimeString().equals(this.taskDate.toTimeString());
        } else {
            return dateEqual && timeEqual;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && taskDate.equals(((DateContainsKeywordsPredicate) other).taskDate)); // state check
    }

}
