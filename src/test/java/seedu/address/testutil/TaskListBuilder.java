package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

public class TaskListBuilder {
    private List<Task> tasks;

    public TaskListBuilder() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskListToCopy}.
     */
    public TaskListBuilder(TaskList taskListToCopy) {
        tasks = new ArrayList<>();
        taskListToCopy.iterator().forEachRemaining((task -> tasks.add(task)));
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a {@code TaskList} object.
     */
    public TaskList build() {
        TaskList result = new TaskList();
        result.setTasks(tasks);
        return result;
    }
}
