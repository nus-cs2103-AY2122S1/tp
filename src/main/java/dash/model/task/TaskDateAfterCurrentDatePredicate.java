package dash.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class TaskDateAfterCurrentDatePredicate implements Predicate<Task> {
    private final LocalDate currentDate = LocalDate.now();

    public TaskDateAfterCurrentDatePredicate() {
    }

    @Override
    public boolean test(Task task) {
        if (!task.getTaskDate().hasDate()) {
            return false;
        }
        LocalDate taskDateToCheck = task.getTaskDate().getDate().get();
        return taskDateToCheck.isAfter(currentDate) || taskDateToCheck.isEqual(currentDate);
    }
}
