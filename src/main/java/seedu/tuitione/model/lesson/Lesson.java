package seedu.tuitione.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tuitione.model.student.Student.STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT;

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

    public static final int MAX_LESSON_SIZE = 15;

    public static final String STUDENT_NOT_ENROLLED_CONSTRAINT = "%1$s is not enrolled for %2$s";
    public static final String STUDENT_ALREADY_ENROLLED_CONSTRAINT = "%1$s is already enrolled for %2$s";
    public static final String DIFFERENT_GRADE_CONSTRAINT = "%1$s is in a different grade from %2$s";
    public static final String CONFLICTING_TIMINGS_CONSTRAINT = "%1$s has enrolled for lessons "
            + "that conflict with %2$s";
    public static final String LESSON_ENROLLMENT_MESSAGE_CONSTRAINT = "%1$s currently has " + MAX_LESSON_SIZE
            + " students enrolled, and cannot enroll anymore students.";

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
     * Returns true if Lesson lesser than MAX_LESSON_SIZE Students enrolled, else false.
     */
    public boolean isAbleToEnrollMoreStudents() {
        return students.size() < MAX_LESSON_SIZE;
    }

    /**
     * Returns true if Lesson and Student are of the same grade, else false.
     */
    public boolean isStudentOfSameGrade(Student student) {
        requireNonNull(student);
        return student.getGrade().equals(grade);
    }

    /**
     * Returns true if {@code otherLesson} conflicts with instance's timing, else false.
     */
    public boolean hasConflictingTimings(Lesson otherLesson) {
        requireNonNull(otherLesson);
        LessonTime otherTime = otherLesson.getLessonTime();
        return otherTime.hasOverlappedTiming(lessonTime);
    }

    /**
     * Returns true if Student has enrolled for lessons that conflicts with instance's timing, else false.
     */
    public boolean doesStudentHaveConflictingTimings(Student student) {
        requireNonNull(student);
        return student.getLessons().stream().anyMatch(this::hasConflictingTimings);
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
        requireNonNull(student);
        return students.stream().anyMatch(s -> s.isSameStudent(student));
    }

    /**
     * Runs all checks if a student can be enrolled. If any conditions failed, an exception will be thrown.
     */
    public void runEnrollmentChecks(Student student) {
        checkArgument(student.isAbleToEnrollForMoreLessons(),
                String.format(STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT, student.getName()));
        checkArgument(!containsStudent(student),
                String.format(STUDENT_ALREADY_ENROLLED_CONSTRAINT, student.getName(), this));
        checkArgument(!doesStudentHaveConflictingTimings(student),
                String.format(CONFLICTING_TIMINGS_CONSTRAINT, student.getName(), this));
        checkArgument(isStudentOfSameGrade(student),
                String.format(DIFFERENT_GRADE_CONSTRAINT, student.getName(), this));
        checkArgument(isAbleToEnrollMoreStudents(),
                String.format(LESSON_ENROLLMENT_MESSAGE_CONSTRAINT, this));
    }

    /**
     * Add student to the lesson instance.
     */
    public void enrollStudent(Student student) {
        requireNonNull(student);

        runEnrollmentChecks(student); // use helper method to run checks
        addStudent(student);
    }

    /**
     * Removes student from the lesson instance.
     */
    public void unenrollStudent(Student student) {
        requireNonNull(student);
        checkArgument(isAbleToUnenroll(student),
                String.format(STUDENT_NOT_ENROLLED_CONSTRAINT, student.getName(), this));

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
        checkArgument(isAbleToUnenroll(oldStudent),
                String.format(STUDENT_NOT_ENROLLED_CONSTRAINT, oldStudent.getName(), this));

        for (Student student : students) {
            if (student.isSameStudent(oldStudent)) {
                removeStudent(oldStudent);

                runEnrollmentChecks(newStudent); // use helper method to run checks
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
