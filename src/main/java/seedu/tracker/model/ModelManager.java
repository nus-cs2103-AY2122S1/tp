package seedu.tracker.model;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.commons.core.LogsCenter;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.McProgress;
import seedu.tracker.model.module.Module;

/**
 * Represents the in-memory model of the mod tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleTracker moduleTracker;
    private final UserPrefs userPrefs;
    private final UserInfo userInfo;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given moduleTracker and userPrefs.
     */
    public ModelManager(ReadOnlyModuleTracker moduleTracker, ReadOnlyUserPrefs userPrefs, ReadOnlyUserInfo userInfo) {
        super();
        requireAllNonNull(moduleTracker, userPrefs, userInfo);

        logger.fine("Initializing with mod tracker: " + moduleTracker + ", user prefs " + userPrefs
                + " and user information: " + userInfo);

        this.moduleTracker = new ModuleTracker(moduleTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        this.userInfo = new UserInfo(userInfo);
        updateMcProgress();
        filteredModules = new FilteredList<>(this.moduleTracker.getModuleList());
    }

    public ModelManager() {
        this(new ModuleTracker(), new UserPrefs(), new UserInfo());
    }

    //=========== UserInfo =============================================================
    @Override
    public void setUserInfo(ReadOnlyUserInfo userInfo) {
        requireNonNull(userInfo);
        this.userInfo.resetData(userInfo);
        updateMcProgress();
    }

    @Override
    public ReadOnlyUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public AcademicCalendar getCurrentSemester() {
        return userInfo.getCurrentSemester();
    }

    @Override
    public void setCurrentSemester(AcademicCalendar currentSemester) {
        requireNonNull(currentSemester);
        this.userInfo.setCurrentSemester(currentSemester);
        updateMcProgress();
    }

    @Override
    public Mc getMcGoal() {
        return userInfo.getMcGoal();
    }
    @Override
    public void setMcGoal(Mc mcGoal) {
        requireNonNull(mcGoal);
        this.userInfo.setMcGoal(mcGoal);
    }

    @Override
    public ObservableList<McProgress> getMcProgressList() {
        return moduleTracker.getMcProgressList();
    }

    @Override
    public void updateMcProgress() {
        moduleTracker.updateMcProgressList(this.userInfo);
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
    public Path getModuleTrackerFilePath() {
        return userPrefs.getModuleTrackerFilePath();
    }

    @Override
    public void setModuleTrackerFilePath(Path moduleTrackerFilePath) {
        requireNonNull(moduleTrackerFilePath);
        userPrefs.setModuleTrackerFilePath(moduleTrackerFilePath);
    }

    //=========== ModuleTracker ================================================================================

    @Override
    public void setModuleTracker(ReadOnlyModuleTracker moduleTracker) {
        this.moduleTracker.resetData(moduleTracker);
    }

    @Override
    public ReadOnlyModuleTracker getModuleTracker() {
        return moduleTracker;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleTracker.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        moduleTracker.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        moduleTracker.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        moduleTracker.setModule(target, editedModule);
    }

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedModuleTracker}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }


    @Override
    public void updateFilteredModuleList() {
        for (Module m : filteredModules) {
            setModule(m, m);
        }
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
        return moduleTracker.equals(other.moduleTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules);
    }

}
