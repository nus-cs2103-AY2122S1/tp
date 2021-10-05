package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * A list of tasks supporting minimal list operations. Implementation mirrors UniquePersonList to hopefully make ui
 * implementation more straightforward.
 */
public class TaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return internalList.stream().anyMatch(task::equals);
    }

    /**
     * Adds a task to the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes a task from the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }


    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedTask) && hasTask(editedTask)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Marks a task as done.
     */
    public void markDone(Task toMark) {
        requireNonNull(toMark);
        if (!hasTask(toMark)) {
            throw new TaskNotFoundException();
        }
        int index = internalList.indexOf(toMark);
        internalList.get(index).setIsDone(true);
    }

    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TaskList
                && internalList.equals(((TaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
