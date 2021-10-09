package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Handles the operation on the {@code TaskList} of a specified {@code Member}.
 */
public class TaskListManager {
    private TaskList tasks = new TaskList();

    public TaskListManager() {
    }

    /**
     * Load the given {@code TaskList} object.
     */
    public void loadTaskList(TaskList tasks) {
        if (this.tasks != tasks) {
            this.tasks = tasks;
        }
    }

    //// task-level operation

    /**
     * Returns ture if a given task with the same identity as task exist in the current task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the current task list.
     * The task must not already exist in the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from the current task list.
     * {@code key} must exist in the current task list.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskListManager that = (TaskListManager) o;
        return Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }

    public ObservableList<Task> getObservableTaskList() {
        return tasks.asUnmodifiableObservableList();
    }
}
