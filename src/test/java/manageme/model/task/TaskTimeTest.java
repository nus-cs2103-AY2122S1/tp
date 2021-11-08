package manageme.model.task;

import static manageme.testutil.Assert.assertThrows;
import static manageme.testutil.TypicalTasks.TASK_A;
import static manageme.testutil.TypicalTasks.TASK_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTime(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTime = "2021/10/05 23:59";
        assertThrows(IllegalArgumentException.class, () -> new TaskTime(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> TaskTime.isValidTaskTime(null));

        // valid time
        assertTrue(TaskTime.isValidTaskTime(""));
        assertTrue(TaskTime.isValidTaskTime("2021-10-05T23:59"));
        assertTrue(TaskTime.isValidTaskTime("2021-11-29T12:00"));

        // invalid time
        assertFalse(TaskTime.isValidTaskTime("abcd")); // alphabets only
        assertFalse(TaskTime.isValidTaskTime("12345")); // numbers only
        assertFalse(TaskTime.isValidTaskTime("2021/10/05T23:59")); // with slash
    }

    @Test
    public void toDisplayString() {
        // empty TaskTime, returns ""
        assertTrue(TaskTime.empty().toDisplayString().equals(""));
    }

    @Test
    public void equals() {
        // same date/time, returns true
        assertTrue(TASK_A.getStart().equals(TASK_A.getStart()));
        assertTrue(TASK_A.getEnd().equals(TASK_A.getEnd()));

        // different date/time, returns false
        assertFalse(TASK_A.getStart().equals(TASK_B.getStart()));
        assertFalse(TASK_A.getEnd().equals(TASK_B.getEnd()));
    }
    @Test
    public void empty() {
        // Empty TaskTime with value set as ""
        assertTrue(TaskTime.empty().value.equals(""));

        // Empty TaskTime with time set as an empty Optional<String>
        assertTrue(TaskTime.empty().time.isEmpty());
    }
    @Test
    public void isEmpty() {
        // Empty TaskTime, returns true
        assertTrue(TaskTime.empty().isEmpty());

        // Empty TaskTime, returns false
        assertFalse(TASK_A.getStart().isEmpty());
    }
    @Test
    public void getTime() {
        // Empty TaskTime, returns null
        assertNull(TaskTime.empty().getTime());
        // Valid TaskTime, returns its time as a LocalDateTime
        assertTrue(TASK_A.getStart().getTime().equals(LocalDateTime.parse("2021-10-05T11:50")));
    }
}
