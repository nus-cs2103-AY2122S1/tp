package seedu.address.model;

import java.util.Map;
import java.util.Set;

import seedu.address.model.id.UniqueId;

public interface TaskAssignable {
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    Set<UniqueId> getAssignedTaskIds();

    /**
     * Returns a map of a task id to that task's completion
     * Does nothing if the TaskAssignable does not support task completion
     */
    Map<UniqueId, Boolean> getTasksCompletion();

    /**
     * Gets the name in type {@code String}.
     *
     * @return The name in string.
     */
    String getNameInString();

    /**
     * Immutable way of updating the assigned task id list
     *
     * @param newAssignedTaskIds the new assigned task id list
     * @return new TaskAssignable instance with the updated assigned task id list
     */
    TaskAssignable updateAssignedTaskIds(Set<UniqueId> newAssignedTaskIds);

    /**
     * Immutable way of updating the task completion map
     * Does nothing if the TaskAssignable does not support task completion
     *
     * @param newTasksCompletion the new task completion map
     * @return new TaskAssignable instance with the updated task completion map
     */
    TaskAssignable updateTasksCompletion(Map<UniqueId, Boolean> newTasksCompletion);

    /**
     * This defines a weaker notion of equality between two {@code TaskAssignable}s.
     */
    boolean isSameTaskAssignable(TaskAssignable otherTaskAssignable);

    /**
     * Checks if the {@code TaskAssignable} is already in the given model.
     *
     * @param model The model to be checked.
     * @return true if the instance is in the model; false otherwise.
     */
    boolean isInModel(Model model);
}
