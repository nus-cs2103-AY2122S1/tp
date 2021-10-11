package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

public class Group {

    public static final String MESSAGE_CONSTRAINTS =
            "Group names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    // Group student list
    public final List<ID> students = new ArrayList<>();

    // Group name
    public final String value;

    /**
     * Constructs an {@code Group}.
     *
     * @param name A valid Group name.
     */
    public Group(String name) {
        requireNonNull(name);
        checkArgument(isValidGroup(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if the given string {@code test} is a valid group name.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    public List<ID> getStudents() {
        return students;
    }

    /**
     * Returns true if the given ID {@code id} specifies a student in the group.
     */
    public boolean hasStudent(ID id) {
        requireNonNull(id);
        return students.contains(id);
    }

    /**
     * Adds student with ID {@code ID} to this {@code Group}.
     * {@code id} must not already exist in the group.
     */
    public void addStudent(ID id) {
        students.add(id);
    }

    /**
     * Removes student with ID {@code key} from this {@code Group}.
     * {@code key} must exist in the group.
     */
    public void removeStudent(ID key) {
        students.remove(key);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && value.equals(((Group) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
