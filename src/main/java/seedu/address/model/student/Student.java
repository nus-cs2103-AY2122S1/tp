package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final ParentContact parentContact;
    private final Email email;

    // Data fields
    private final Address address;
    private final Grade grade;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<LessonCode> lessonCodes = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, ParentContact parentContact, Email email, Address address, Grade grade, Set<Tag> tags) {
        requireAllNonNull(name, parentContact, email, address, tags);

        this.name = name;
        this.parentContact = parentContact;
        this.email = email;
        this.address = address;
        this.grade = grade;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public ParentContact getParentContact() {
        return parentContact;
    }

    public Grade getGrade() {
        return grade;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable lesson code set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<LessonCode> getLessonCodes() {
        return Collections.unmodifiableSet(lessonCodes);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }
        return (otherStudent != null) && otherStudent.name.equals(name);
    }

    /**
     * Adds lesson to student instance. Student instance holds a weaker linkage to Lessons, using its lesson code.
     */
    public void enrollForLesson(Lesson lesson) {
        lessonCodes.add(lesson.getLessonCode());
    }

    /**
     * Remove lesson from student instance. Student instance uses a weaker linkage to Lessons, using its lesson code.
     */
    public void unenrollFromLesson(Lesson lesson) {
        LessonCode codeToUnenroll = lesson.getLessonCode();
        lessonCodes.remove(codeToUnenroll);
    }

    /**
     * Returns a clone of the Student instance.
     */
    public Student createClone() {
        Student newStudent = new Student(name, parentContact, email, address, grade, tags);
        newStudent.lessonCodes.addAll(lessonCodes);
        return newStudent;
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
        return otherStudent.name.equals(name)
                && otherStudent.parentContact.equals(parentContact)
                && otherStudent.email.equals(email)
                && otherStudent.address.equals(address)
                && otherStudent.grade.equals(grade)
                && otherStudent.tags.equals(tags)
                && otherStudent.lessonCodes.equals(lessonCodes);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, parentContact, email, address, tags, lessonCodes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("; Phone: ").append(parentContact)
                .append("; Email: ").append(email)
                .append("; Address: ").append(address)
                .append("; Grade: ").append(grade);
        if (!tags.isEmpty()) {
            builder.append("; Tag(s): ");
            tags.forEach(t -> builder.append(t).append(" "));
        }
        if (!lessonCodes.isEmpty()) {
            builder.append("; Lesson(s): ");
            lessonCodes.forEach(l -> builder.append(l).append(" "));
        }
        return builder.toString();
    }
}
