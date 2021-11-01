package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;


public class TypicalTasks {

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
