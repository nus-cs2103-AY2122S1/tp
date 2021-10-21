package seedu.tuitione.model.student;

import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.remark.Remark;

/**
 * Represents a Student in the tuitione book.
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
    private final Set<Remark> remarks = new HashSet<>();
    private final Map<LessonCode, Price> lessonCodesAndPrices = new HashMap<>();
    private final Set<Lesson> lessons = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name,
                   ParentContact parentContact,
                   Email email,
                   Address address,
                   Grade grade,
                   Set<Remark> remarks) {
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
     * Returns an immutable lesson code and price map, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Map<LessonCode, Price> getLessonCodesAndPrices() {
        return Collections.unmodifiableMap(lessonCodesAndPrices);
    }

    /**
     * Returns an immutable lesson code set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<LessonCode> getLessonCodes() {
        return Collections.unmodifiableSet(lessonCodesAndPrices.keySet());
    }

    /**
     * Returns the number of lessons the Student has enrolled in.
     */
    public int getNumberOfLessonsEnrolled() {
        return lessonCodesAndPrices.size();
    }

    /**
     * Returns an immutable lesson price list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Price> getLessonPrices() {
        return List.copyOf(lessonCodesAndPrices.values());
    }

    /**
     * Returns an immutable lessons set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Returns the Student's weekly subscription price.
     */
    public double getSubscriptionPrice() {
        return this.getLessonPrices().stream()
                .map(e -> e.value)
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

    /**
     * Adds lesson to student instance. Student instance holds a weaker linkage to Lessons, using its lesson code.
     */
    public void enrollForLesson(Lesson lesson) {
        lessons.add(lesson);
        lessonCodesAndPrices.put(lesson.getLessonCode(), lesson.getPrice());
    }

    /**
     * Puts the lesson codes and prices of the given input into the current students' lesson codes and prices.
     */
    public void setLessonCodesAndPrices(Map<LessonCode, Price> map) {
        lessonCodesAndPrices.putAll(map);
    }

    /**
     * Puts the lessons of the given input into the current students' lessons set.
     */
    public void setLessons(Set<Lesson> newLessons) {
        lessons.addAll(newLessons);
    }

    /**
     * Adds the lesson into the current students' lessons set.
     */
    public void addLesson(Lesson newLesson) {
        lessons.add(newLesson);
    }

    /**
     * Remove lesson from student instance. Student instance uses a weaker linkage to Lessons, using its lesson code.
     */
    public void unenrollFromLesson(Lesson lesson) {
        LessonCode codeToUnenroll = lesson.getLessonCode();
        lessonCodesAndPrices.remove(codeToUnenroll);
        lessons.remove(lesson);
    }

    /**
     * Returns a clone of the Student instance.
     */
    public Student createClone() {
        Student newStudent = new Student(name, parentContact, email, address, grade, remarks);
        newStudent.lessonCodesAndPrices.putAll(lessonCodesAndPrices);
        newStudent.lessons.addAll(lessons);
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
                && otherStudent.remarks.equals(remarks)
                && otherStudent.lessonCodesAndPrices.equals(lessonCodesAndPrices);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, parentContact, email, address, remarks, lessonCodesAndPrices);
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

        if (!lessonCodesAndPrices.isEmpty()) {
            builder.append("Lesson(s): ");
            lessonCodesAndPrices.keySet().forEach(l -> builder.append(l).append(" "));
        }
        return builder.toString();
    }
}
