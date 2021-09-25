package seedu.address.model.group;

import seedu.address.model.person.Student;

import java.util.ArrayList;
import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Group in the CSBook.
 * Guarantee: groupName is present and not null, field values are validated, immutable.
 */
public class Group {
    // Identity fields
    private final GroupName groupName;
    private final Description description;

    // Data fields
    private final ArrayList<Student> students;

    /**
     * Every field must be present and not null.
     */
    public Group(GroupName groupName, Description description) {
        requireAllNonNull(groupName, description);
        this.groupName = groupName;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public Description getDescription() {
        return description;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Returns true if both groups have the same GroupName.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getGroupName().equals(getGroupName());
    }

    /**
     * Returns true if both Groups have the same GroupName and list of students
     * This defines a stronger notion of equality between two groups.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getGroupName().equals(getGroupName())
                && getDescription().equals(otherGroup.getDescription())
                && getStudents().equals(otherGroup.getStudents());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(groupName, students, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getGroupName())
                .append("; Description: ")
                .append(getDescription());


        ArrayList<Student> students = getStudents();
        if(!students.isEmpty()) {
            builder.append("; Students: ");
            students.forEach(builder::append);
        }
        return builder.toString();
    }

}
