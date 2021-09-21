package seedu.address.model.task;

import java.util.ArrayList;

/**
 * A list of tasks supporting minimal list operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
