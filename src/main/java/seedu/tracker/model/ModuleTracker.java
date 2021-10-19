package seedu.tracker.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.UniqueModuleList;

/**
 * Wraps all data at the mod-tracker level
 * Duplicates are not allowed (by .isSameModule comparison)
 */
public class ModuleTracker implements ReadOnlyModuleTracker {

    private final UniqueModuleList modules;
    private AcademicCalendar currentSemester;
    private Mc mcGoal;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        AcademicYear defaultAcademicYear = new AcademicYear(1);
        Semester defaultSemester = new Semester(1);
        currentSemester = new AcademicCalendar(defaultAcademicYear, defaultSemester);
        modules = new UniqueModuleList();
        mcGoal = new Mc(160);
    }

    public ModuleTracker() {}

    /**
     * Creates an ModuleTracker using the Modules in the {@code toBeCopied}
     */
    public ModuleTracker(ReadOnlyModuleTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setCurrentSemester(AcademicCalendar academicCalendar) {
        this.currentSemester = academicCalendar;
    }

    public AcademicCalendar getCurrentSemester() {
        return this.currentSemester;
    }

    public void setMcGoal(Mc mcGoal) {
        this.mcGoal = mcGoal;
    }

    public Mc getMcGoal() {
        return this.mcGoal;
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
