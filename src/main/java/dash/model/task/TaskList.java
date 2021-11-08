package dash.model.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dash.commons.util.CollectionUtil;
import dash.model.person.Person;
import dash.model.task.exceptions.TaskNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * A list of tasks that does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 */
public class TaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public TaskList() {
    }

    /**
     * Creates a TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskList(TaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(TaskList newData) {
        requireNonNull(newData);
        setTasks(newData.getObservableTaskList());
    }

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a task to the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the task at index {@code index} in the list with {@code editedTask}.
     */
    public void setTask(int index, Task editedTask) {
        CollectionUtil.requireAllNonNull(editedTask);
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

    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     */
    public void setTasks(List<Task> tasks) {
        CollectionUtil.requireAllNonNull(tasks);
        internalList.setAll(tasks);
    }

    /**
     * Sorts the contents of the list in the following order:
     * Completed tasks, tasks without dates, and then chronologically.
     */
    public void sortTasks() {
        Comparator<Task> c = (t1, t2) -> {
            if (t1.getCompletionStatus().get()) {
                return -1;
            }
            if (t2.getCompletionStatus().get()) {
                return 1;
            }
            if (!t1.getTaskDate().hasDate()) {
                return -1;
            }
            if (!t2.getTaskDate().hasDate()) {
                return 1;
            }

            boolean isDateAfter = t1.getTaskDate().getDate().get().isAfter(t2.getTaskDate().getDate().get());
            boolean isDateBefore = t1.getTaskDate().getDate().get().isBefore(t2.getTaskDate().getDate().get());

            if (isDateAfter) {
                return 1;
            } else if (isDateBefore) {
                return -1;
            } else if (!t1.getTaskDate().hasTime()) {
                return -1;
            } else if (!t2.getTaskDate().hasTime()) {
                return 1;
            } else {
                boolean isTimeAfter = t1.getTaskDate().getTime().get().isAfter(t2.getTaskDate().getTime().get());
                boolean isTimeBefore = t1.getTaskDate().getTime().get().isBefore(t2.getTaskDate().getTime().get());

                if (isTimeAfter) {
                    return 1;
                } else if (isTimeBefore) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        internalList.sort(c);
    }

    /**
     * Deletes the tasks in this list that are completed.
     */
    public void deleteDoneTasks() {
        List<Task> taskList = internalList.stream().filter(task -> !task.getCompletionStatus().get())
                        .collect(Collectors.toList());

        setTasks(taskList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> getObservableTaskList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the index of task to be edited.
     *
     * @param userIndexZeroBase Index of user input.
     * @param taskToEdit Task object to edit.
     * @param filteredList The current filtered Task List.
     * @return int of index to edit.
     */
    public int getIndexToEdit(int userIndexZeroBase, Task taskToEdit, List<Task> filteredList) {
        FilteredList<Task> filteredMainTaskList = internalUnmodifiableList.filtered(task -> task.equals(taskToEdit));

        if (filteredMainTaskList.size() < 2) {
            return internalUnmodifiableList.indexOf(taskToEdit);
        }
        ArrayList<Task> filteredArray = new ArrayList<>(internalUnmodifiableList);
        ArrayList<Task> arrayOfTasks = new ArrayList<>(filteredList);
        arrayOfTasks = new ArrayList<>(arrayOfTasks.subList(0, userIndexZeroBase));
        arrayOfTasks.removeIf(task -> !task.equals(taskToEdit));
        int duplicates = arrayOfTasks.size();

        int countDuplicate = 0;
        int indexToEdit = 0;
        for (int i = 0; i < filteredArray.size(); i++) {
            if (countDuplicate == duplicates) {
                indexToEdit = i;
            }
            if (filteredArray.get(i).equals(taskToEdit)) {
                countDuplicate++;
            }
        }
        return indexToEdit;
    }

    /**
     * Finds all tasks that contain personToBeReplaced, and replaces that person with newPerson.
     *
     * @param personToBeReplaced Person to be replaced.
     * @param newPerson New Person.
     */
    public void replacePeople(Person personToBeReplaced, Person newPerson) {
        for (Task t : internalList) {
            if (t.containsPerson(personToBeReplaced)) {
                Set<Person> newPersonList = new HashSet<>(t.getPeople());
                newPersonList.remove(personToBeReplaced);
                newPersonList.add(newPerson);

                Task newTask = new Task(t.getTaskDescription(), t.getCompletionStatus(), t.getTaskDate(),
                        newPersonList, t.getTags());

                setTask(internalList.indexOf(t), newTask);
            }
        }
    }

    /**
     * Deletes personToBeDeleted from all tasks.
     *
     * @param personToBeDeleted Person to be deleted.
     */
    public void deletePeople(Person personToBeDeleted) {
        for (Task t : internalList) {
            if (t.containsPerson(personToBeDeleted)) {
                Set<Person> newPersonList = new HashSet<>(t.getPeople());
                newPersonList.remove(personToBeDeleted);

                Task newTask = new Task(t.getTaskDescription(), t.getCompletionStatus(), t.getTaskDate(),
                        newPersonList, t.getTags());

                setTask(internalList.indexOf(t), newTask);
            }
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && internalList.equals(((TaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
