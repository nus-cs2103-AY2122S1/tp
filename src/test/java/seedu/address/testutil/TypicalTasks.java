package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_SEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKTAG_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKTAG_SEW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

public class TypicalTasks {

    public static final Task TASK1 = new TaskBuilder().withLabel("Sew red buttons")
            .withDate("2021-09-18").withTaskTag("SO10").build();
    public static final Task TASK2 = new TaskBuilder().withLabel("Buy green buttons")
            .withDate("2021-09-19").withIsDone(false).withTaskTag("SO2").build();
    public static final Task TASK3 = new TaskBuilder().withLabel("Make orange buttons")
            .withDate("2021-09-23").withIsDone(true).withTaskTag("SO2").build();
    public static final Task TASK4 = new TaskBuilder().withLabel("Adjust blue buttons")
            .withDate("2021-09-20").withIsDone(false).build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task ORDER = new TaskBuilder().withLabel(VALID_LABEL_ORDER).withDate(VALID_DATE_SEPT)
            .withTaskTag(VALID_TASKTAG_ORDER).build();
    public static final Task SEW = new TaskBuilder().withLabel(VALID_LABEL_SEW).withDate(VALID_DATE_OCT)
            .withTaskTag(VALID_TASKTAG_SEW).build();

    /**
     * Returns an {@code AddressBook} with all the typical tasks added.
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tl = new TaskBook();
        for (Task task : getTypicalTasks()) {
            tl.addTask(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK1, TASK2, TASK3, TASK4));
    }

}
