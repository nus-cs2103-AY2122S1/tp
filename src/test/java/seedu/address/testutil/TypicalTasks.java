package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TUTORIAL = new DeadlineTaskBuilder().withName("Do Tutorial")
            .withDate("2000-02-02")
            .withTags("work").build();
    public static final Task EXERCISE = new DeadlineTaskBuilder().withName("Run")
            .withDate("2000-02-02")
            .build();
    public static final Task MEETING = new DeadlineTaskBuilder().withName("Prepare for meeting")
            .withDate("2010-02-02")
            .withTags("work").build();
    public static final Task DINNER = new DeadlineTaskBuilder().withName("Buy Dinner")
            .withDate("2021-10-12")
            .withTags("personal").build();
    public static final Task FRIEND_BIRTHDAY = new DeadlineTaskBuilder().withName("Plan Birthday")
            .withDate("2021-10-20")
            .withTags("friends").build();

    private TypicalTasks() {}

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBookWithTasks() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(
                TUTORIAL, EXERCISE, MEETING, DINNER, FRIEND_BIRTHDAY));
    }
}
