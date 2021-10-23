package seedu.tracker.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.McProgress;
import seedu.tracker.model.module.McProgressList;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.UniqueModuleList;

/**
 * Wraps all data at the mod-tracker level
 * Duplicates are not allowed (by .isSameModule comparison)
 */
public class ModuleTracker implements ReadOnlyModuleTracker {

    private final UniqueModuleList modules;
    private McProgressList mcProgressList;
    private final UserInfo userInfo;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
        userInfo = new UserInfo();
        mcProgressList = new McProgressList(userInfo);
    }

    public ModuleTracker() {}

    /**
     * Creates an ModuleTracker using the Modules in the {@code toBeCopied}
     */
    public ModuleTracker(ReadOnlyModuleTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public ObservableList<McProgress> getMcProgressList() {
        return mcProgressList.getMcProgressList();
    }

    public void updateMcProgressList(UserInfo userInfo) {
        this.mcProgressList.update(this.modules.asUnmodifiableObservableList(), userInfo);
    }

    //// list overwrite operations

    public void setCurrentSemester(AcademicCalendar academicCalendar) {
        this.userInfo.setCurrentSemester(academicCalendar);
        updateMcProgressList(userInfo);
    }

    public AcademicCalendar getCurrentSemester() {
        return this.userInfo.getCurrentSemester();
    }

    public void setMcGoal(Mc mcGoal) {
        this.userInfo.setMcGoal(mcGoal);
    }

    public Mc getMcGoal() {
        return this.userInfo.getMcGoal();
    }

    /**
     * Replaces the contents of the Module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code ModuleTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyModuleTracker newData) {
        requireNonNull(newData);
        setModules(newData.getModuleList());
        mcProgressList.update(newData.getModuleList(), userInfo);
    }

    //// module-level operations

    /**
     * Returns true if a module with the same identity as {@code module} exists in the mod tracker.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to the mod tracker.
     * The module must not already exist in the mod tracker.
     */
    public void addModule(Module m) {
        modules.add(m);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the mod tracker.
     * The module identity of {@code editedModule} must not be the same as another existing module in the mod tracker.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code ModuleTracker}.
     * {@code key} must exist in the mod tracker.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTracker // instanceof handles nulls
                && modules.equals(((ModuleTracker) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
