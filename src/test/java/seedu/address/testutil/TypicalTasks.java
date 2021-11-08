package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests
 */
public class TypicalTasks {
    public static final Task MEETING = new TaskBuilder().build();
    public static final Task PROJECT = new TaskBuilder()
            .withName("group project")
            .withDeadline("21/10/2021 23:59")
            .build();
    public static final Task CHECK_PAYMENT = new TaskBuilder().withName("check payment").build();
    public static final Task CHECK_VACCINATION_STATUS = new TaskBuilder()
            .withName("check vaccination status")
            .withDeadline("22/10/2021 08:00")
            .build();
    public static final Task REHEARSAL = new TaskBuilder().withName("rehearse 2 times").build();

    public static final Task MEETING_DONE = new TaskBuilder(MEETING).withIsDone(true).build();
    public static final Task PROJECT_DONE = new TaskBuilder(PROJECT).withIsDone(true).build();
    public static final Task CHECK_PAYMENT_DONE = new TaskBuilder(CHECK_PAYMENT).withIsDone(true).build();
    public static final Task CHECK_VACCINATION_STATUS_DONE = new TaskBuilder(CHECK_VACCINATION_STATUS)
            .withIsDone(true).build();
    public static final Task REHEARSAL_DONE = new TaskBuilder(REHEARSAL).withIsDone(true).build();

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(MEETING, PROJECT, CHECK_PAYMENT, CHECK_VACCINATION_STATUS, REHEARSAL));
    }

    public static List<Task> getTypicalTasksDone() {
        return new ArrayList<>(Arrays.asList(MEETING_DONE, PROJECT_DONE, CHECK_PAYMENT_DONE,
                CHECK_VACCINATION_STATUS_DONE, REHEARSAL_DONE));
    }
}
