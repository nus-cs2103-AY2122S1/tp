package seedu.times.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.times.model.person.Name;
import seedu.times.model.person.Student;
import seedu.times.model.person.UniqueStudentList;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.model.tuitionclass.UniqueClassList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TimesTable implements ReadOnlyTimesTable {

    private final UniqueStudentList students;
    private final UniqueClassList classes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        classes = new UniqueClassList();
    }

    public TimesTable() {}

    /**
     * Creates a TimesTable using the Persons in the {@code toBeCopied}
     */
    public TimesTable(ReadOnlyTimesTable toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setStudents(List<Student> students) {
        this.students.setPersons(students);
    }

    public void setClasses(List<TuitionClass> classes) {
        this.classes.setClasses(classes);
    }

    /**
     * Resets the existing data of this {@code TimesTable} with {@code newData}.
     */
    public void resetData(ReadOnlyTimesTable newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setClasses(newData.getTuitionClassList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a tuition class with the same identity as {@code TuitionClass} exists in the address book.
     */
    public boolean hasTuitionClass(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return classes.contains(tuitionClass);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Adds a tuition class to the address book.
     * The tuition class must not already exist in the address book.
     */
    public void addTuitionClass(TuitionClass t) {
        classes.add(t);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TimesTable}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Student key) {
        students.remove(key);
        classes.removeStudent(key.getName());
    }

    public void deleteTuitionClass(TuitionClass toDelete) {
        classes.delete(toDelete);
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
    public ObservableList<TuitionClass> getTuitionClassList() {
        return classes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimesTable // instanceof handles nulls
                && students.equals(((TimesTable) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    public void setClass(TuitionClass target, TuitionClass editedClass) {
        requireNonNull(editedClass);
        classes.setClass(target, editedClass);
    }

    /**
     * Replaces the given old name in the list with a new name.
     *
     * @param newName New name to replace the given old name.
     * @param oldName Old name to be replaced by new name.
     */
    public void updateClassStudentLists(Name newName, Name oldName) {
        for (TuitionClass tuitionClass : classes) {
            tuitionClass.replaceStudentName(newName, oldName);
        }
    }
}
