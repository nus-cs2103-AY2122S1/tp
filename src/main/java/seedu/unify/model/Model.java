package seedu.unify.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.unify.commons.core.GuiSettings;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.WeeklyTasks;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' unify file path.
     */
    Path getUniFyFilePath();

    /**
     * Sets the user prefs' unify file path.
     */
    void setUniFyFilePath(Path uniFyFilePath);

    /**
     * Replaces unify data with the data in {@code uniFy}.
     */
    void setUniFy(ReadOnlyUniFy uniFy);

    /**
     * Returns the UniFy
     */
    ReadOnlyUniFy getUniFy();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the unify.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the unify.
     */
    void deleteTask(Task target);


    /**
     * Adds the given task.
     * {@code task} must not already exist in the unify.
     */
    void addTask(Task task);

    void sortTasks(Comparator<Task> f);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the unify.
     * The task identity of {@code editedTask} must not be the same as another existing task in the unify.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns a task list filtered by the week
     */
    WeeklyTasks getWeeklyTasks();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateWeeklyTaskList(Integer week);

    /**
     * Updates the state of the weekly tasks.
     */
    void updateWeeklyProgress();
}
