package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import dash.testutil.TaskBuilder;

public class TaskDateAfterCurrentDatePredicateTest {

    TaskDateAfterCurrentDatePredicate predicate = new TaskDateAfterCurrentDatePredicate();

    @Test
    public void test_TaskDateAfterCurrentDatePredicate_returnsTrue() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nextDay = LocalDate.now().plusDays(1);


        Task taskWithDateAfterCurrentDate = new TaskBuilder().withTaskDescription("Task")
                .withTaskDate(nextDay.format(dateFormat)).build();

        assertTrue(predicate.test(taskWithDateAfterCurrentDate));
    }

    @Test
    public void test_TaskDateAfterCurrentDatePredicate_returnsFalse() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nextDay = LocalDate.now().plusDays(-1);


        Task taskWithDateBeforeCurrentDate = new TaskBuilder().withTaskDescription("Task")
                .withTaskDate(nextDay.format(dateFormat)).build();

        assertFalse(predicate.test(taskWithDateBeforeCurrentDate));
    }
}
