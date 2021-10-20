package seedu.address.model;

import java.util.List;
import java.util.Set;

import seedu.address.model.id.UniqueId;

public interface TaskAssignable {
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    Set<UniqueId> getAssignedTaskIds();

    /**
     * Immutable way of updating the assigned task id list
     *
     * @param newAssignedTaskIds the new assigned task id list
     * @return new TaskAssignable instance with the updated assigned task id list
     */
    TaskAssignable updateAssignedTaskIds(Set<UniqueId> newAssignedTaskIds);

    /**
     * Gets the filter list from the given model.
     *
     * @param model The model that stores the filter list.
     * @return The filter list from the given model.
     */
    List<? extends TaskAssignable> getFilteredListFromModel(Model model);
}
