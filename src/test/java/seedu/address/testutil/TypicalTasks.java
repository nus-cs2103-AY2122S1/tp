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

    public static final Task TUTORIAL = new TaskBuilder().withName("Do Tutorial").withDeadline("2000-02-02")
            .withTags("work").build();
    public static final Task MEETING = new TaskBuilder().withName("Prepare for meeting").withDeadline("2010-02-02")
            .withTags("work").build();
    public static final Task DINNER = new TaskBuilder().withName("Buy Dinner").withDeadline("2021-10-12")
            .withTags("personal").build();
    public static final Task FRIEND_BIRTHDAY = new TaskBuilder().withName("Plan Birthday").withDeadline("2021-10-20")
            .withTags("friends").build();
    public static final Task EXERCISE = new TaskBuilder().withName("Run").withDeadline("2000-02-02")
            .build();

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
        return new ArrayList<>(Arrays.asList(TUTORIAL, MEETING, DINNER, FRIEND_BIRTHDAY, EXERCISE));
    }
}
