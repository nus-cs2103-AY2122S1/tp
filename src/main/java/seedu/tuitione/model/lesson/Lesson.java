package seedu.tuitione.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Student;

/**
 * Represents a Lesson in the tuitiONE book.
 */
public class Lesson {

    public static final String ENROLLMENT_MESSAGE_CONSTRAINT =
            "%1$s is unable to enroll for this lesson";
    public static final String EXCEED_ENROLLMENT_MESSAGE_CONSTRAINT =
            "%1$s currently has %2$s students enrolled, "
            + "and cannot enroll anymore students.";
    public static final String STUDENT_NOT_ENROLLED = "⚠\tAlert:\n\n%1$s is not enrolled for %2$s";
    public static final int MAX_STUDENT_SIZE = 15;

    private final Subject subject;
    private final Grade grade;
    private final LessonTime lessonTime;
    private final Price price;
    private final LessonCode lessonCode;
    private final List<Student> students;

    /**
     * Constructs a {@code Lesson}.
     *
     * @param subject A Subject for lesson.
     * @param grade Grade of lesson.
     * @param lessonTime A lesson time.
     * @param price Price of lesson.
     */
    public Lesson(Subject subject, Grade grade, LessonTime lessonTime, Price price) {
        requireAllNonNull(subject, grade, lessonTime, price);

        this.subject = subject;
        this.grade = grade;
        this.lessonTime = lessonTime;
        this.price = price;
        this.lessonCode = LessonCode.getLessonCode(subject, grade, lessonTime);
        this.students = new ArrayList<>();
    }

    /**
     * Returns the subject of lesson.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns the grade of lesson.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Returns the lesson time.
     */
    public LessonTime getLessonTime() {
        return lessonTime;
    }

    /**
     * Returns the price of lesson.
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Returns the lesson code.
     */
    public LessonCode getLessonCode() {
        return lessonCode;
    }

    /**
     * Returns an unmodifiable list of student for equality checks.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Returns the number of students attending the lesson.
     */
    public int getLessonSize() {
        return students.size();
    }

    /**
     * Returns true if both lessons have the same lesson code.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }
        return (otherLesson != null) && otherLesson.lessonCode.equals(lessonCode);
    }

    /**
     * Returns true if Lesson has 14 or fewer Students enrolled.
     */
    public boolean isAbleToEnrollMoreStudents() {
        return students.size() < MAX_STUDENT_SIZE;
    }

    /**
     * Returns true if a student is eligible to enroll for the lesson.
     * A student must be of the same grade, must be available for the time slot,
     * and must not be already enrolled to the lesson.
     */
    public boolean isAbleToEnroll(Student student) {
        if (student == null || !student.isAbleToEnrollForMoreLessons()) {
            return false;
        }
        if (containsStudent(student)) {
            return false;
        }
        if (!student.getGrade().equals(grade)) {
            return false;
        }
        for (Lesson lesson : student.getLessons()) {
            LessonTime time = lesson.getLessonTime();
            if (time.hasOverlappedTiming(lessonTime)) {
                return false;
            }
        }
        return isAbleToEnrollMoreStudents();
    }

    /**
     * Returns true if a student is eligible to unenroll from the lesson.
     * A student must be already enrolled to the lesson.
     * @param student the student to unenroll from this lesson.
     * @return a boolean to represent whether the student can be unenrolled from the lesson.
     */
    public boolean isAbleToUnenroll(Student student) {
        requireNonNull(student);
        return containsStudent(student);
    }

    /**
     * Checks if Student is enrolled in this Lesson.
     */
    public boolean containsStudent(Student student) {
        return students.stream().anyMatch(s -> s.isSameStudent(student));
    }

    /**
     * Add student to the lesson instance.
     */
    public void enrollStudent(Student student) {
        requireNonNull(student);
        checkArgument(isAbleToEnroll(student), String.format(ENROLLMENT_MESSAGE_CONSTRAINT, student.getName()));
        addStudent(student);
    }

    /**
     * Removes student from the lesson instance.
     */
    public void unenrollStudent(Student student) {
        requireNonNull(student);
        checkArgument(isAbleToUnenroll(student), String.format(STUDENT_NOT_ENROLLED, student.getName(), this));
        for (Student s : students) {
            if (s.isSameStudent(student)) {
                removeStudent(s);
                return;
            }
        }
    }

    /**
     * Adds student to the lesson instance, without any checks.
     */
    public void addStudent(Student student) {
        requireNonNull(student);
        if (!containsStudent(student)) {
            students.add(student);
            student.addLesson(this);
        }
    }

    /**
     * Updates a student in the lesson instance.
     */
    public void updateStudent(Student oldStudent, Student newStudent) {
        requireAllNonNull(oldStudent, newStudent);
        checkArgument(isAbleToUnenroll(oldStudent), String.format(STUDENT_NOT_ENROLLED, oldStudent.getName(), this));

        for (Student student : students) {
            if (student.isSameStudent(oldStudent)) {
                removeStudent(oldStudent);
                checkArgument(isAbleToEnroll(newStudent),
                        String.format(ENROLLMENT_MESSAGE_CONSTRAINT, newStudent.getName()));
                addStudent(newStudent);
                return;
            }
        }
    }

    /**
     * Removes student to the lesson instance, without any checks.
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        students.remove(student);
        student.removeLesson(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, grade, lessonTime, price, students);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }
        // state check
        Lesson otherLesson = (Lesson) other;
        return subject.equals(otherLesson.subject)
                && grade.equals(otherLesson.grade)
                && lessonTime.equals(otherLesson.lessonTime)
                && price.equals(otherLesson.price)
                && lessonCode.equals(otherLesson.lessonCode)
                && students.equals(otherLesson.students);
    }

    @Override
    public String toString() {
        return lessonCode.toString();
    }
}
