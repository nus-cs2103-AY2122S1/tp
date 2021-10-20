package dash.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class TaskDateAfterCurrentDatePredicate implements Predicate<Task> {
    private final LocalDate currentDate = LocalDate.now();
    private final TaskList taskList;

    public TaskDateAfterCurrentDatePredicate(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean test(Task task) {
        return false;
    }
}
