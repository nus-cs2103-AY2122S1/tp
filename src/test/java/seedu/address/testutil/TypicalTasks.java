package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task REPORT_1 = new TaskBuilder().withName("Report 1").withDeadline("2021-10-10").build();

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(List.of(REPORT_1));
    }
}
