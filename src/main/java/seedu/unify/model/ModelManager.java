package seedu.unify.model;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.unify.commons.core.GuiSettings;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.WeeklyTasks;

/**
 * Represents the in-memory model of the unify data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UniFy uniFy;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTask;
    private final WeeklyTasks weeklyTasks;

    /**
     * Initializes a ModelManager with the given uniFy and userPrefs.
     */
    public ModelManager(ReadOnlyUniFy uniFy, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(uniFy, userPrefs);

        logger.fine("Initializing with unify: " + uniFy + " and user prefs " + userPrefs);

        this.uniFy = new UniFy(uniFy);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTask = new FilteredList<>(this.uniFy.getTaskList());
        weeklyTasks = new WeeklyTasks(filteredTask);
    }

    public ModelManager() {
        this(new UniFy(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUniFyFilePath() {
        return userPrefs.getUniFyFilePath();
    }

    @Override
    public void setUniFyFilePath(Path uniFyFilePath) {
        requireNonNull(uniFyFilePath);
        userPrefs.setUniFyFilePath(uniFyFilePath);
    }

    //=========== UniFy ================================================================================

    @Override
    public void setUniFy(ReadOnlyUniFy uniFy) {
        this.uniFy.resetData(uniFy);
        updateWeeklyProgress();
    }

    @Override
    public ReadOnlyUniFy getUniFy() {
        return uniFy;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return uniFy.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        uniFy.removeTask(target);
        updateWeeklyProgress();
    }

    @Override
    public void addTask(Task task) {
        uniFy.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void sortTasks(Comparator<Task> f) {
        uniFy.sortTasks(f);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        uniFy.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTask;
    }

    @Override
    public WeeklyTasks getWeeklyTasks() {
        return weeklyTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTask.setPredicate(predicate);
        updateWeeklyProgress();
    }

    @Override
    public void updateWeeklyTaskList(Integer week) {
        requireNonNull(week);
        weeklyTasks.setWeek(week);
    }

    @Override
    public void updateWeeklyProgress() {
        weeklyTasks.updateWeeklyProgress();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return uniFy.equals(other.uniFy)
                && userPrefs.equals(other.userPrefs)
                && filteredTask.equals(other.filteredTask);
    }

}
