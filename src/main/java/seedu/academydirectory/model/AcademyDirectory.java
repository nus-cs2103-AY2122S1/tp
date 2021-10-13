package seedu.academydirectory.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.UniqueStudentList;

/**
 * Wraps all data at the academy-directory level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AcademyDirectory implements ReadOnlyAcademyDirectory {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public AcademyDirectory() {}

    /**
     * Creates an AcademyDirectory using the Students in the {@code toBeCopied}
     */
    public AcademyDirectory(ReadOnlyAcademyDirectory toBeCopied) {
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
     * Resets the existing data of this {@code AcademyDirectory} with {@code newData}.
     */
    public void resetData(ReadOnlyAcademyDirectory newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the academy directory.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the academy directory.
     * The student must not already exist in the academy directory.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the academy directory.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * academy directory.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AcademyDirectory}.
     * {@code key} must exist in the academy directory.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademyDirectory // instanceof handles nulls
                && students.equals(((AcademyDirectory) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
