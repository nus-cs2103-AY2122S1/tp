package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TaskListManager implements ReadOnlyTaskList {

    private final TaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new TaskList();
    }

    public TaskListManager() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public TaskListManager(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the tasks list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        setTasks(newData.getTaskList());
    }

    //// task-level operations

    public void addTask(Task toAdd) {
        tasks.add(toAdd);
    }

    public void deleteTask(Task toDelete) {
        tasks.remove(toDelete);
    }

    public void markDone(Task task) {
        tasks.markDone(task);
    }

    public void setTask(Task target, Task editedtask) {
        tasks.setTask(target, editedtask);
    }

    public boolean hasTask(Task task) {
        return tasks.hasTask(task);
    }


    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

}
