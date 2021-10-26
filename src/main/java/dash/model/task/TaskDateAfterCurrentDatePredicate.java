package dash.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Predicate;

public class TaskDateAfterCurrentDatePredicate implements Predicate<Task> {
    private final LocalDate currentDate = LocalDate.now();
    private final LocalDateTime currentDateTime = LocalDateTime.now();

    public TaskDateAfterCurrentDatePredicate() {
    }

    @Override
    public boolean test(Task task) {
        if (task.getCompletionStatus().get()) {
            return false;
        }

        if (!task.getTaskDate().hasDate()) {
            return false;
        }

        LocalDate taskDateToCheck = task.getTaskDate().getDate().get();

        if (!task.getTaskDate().hasTime()) {
            return taskDateToCheck.isAfter(currentDate) || taskDateToCheck.isEqual(currentDate);
        }

        LocalTime taskTimeToCheck = task.getTaskDate().getTime().get();
        LocalDateTime taskDateTimeToCheck = LocalDateTime.of(taskDateToCheck, taskTimeToCheck);
        return taskDateTimeToCheck.isAfter(currentDateTime);
    }
}
