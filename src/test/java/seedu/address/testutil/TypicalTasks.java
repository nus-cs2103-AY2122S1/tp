package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;


public class TypicalTasks {

    public static final Task TASK1 = new TaskBuilder().withModule(MODULE_NAME_0).withId(VALID_TASK_ID_0)
            .withName(VALID_TASK_NAME_0).withDeadline(VALID_TASK_DEADLINE_0).build();
    public static final Task TASK2 = new TaskBuilder().withModule(MODULE_NAME_1).withId(VALID_TASK_ID_1)
            .withName(VALID_TASK_NAME_1).withDeadline(VALID_TASK_DEADLINE_1).build();
    public static final Task TASK3 = new TaskBuilder().withModule(MODULE_NAME_0).withId(VALID_TASK_ID_2)
            .withName(VALID_TASK_NAME_2).withDeadline(VALID_TASK_DEADLINE_2)
            .withStatus(true).build();

    /**
     * Returns a list of tasks assigned to the {@code moduleName} specified
     *
     * @param moduleName the module of which the tasks are assigned to
     * @return A list of tasks
     */
    public static List<Task> getTypicalTasksForModule(String moduleName) {
        //some samples, can add more/modify for testing
        Task task1 = new TaskBuilder()
                .withModule(moduleName)
                .withId("T1")
                .withName("Assignment 1")
                .withDeadline("2021-12-31").build();

        Task task2 = new TaskBuilder()
                .withModule(moduleName)
                .withId("T2")
                .withName("Assignment 2")
                .withDeadline("2021-12-30")
                .build();

        Task task3 = new TaskBuilder()
                .withModule(moduleName)
                .withId("T3")
                .withName("Assignment 3")
                .withDeadline("2021-11-30")
                .build();

        Task task4 = new TaskBuilder()
                .withModule(moduleName)
                .withId("T4")
                .withName("Assignment 4")
                .withDeadline("2021-11-30")
                .build();

        return new ArrayList<>(Arrays.asList(task1, task2, task3, task4));
    }

}
