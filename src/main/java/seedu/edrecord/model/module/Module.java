package seedu.edrecord.model.module;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.UniqueAssignmentList;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.group.GroupSystem;
import seedu.edrecord.model.group.ReadOnlyGroupSystem;
import seedu.edrecord.model.name.Name;

/**
 * Represents a module in EdRecord.
 * Guarantees: immutable; is always valid
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module code must be alphanumeric. Only 1-8 characters allowed.";
    public static final String MESSAGE_INVALID_JSON = "Module code cannot be saved with lower case or white spaces.";
    public static final String MESSAGE_DOES_NOT_EXIST = "Module %s has yet to be created.";
    public static final String MESSAGE_DUPLICATE = "Module with that code has already been created.";
    public static final String MESSAGE_MODULE_GROUPS_DOES_NOT_EXIST = "Module and/or Group has not been created";

    /* The module code must not have any whitespace characters. */
    public static final String VALIDATION_REGEX = "^\\p{Alnum}{1,8}$";

    public static final ModuleSystem MODULE_SYSTEM = new ModuleSystem();

    private final String code;
    private final GroupSystem groupSystem;
    private final UniqueAssignmentList assignmentList;

    /**
     * Constructs a {@code Module}.
     *
     * @param code        A valid module code.
     * @param groupSystem A valid group system.
     */
    public Module(String code, GroupSystem groupSystem) {
        requireNonNull(code);
        this.code = code;

        requireNonNull(groupSystem);
        this.groupSystem = groupSystem;
        this.assignmentList = new UniqueAssignmentList();
    }

    /**
     * Constructs a {@code Module} containing an empty list of assignments.
     *
     * @param code A valid module code.
     */
    public Module(String code) {
        requireNonNull(code);
        this.code = code.toUpperCase();
        this.groupSystem = new GroupSystem();
        this.assignmentList = new UniqueAssignmentList();
    }

    public String getCode() {
        return code;
    }

    public GroupSystem getGroupSystem() {
        return groupSystem;
    }

    /**
     * Returns a view of this module's assignment list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> getAssignmentList() {
        return assignmentList.asUnmodifiableObservableList();
    }

    /**
     * Returns a view of this module's class list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Group> getGroupList() {
        return groupSystem.getGroupList();
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid parsed module code.
     */
    public static boolean isValidSavedModuleCode(String test) {
        return isValidModuleCode(test) && test.equals(test.toUpperCase().trim());
    }

    /**
     * Returns true if the module given has the same module code.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }
        if (otherModule == null) {
            return false;
        }
        return code.equalsIgnoreCase(otherModule.getCode());
    }

    public void setGroupSystem(ReadOnlyGroupSystem groupSystem) {
        this.groupSystem.resetData(groupSystem);
    }

    /**
     * Returns true if groupSystem has a group with the same group code.
     */
    public boolean hasGroup(Group grp) {
        requireNonNull(grp);
        return groupSystem.hasGroup(grp);
    }

    public void deleteGroup(Group target) {
        groupSystem.removeGroup(target);
    }

    public void addGroup(Group toAdd) {
        groupSystem.addGroup(toAdd);
    }

    public Group getGroup(String groupCode) {
        return groupSystem.getGroup(groupCode);
    }

    /**
     * Returns true if the module contains an assignment that is equivalent to the given assignment.
     */
    public boolean hasAssignment(Assignment a) {
        return assignmentList.contains(a);
    }

    /**
     * Returns true if adding the assignment {@code toAdd} will bring
     * the total weightage of all assignments to above 100%.
     */
    public boolean isTotalWeightageExceeded(Assignment toAdd) {
        return assignmentList.isTotalWeightageExceeded(toAdd);
    }

    /**
     * Returns an {@code Optional} containing the assignment with the given name, if it exists.
     */
    public Optional<Assignment> searchAssignment(Name name) {
        return assignmentList.searchAssignment(name);
    }

    /**
     * Adds an assignment to the assignment list under this module.
     * The assignment to be added must not already exist in the assignment list.
     */
    public void addAssignment(Assignment a) {
        assignmentList.add(a);
    }

    /**
     * Removes Assignment {@code key} from this {@code Module}.
     * {@code key} must exist in this module.
     */
    public void deleteAssignment(Assignment key) {
        assignmentList.remove(key);
    }

    /**
     * Replaces the given Assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in EdRecord under this module.
     * The identity of {@code editedAssignment} must not be the same as any existing assignment under the same module.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        assignmentList.setAssignment(target, editedAssignment);
    }

    @Override
    public String toString() {
        return this.getCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Module)) {
            return false;
        }
        Module otherModule = (Module) other;
        return code.equalsIgnoreCase(otherModule.code)
                && groupSystem.equals(((Module) other).groupSystem)
                && assignmentList.equals(otherModule.assignmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, groupSystem);
    }
}
