package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TeachingAssistantBuddy implements ReadOnlyTeachingAssistantBuddy {

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

    public TeachingAssistantBuddy() {}

    /**
     * Creates an TeachingAssistantBuddy using the Persons in the {@code toBeCopied}
     */
    public TeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code TeachingAssistantBuddy} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachingAssistantBuddy newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedPerson} must not be the same
     * as another existing student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TeachingAssistantBuddy}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachingAssistantBuddy // instanceof handles nulls
                && students.equals(((TeachingAssistantBuddy) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
