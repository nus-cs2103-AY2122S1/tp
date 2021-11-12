package seedu.unify.model;

import javafx.collections.ObservableList;
import seedu.unify.model.task.Task;

/**
 * Unmodifiable view of unify tasks
 */
public interface ReadOnlyUniFy {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
