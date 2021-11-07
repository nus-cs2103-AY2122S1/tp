package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Represents a Student in the ClassMATE.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final ClassCode classCode;
    private final List<StudentMark> marks;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<TutorialGroup> tutorialGroups = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, ClassCode classCode, Set<Tag> tags,
                   List<StudentMark> marks, Set<TutorialGroup> tutorialGroups) {
        requireAllNonNull(name, phone, email, address, classCode, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.classCode = classCode;
        this.marks = marks;
        this.tags.addAll(tags);
        this.tutorialGroups.addAll(tutorialGroups);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public ClassCode getClassCode() {
        return classCode;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tutorial group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<TutorialGroup> getTutorialGroups() {
        return Collections.unmodifiableSet(tutorialGroups);
    }

    /**
     * Returns an immutable list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<StudentMark> getMarks() {
        return Collections.unmodifiableList(marks);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && this.equals(otherStudent);
    }

    /**
     * Returns true if student is already in a OP group type.
     * Checks whether student is added to multiple groups of the same type.
     * @param groupType
     * @return whether {@code Student} is already in
     */
    public boolean hasTutorialGroupType(GroupType groupType) {
        for (TutorialGroup group : tutorialGroups) {
            if (group.getGroupType().equals(groupType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if student belongs to the tutorial gorup.
     *
     * @param tutorialGroup The given tutorial group to check.
     * @return whether {@code Student} is already in the tutorial group
     */
    public boolean isBelongTutorialGroup(TutorialGroup tutorialGroup) {
        for (TutorialGroup group : tutorialGroups) {
            if (group.isSameTutorialGroup(tutorialGroup)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, classCode, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; ClassCode: ")
                .append(getClassCode());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<TutorialGroup> tutorialGroups = getTutorialGroups();
        if (!tutorialGroups.isEmpty()) {
            builder.append("; TutorialGroups: ");
            tutorialGroups.forEach(builder::append);
        }

        List<StudentMark> studentMarks = getMarks();
        if (!studentMarks.isEmpty()) {
            builder.append("; Marks: ");
            studentMarks.forEach(mark -> builder.append(mark).append(" "));
        }
        return builder.toString();
    }

}
