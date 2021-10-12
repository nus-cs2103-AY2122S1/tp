package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.Grade;
import seedu.address.model.person.Student;

/**
 * Represents a Lesson in the tuitiONE book.
 */
public class Lesson {

    public static final String ENROLLMENT_MESSAGE_CONSTRAINT = "%1$s is unable to enroll for this lesson";
    public static final String STUDENT_NOT_ENROLLED = "%1$s is not enrolled for %2$s";

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
     * Returns an unmodifiable set of student for equality checks.
     */
    public List<Student> getStudents() {
        return students.stream().collect(Collectors.toUnmodifiableList());
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
        return lessonCode.equals(otherLesson.lessonCode);
    }

    /**
     * Returns true if a student is eligible to enroll for the lesson.
     * A student must be of the same grade, must be available for the time slot,
     * and must not be already enrolled to the lesson.
     */
    public boolean isAbleToEnroll(Student student) {
        if (student == null) {
            return false;
        }
        if (containsStudent(student)) {
            return false;
        }
        if (!student.getGrade().equals(grade)) {
            return false;
        }
        for (LessonCode code : student.getLessonCodes()) {
            LessonTime time = LessonCode.getLessonTimeFromCode(code);
            if (time.hasOverlappedTiming(lessonTime)) {
                return false;
            }
        }
        return true;
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
        return students.stream().anyMatch(s -> student.getName().equals(s.getName()));
    }

    /**
     * Add student to the lesson instance.
     * Lesson instance holds a stronger linkage to Students, storing Student entities.
     */
    public void addStudent(Student student) {
        requireNonNull(student);
        checkArgument(isAbleToEnroll(student), ENROLLMENT_MESSAGE_CONSTRAINT);
        students.add(student);
        student.enrollForLesson(this);
    }

    /**
     * Removes student from the lesson instance.
     * Lesson instance holds a stronger linkage to Students, tracking Student entities.
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        checkArgument(isAbleToUnenroll(student), String.format(STUDENT_NOT_ENROLLED, student, this));
        for (Student s : students) {
            if (s.isSamePerson(student)) {
                students.remove(s);
                s.unenrollFromLesson(this);
                student.unenrollFromLesson(this);
                break;
            }
        }
    }

    /**
     * Removes all students from lesson.
     */
    public void removeAll() {
        while (!students.isEmpty()) {
            Student currStudent = students.get(0);
            this.removeStudent(currStudent);
        }
    }

    /**
     * Returns a clone of the Lesson instance.
     */
    public Lesson createClone() {
        Lesson clone = new Lesson(subject, grade, lessonTime, price);
        clone.students.addAll(students);
        return clone;
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
