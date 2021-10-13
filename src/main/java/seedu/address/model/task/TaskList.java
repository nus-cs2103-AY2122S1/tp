package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.TaskNotFoundException;

public class TaskList implements Iterable<Task> {
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Adds a task to the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        /* if (contains(toAdd)) {
            throw new DuplicateTaskException();
        } */
        internalList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        /*if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }*/

        internalList.set(index, editedTask);
    }

    /**
     * Replaces the task specified by the given {@code index} in the list with {@code editedTask}.
     */
    public void setTask(int index, Task editedTask) {
        requireNonNull(editedTask);
        if (index < 0 || index >= internalList.size()) {
            throw new TaskNotFoundException();
        }
        internalList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Removes the task specified by the index from the list.
     * The task must exist in the list.
     */
    public void remove(int index) {
        if (index < 0 || index >= internalList.size()) {
            throw new TaskNotFoundException();
        }
        internalList.remove(index);
    }

    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        /*if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }*/

        internalList.setAll(tasks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList tasks = (TaskList) o;
        return internalList.equals(tasks.internalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalList, internalUnmodifiableList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Tasks: ");
        this.internalList.forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).equals(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
