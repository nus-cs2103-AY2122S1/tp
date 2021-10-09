package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests
 */
public class TypicalTasks {
    public static final Task MEETING = new Task("team meeting");
    public static final Task PROJECT = new Task("group project");
    public static final Task CHECK_PAYMENT = new Task("check payment");
    public static final Task CHECK_VACCINATION_STATUS = new Task("check vaccination status");
    public static final Task REHEARSAL = new Task("rehearse 2 times");

    public static final Task MEETING_DONE = new Task("team meeting", true);
    public static final Task PROJECT_DONE = new Task("group project", true);
    public static final Task CHECK_PAYMENT_DONE = new Task("check payment", true);
    public static final Task CHECK_VACCINATION_STATUS_DONE = new Task("check vaccination status", true);
    public static final Task REHEARSAL_DONE = new Task("rehearse 2 times", true);

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(MEETING, PROJECT, CHECK_PAYMENT, CHECK_VACCINATION_STATUS, REHEARSAL));
    }

    public static List<Task> getTypicalTasksDone() {
        return new ArrayList<>(Arrays.asList(MEETING_DONE, PROJECT_DONE, CHECK_PAYMENT_DONE,
                CHECK_VACCINATION_STATUS_DONE, REHEARSAL_DONE));
    }
}
