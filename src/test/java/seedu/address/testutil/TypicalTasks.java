package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task MEETING = new TaskBuilder()
            .withName("Project Meeting")
            .withDate("2021-12-06")
            .withTime("17:00")
            .withVenue("Zoom")
            .withDone(false)
            .build();
    public static final Task DISCUSSION = new TaskBuilder()
            .withName("Module Discussion")
            .withDate("2022-03-16")
            .withTime("12:00")
            .withVenue("Zoom")
            .withDone(false)
            .build();
    public static final Task LUNCH = new TaskBuilder()
            .withName("Lunch Date")
            .withDate("2021-12-22")
            .withTime("11:30")
            .withVenue("Marina Bay Sands")
            .withDone(false)
            .build();
    public static final Task DINNER = new TaskBuilder()
            .withName("Dinner Date")
            .withDate("2022-01-02")
            .withTime("17:00")
            .withVenue("Clementi Mall")
            .withDone(false)
            .build();
    public static final Task EXERCISE = new TaskBuilder()
            .withName("Gym Session")
            .withDate("2021-12-03")
            .withTime("14:00")
            .withVenue("UTown Gym")
            .withDone(false)
            .build();
    public static final Task PROJECT = new TaskBuilder()
            .withName("Individual Project")
            .withDate("2021-10-06")
            .withTime("17:00")
            .withVenue("Zoom")
            .withDone(true)
            .build();

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(MEETING, DISCUSSION, LUNCH, DINNER, EXERCISE, PROJECT));
    }
}
