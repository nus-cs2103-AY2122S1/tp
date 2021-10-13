package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Email email;
    private final StudentNumber studentNumber;

    // Data fields
    private final Attendance attendance;
    private final Participation participation;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     * Constructor for a new Person object
     */
    public Student(Name name, Email email, StudentNumber studentNumber, Set<Tag> tags) {
        requireAllNonNull(name, email, studentNumber, tags);
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.tags.addAll(tags);
        this.attendance = new Attendance();
        this.participation = new Participation();
    }

    /**
     * Constructor for a re-stored Person object
     */
    public Student(Name name, Email email, StudentNumber studentNumber, Set<Tag> tags, Attendance attendance) {
        requireAllNonNull(name, email, studentNumber, tags, attendance);
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.tags.addAll(tags);
        this.attendance = attendance;
        this.participation = new Participation();
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Participation getParticipation() {
        return participation;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherStudent.getName().equals(getName());
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
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getStudentNumber().equals((getStudentNumber()))
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, tags, attendance, studentNumber);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Student Number: ")
                .append(getStudentNumber());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
