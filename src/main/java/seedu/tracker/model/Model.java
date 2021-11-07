package seedu.tracker.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.McProgress;
import seedu.tracker.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    void setUserInfo(ReadOnlyUserInfo userInfo);

    ReadOnlyUserInfo getUserInfo();

    AcademicCalendar getCurrentSemester();

    void setCurrentSemester(AcademicCalendar currentSemester);

    Mc getMcGoal();

    void setMcGoal(Mc mcGoal);

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
     * Returns the user prefs' moduleTracker file path.
     */
    Path getModuleTrackerFilePath();

    /**
     * Sets the user prefs' moduleTracker file path.
     */
    void setModuleTrackerFilePath(Path moduleTrackerFilePath);

    /**
     * Replaces moduleTracker data with the data in {@code moduleTracker}.
     */
    void setModuleTracker(ReadOnlyModuleTracker moduleTracker);

    /** Returns the ModuleTracker */
    ReadOnlyModuleTracker getModuleTracker();

    /**
     * Returns the list of user's mc progress.
     * @return
     */
    ObservableList<McProgress> getMcProgressList();

    /**
     * Updates the user's mc progress.
     */
    void updateMcProgress();

    /**
     * Returns true if a module with the same identity as {@code module} exists in the moduleTracker.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the mod tracker.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the mod tracker.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the mod tracker.
     * The module identity of {@code editedModule} must not be the same as another existing module in the mod tracker.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates all elements in filteredModuleList, but with no contents in filteredModuleList changed.
     */
    void updateFilteredModuleList();
}
