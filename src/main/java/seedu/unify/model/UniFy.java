package seedu.unify.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.UniqueTaskList;

/**
 * Wraps all data at the unify level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class UniFy implements ReadOnlyUniFy {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public UniFy() {}

    /**
     * Creates an UniFy using the Tasks in the {@code toBeCopied}
     */
    public UniFy(ReadOnlyUniFy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code UniFy} with {@code newData}.
     */
    public void resetData(ReadOnlyUniFy newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the unify.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the unify.
     * The task must not already exist in the unify.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the unify.
     * The task identity of {@code editedTask} must not be the same as another existing task in the unify.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code UniFy}.
     * {@code key} must exist in the unify.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }


    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniFy // instanceof handles nulls
                && tasks.equals(((UniFy) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
