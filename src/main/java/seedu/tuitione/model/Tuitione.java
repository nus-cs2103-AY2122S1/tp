package seedu.tuitione.model;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.UniqueLessonList;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.model.student.UniqueStudentList;
import seedu.tuitione.model.student.exceptions.DuplicateLessonException;


/**
 * Wraps all data at the tuitione-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Tuitione implements ReadOnlyTuitione {

    private final UniqueStudentList students;

    private final UniqueLessonList lessons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        lessons = new UniqueLessonList();
    }

    public Tuitione() {}

    /**
     * Creates an Tuitione using the Students in the {@code toBeCopied}
     */
    public Tuitione(ReadOnlyTuitione toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code Tuitione} with {@code newData}.
     */
    public void resetData(ReadOnlyTuitione newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setLessons(newData.getLessonList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the tuitione book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the tuitione book.
     * The student must not already exist in the tuitione book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the tuitione book.
     * The student identity of {@code editedStudent}
     * must not be the same as another existing student in the tuitione book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Tuitione}.
     * {@code key} must exist in the tuitione book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// lesson-level operations

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in TuitiONE.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        for (Lesson l : lessons) {
            // weak equality check
            if (l.isSameLesson(lesson)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a lesson to TuitiONE.
     * The student must not already exist in the tuitione book.
     */
    public void addLesson(Lesson l) {
        lessons.add(l);
    }

    /**
     * Removes a lesson from this {@code TuitiONE}.
     * lesson must exist in TuitiONE.
     */
    public void removeLesson(Lesson key) {
        lessons.remove(key);
    }

    /**
     * Replaces the contents of the lesson list with {@code lesson}.
     * {@code lesson} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        requireAllNonNull(lessons);
        if (!lessonsAreUnique(lessons)) {
            throw new DuplicateLessonException();
        }
        this.lessons.setLessons(lessons);
    }

    public void setLesson(Lesson target, Lesson editedLesson) {
        requireNonNull(editedLesson);
        lessons.setLesson(target, editedLesson);
    }

    /**
     * Returns true if {@code lessons} contains only unique lessons.
     */
    public boolean lessonsAreUnique(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isSameLesson(lessons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    //// util methods

    @Override
    public String toString() {
        return "Tuitione["
                + students.asUnmodifiableObservableList().size() + " students, "
                + lessons.asUnmodifiableObservableList().size() + " lessons]";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tuitione // instanceof handles nulls
                && students.equals(((Tuitione) other).students))
                && lessons.equals(((Tuitione) other).lessons);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

}
