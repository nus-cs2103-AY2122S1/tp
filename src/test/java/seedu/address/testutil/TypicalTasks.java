package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_SEW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    // Manually added
    public static final Task MEASURE = new TaskBuilder()
            .withLabel("Measure client")
            .withDate("12th December 1921").build();

    public static final Task CUT = new TaskBuilder()
            .withLabel("Cut fabric")
            .withDate("2nd November 2021").build();

    public static final Task ALTER = new TaskBuilder()
            .withLabel("Mark alterations")
            .withDate("31 January 2008").build();

    public static final Task FIT = new TaskBuilder()
            .withLabel("Fit clothing on customer")
            .withDate("9th November 2001").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withLabel(VALID_LABEL_SEW).withDate(VALID_DATE_OCT).build();
    public static final Task BOB = new TaskBuilder().withLabel(VALID_LABEL_ORDER).withDate(VALID_DATE_SEPT).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns a {@code TaskList} with all the typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasks()) {
            tl.add(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(MEASURE, CUT, ALTER, FIT));
    }
}
