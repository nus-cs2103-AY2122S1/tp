package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;

/**
 * Represents the in-memory model of the mod tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModTracker modTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given modTracker and userPrefs.
     */
    public ModelManager(ReadOnlyModTracker modTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(modTracker, userPrefs);

        logger.fine("Initializing with mod tracker: " + modTracker + " and user prefs " + userPrefs);

        this.modTracker = new ModTracker(modTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredModules = new FilteredList<>(this.modTracker.getModuleList());
    }

    public ModelManager() {
        this(new ModTracker(), new UserPrefs());
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
    public Path getModTrackerFilePath() {
        return userPrefs.getModTrackerFilePath();
    }

    @Override
    public void setModTrackerFilePath(Path modTrackerFilePath) {
        requireNonNull(modTrackerFilePath);
        userPrefs.setModTrackerFilePath(modTrackerFilePath);
    }

    //=========== ModTracker ================================================================================

    @Override
    public void setModTracker(ReadOnlyModTracker modTracker) {
        this.modTracker.resetData(modTracker);
    }

    @Override
    public ReadOnlyModTracker getModTracker() {
        return modTracker;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modTracker.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        modTracker.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        modTracker.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        modTracker.setModule(target, editedModule);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedModTracker}
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
        return modTracker.equals(other.modTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules);
    }

}
