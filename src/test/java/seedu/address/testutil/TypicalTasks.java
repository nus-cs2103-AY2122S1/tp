package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task REPORT_1 = new TaskBuilder().withName("Report 1")
            .withUniqueId("f31648db-5619-4bad-99d2-87367a2b5f28").withDeadline("2021-10-10").build();
    public static final Task REPORT_2 = new TaskBuilder().withName("Report 2").withDeadline("2021-11-11").build();

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
        return new ArrayList<>(List.of(REPORT_1, REPORT_2));
    }
}
