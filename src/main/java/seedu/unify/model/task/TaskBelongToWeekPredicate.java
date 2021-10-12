package seedu.unify.model.task;


import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskBelongToWeekPredicate implements Predicate<Task> {
    private final Integer week;

    /**
     * Create a TaskBelongToWeekPredicate.
     */
    public TaskBelongToWeekPredicate(Integer week) {
        this.week = week;
    }

    @Override
    public boolean test(Task task) {

        return task.getDate().getWeek().equals(week);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBelongToWeekPredicate // instanceof handles nulls
                && week.equals(((TaskBelongToWeekPredicate) other).week)); // state check

    }

}
