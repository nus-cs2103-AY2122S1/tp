package seedu.tuitione.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.remark.Remark;

/**
 * Represents a Student in the tuitione book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    public static final int MAX_ENROLLMENT_SIZE = 10;
    public static final int MAX_REMARK_SIZE = 5;

    public static final String STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT = "Student %1$s has enrolled for "
            + MAX_ENROLLMENT_SIZE + " lessons already.";
    public static final String REMARK_COUNT_CONSTRAINT = "You can only tag a maximum of " + MAX_REMARK_SIZE
            + " remarks to a student.";

    // Identity fields
    private final Name name;
    private final ParentContact parentContact;
    private final Email email;

    // Data fields
    private final Address address;
    private final Grade grade;
    private final Set<Remark> remarks = new HashSet<>();
    private final List<Lesson> lessons = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, ParentContact parentContact, Email email, Address address,
            Grade grade, Set<Remark> remarks) {

        requireAllNonNull(name, parentContact, email, address, remarks);

        this.name = name;
        this.parentContact = parentContact;
        this.email = email;
        this.address = address;
        this.grade = grade;
        this.remarks.addAll(remarks);
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
     * Returns an immutable remark set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Remark> getRemarks() {
        return Collections.unmodifiableSet(remarks);
    }

    /**
     * Returns the number of lessons the Student has enrolled in.
     */
    public int getNumberOfLessonsEnrolled() {
        return lessons.size();
    }

    /**
     * Returns an immutable lessons list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessons);
    }

    /**
     * Returns an immutable lesson code list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<LessonCode> getLessonCodes() {
        return lessons.stream()
                .map(Lesson::getLessonCode)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Returns the Student's weekly subscription price.
     */
    public double getSubscriptionPrice() {
        return lessons.stream()
                .map(l -> l.getPrice().value)
                .reduce(0.0, Double::sum);
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

    public boolean containsLesson(Lesson lesson) {
        return lessons.stream().anyMatch(l -> l.isSameLesson(lesson));
    }

    /**
     * Adds lesson to student instance.
     */
    public void enrollForLesson(Lesson lesson) {
        requireNonNull(lesson);
        if (!containsLesson(lesson)) {
            lesson.enrollStudent(this);
        }
    }

    /**
     * Remove lesson from student instance.
     */
    public void unenrollFromLesson(Lesson lesson) {
        requireNonNull(lesson);
        lesson.unenrollStudent(this);
    }

    /**
     * Adds the lesson into the student's lessons list.
     */
    public void addLesson(Lesson lesson) {
        requireNonNull(lesson);
        checkArgument(isAbleToEnrollForMoreLessons(),
                String.format(STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT, this.getName()));

        if (!containsLesson(lesson)) {
            lessons.add(lesson);
        }
    }

    /**
     * Updates a lesson in the student instance.
     */
    public void updateLesson(Lesson oldLesson, Lesson newLesson) {
        requireAllNonNull(oldLesson, newLesson);
        for (int idx = 0; idx < lessons.size(); idx++) {
            if (lessons.get(idx).isSameLesson(oldLesson)) {
                lessons.set(idx, newLesson);
                return;
            }
        }
    }

    /**
     * Removes the lesson from the student's lessons list.
     */
    public void removeLesson(Lesson lesson) {
        requireNonNull(lesson);
        lessons.remove(lesson);
    }

    /**
     * Returns true if Student is enrolled in 9 or fewer lessons
     */
    public boolean isAbleToEnrollForMoreLessons() {
        return lessons.size() < MAX_ENROLLMENT_SIZE;
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
                && otherStudent.remarks.equals(remarks)
                && otherStudent.getLessonCodes().equals(getLessonCodes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, parentContact, email, address, remarks, getLessonCodes());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("; Phone: ").append(parentContact)
                .append("; Email: ").append(email)
                .append("; Address: ").append(address)
                .append("; Grade: ").append(grade)
                .append("; ");

        if (!remarks.isEmpty()) {
            builder.append("Remark(s): ");
            remarks.forEach(t -> builder.append(t).append(" "));
            builder.insert(builder.length() - 1, ';');
        }

        if (!lessons.isEmpty()) {
            builder.append("Lesson(s): ");
            getLessonCodes().forEach(lc -> builder.append(lc).append(" "));
        }
        return builder.toString();
    }
}
