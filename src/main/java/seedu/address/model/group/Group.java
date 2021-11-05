package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.student.Name;

/**
 * Represents a Group in the CSBook.
 * Guarantee: groupName is present and not null, field values are validated, immutable.
 */
public class Group {
    // Identity fields
    private final GroupName groupName;
    private final Description description;

    // Data fields
    private final Set<Name> studentNames = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Group(GroupName groupName, Description description) {
        requireAllNonNull(groupName, description);
        this.groupName = groupName;
        this.description = description;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public Description getDescription() {
        return description;
    }

    public Set<Name> getStudentNames() {
        return Collections.unmodifiableSet(studentNames);
    }

    public void addStudentName(Name studentName) {
        studentNames.add(studentName);
    }

    public void addAllStudentNames(Collection<Name> studentNames) {
        this.studentNames.addAll(studentNames);
    }

    public void removeStudentName(Name studentName) {
        studentNames.remove(studentName);
    }

    /**
     * Changes an indicated student reference (by Name) to a new one
     */
    public void updateStudentName(Name studentName, Name newStudentName) {
        removeStudentName(studentName);
        addStudentName(newStudentName);
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
     * Returns true if the group has no assigned students
     */
    public boolean hasNoStudents() {
        return studentNames.isEmpty();
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
                && getStudentNames().equals(otherGroup.getStudentNames());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(groupName, studentNames, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getGroupName())
                .append("; Description: ")
                .append(getDescription());


        Set<Name> studentNames = getStudentNames();
        if (!studentNames.isEmpty()) {
            builder.append("; Students: ");
            studentNames.forEach(student -> builder.append(student).append(", "));
            builder.delete(builder.lastIndexOf(", "), builder.length() - 1); // Remove last delimiter
        }
        return builder.toString();
    }

}
