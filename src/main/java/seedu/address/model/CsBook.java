package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class CsBook implements ReadOnlyCsBook {

    private final UniqueStudentList students;
    private final UniqueGroupList groups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        groups = new UniqueGroupList();
    }

    public CsBook() {}

    /**
     * Creates an CsBook using the Students in the {@code toBeCopied}
     */
    public CsBook(ReadOnlyCsBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudent(students);
    }

    /**
     * Replaces the contents of the student list with {@code groups}.
     * {@code students} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroup(groups);
    }


    /**
     * Resets the existing data of this {@code CsBook} with {@code newData}.
     */
    public void resetData(ReadOnlyCsBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setGroups(newData.getGroupList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the CS book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return this.students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the CS book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the CS
     * book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code CsBook}.
     * {@code key} must exist in the CSBook.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// group-level operations

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return this.groups.contains(group);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    /**
     * Removes {@code key} from this {@code CsBook}.
     * {@code key} must exist in the address book.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireNonNull(editedGroup);

        groups.setGroup(target, editedGroup);
    }

    /**
     * Returns true if a student with the same identity as {@code assessment} exists in the student's assessment list.
     */
    public boolean hasAssessment(Student student, Assessment assessment) {
        requireNonNull(student);
        requireNonNull(assessment);
        return student.hasAssessment(assessment);
    }

    /**
     * Adds an assessment to a student.
     * The assessment must not already exist in the student's assessment list.
     */
    public void addAssessment(Student student, Assessment assessment) {
        requireNonNull(student);
        requireNonNull(assessment);
        student.addAssessment(assessment);
    }

    /**
     * Removes {@code assessment} from the {@code student}.
     * {@code assessment} must exist in the student's assessment list.
     */
    public void deleteAssessment(Student student, Assessment assessment) {
        requireNonNull(student);
        requireNonNull(assessment);
        student.deleteAssessment(assessment);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CsBook // instanceof handles nulls
                && students.equals(((CsBook) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
