package seedu.teachbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.UniqueClassList;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.student.UniqueStudentList;

/**
 * Wraps all data at the TeachBook level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TeachBook implements ReadOnlyTeachBook {

    private final UniqueClassList classes;
    private UniqueStudentList students; // different from AB3: this variable is for "list all" command only!
    private GradingSystem gradingSystem;

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
        gradingSystem = new GradingSystem();
    }

    public TeachBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public TeachBook(ReadOnlyTeachBook toBeCopied) {
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
     * Replaces the contents of the class list with {@code classes}.
     * {@code classes} must not contain duplicate classes.
     */
    public void setClasses(List<Class> classes) {
        this.classes.setClasses(classes);
    }

    /**
     * Removes the contents of the gradingSystem in the class with {@code classIndex}.
     */
    public void resetGradingSystem(GeneralIndex classIndex) {
        this.gradingSystem = new GradingSystem();
        getClassList().forEach(studentClass -> studentClass.getStudentsOfThisClass().forEach(student -> {
            Student editedStudent = new Student(student.getName(), student.getPhone(),
                    student.getStudentClass(), student.getEmail(), student.getAddress(),
                    student.getRemark(), student.getTags(), student.getAttendance(), NOT_GRADED);
            setStudent(classIndex, student, editedStudent);
        }));
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachBook newData) {
        requireNonNull(newData);
        ObservableList<Class> copy = FXCollections.observableArrayList();
        for (Class c : newData.getClassList()) {
            Class newClass = new Class(c.getClassName());
            for (Student s : c.getStudentsOfThisClass()) {
                Student studentToAdd = new Student(s.getName(), s.getPhone(), newClass, s.getEmail(),
                        s.getAddress(), s.getRemark(), s.getTags(), s.getAttendance(), s.getGrade());
                newClass.addStudent(studentToAdd);
            }
            copy.add(newClass);
        }
        setClasses(copy);

        List<Grade> gradeListCopy = new ArrayList<>();
        for (Grade g : newData.getGradingSystem().getGradeList()) {
            Grade newGrade = new Grade(g.value);
            gradeListCopy.add(newGrade);
        }
        setGradingSystem(new GradingSystem(gradeListCopy));
    }

    //// class-level operations

    /**
     * Adds a class to the TeachBook.
     * The class must not already exist in the TeachBook.
     */
    public void addClass(Class toAdd) {
        classes.add(toAdd);
    }

    /**
     * Removes {@code target} from this {@code TeachBook}.
     * {@code target} must exist in the TeachBook.
     */
    public void removeClass(Class target) {
        classes.remove(target);
    }

    /**
     * Replaces the name of given class with index {@code classIndex} in the list with {@code updatedClassName}.
     * {@code classIndex} must exist in the TeachBook.
     * The name specified in {@code updatedClassName} must not be the same as
     * another existing className in the TeachBook.
     */
    public void setClassName(GeneralIndex classIndex, ClassName updatedClassName) {
        Class target = getClassAtIndex(classIndex);
        Class editedClass = new Class(updatedClassName);
        UniqueStudentList newStudentList = target.getUniqueStudentListOfThisClass();
        newStudentList.forEach(student -> student.setStudentClass(editedClass));
        editedClass.setStudentsOfThisClass(newStudentList);
        setClass(target, editedClass);
    }

    /**
     * Getter method to return current number of classes.
     *
     * @return number of classes
     */
    public int getNumOfClasses() {
        return classes.size();
    }

    @Override
    public ObservableList<Class> getClassList() {
        return classes.asUnmodifiableObservableList();
    }

    /**
     * Returns true if a class with the same identity as {@code classObj} exists in the TeachBook.
     */
    public boolean hasClass(Class classObj) {
        requireNonNull(classObj);
        return classes.contains(classObj);
    }

    /**
     * Sets the target class to the edited class.
     *
     * @param target the class to be change.
     * @param editedClass the class to be replaced with.
     */
    public void setClass(Class target, Class editedClass) { // for editClass command
        requireNonNull(editedClass);
        classes.setClass(target, editedClass);
    }

    /**
     * Getter method for index of class with specified {@code className}.
     *
     * @param className of class to get index of.
     * @return General index of specified class.
     * @throws NoClassWithNameException thrown when classname does not exist in class list
     */
    public GeneralIndex getIndexOfClass(ClassNameDescriptor className) throws NoClassWithNameException {
        requireNonNull(className);
        return classes.locateClass(className);
    }

    /**
     * Getter method for class with specified {@code classIndex}.
     *
     * @param classIndex of class to get.
     * @return Class at specified index.
     */
    public Class getClassAtIndex(GeneralIndex classIndex) {
        return classes.getClassAtIndex(classIndex);
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TeachBook.
     */
    public boolean hasStudent(GeneralIndex classIndex, Student student) {
        if (classIndex.equals(INDEX_LIST_ALL)) {
            return students.contains(student);
        } else {
            assert getClassAtIndex(classIndex).equals(student.getStudentClass());
        }
        return student.getStudentClass().containsStudent(student);
    }

    /**
     * Adds a student to the TeachBook.
     * The student must not already exist in the TeachBook.
     */
    public void addStudent(GeneralIndex classIndex, Student studentToAdd) {
        requireAllNonNull(classIndex, studentToAdd);
        assert getClassAtIndex(classIndex).equals(studentToAdd.getStudentClass());
        studentToAdd.getStudentClass().addStudent(studentToAdd);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the TeachBook.
     * The student identity of {@code editedPerson} must not be the same as
     * another existing student in the TeachBook.
     */
    public void setStudent(GeneralIndex classIndex, Student target, Student editedStudent) {
        requireAllNonNull(classIndex, target, editedStudent);
        if (classIndex.equals(INDEX_LIST_ALL)) {
            students.setStudent(target, editedStudent); // edit twice
        }
        target.getStudentClass().setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TeachBook}.
     * {@code key} must exist in the TeachBook.
     */
    public void removeStudent(GeneralIndex classIndex, Student key) {
        if (classIndex.equals(INDEX_LIST_ALL)) {
            students.remove(key); // delete twice
        } else {
            assert getClassAtIndex(classIndex).equals(key.getStudentClass());
        }
        key.getStudentClass().removeStudent(key);
    }

    /**
     * Sets the class of {@code student} to the Class with index {@code classIndex}.
     *
     * @param classIndex of class to set to.
     * @param student to set class for.
     */
    public void setClassForStudent(GeneralIndex classIndex, Student student) {
        student.setStudentClass(getClassAtIndex(classIndex));
    }

    /**
     * Reorders the list of students currently displayed.
     *
     * @param classIndex of class to be reordered.
     * @param comparator to set the way to be reordered.
     */
    public void reorderStudents(GeneralIndex classIndex, Comparator<? super Student> comparator) {
        if (classIndex.equals(INDEX_LIST_ALL)) {
            students.sort(comparator);
        } else {
            getClassAtIndex(classIndex).reorderStudents(comparator);
        }
    }

    /**
     * Getter method for student list in specified class at index {@code classIndex}.
     *
     * @param classIndex of class to get student list from.
     * @return list of students.
     */
    public ObservableList<Student> getStudentListOfClass(GeneralIndex classIndex) {
        return classes.getClassAtIndex(classIndex).getStudentsOfThisClass();
    }

    //// Grade System methods

    /**
     * Getter method for Grading System
     *
     * @return Grading system of current TeachBook
     */
    public GradingSystem getGradingSystem() {
        return new GradingSystem(Collections.unmodifiableList(gradingSystem.getGradeList()));
    }

    /**
     * Setter method for Grading System.
     *
     * @param newGradingSystem to set the grading system to.
     */
    public void setGradingSystem(GradingSystem newGradingSystem) {
        gradingSystem = newGradingSystem;
    }

    /**
     * Returns true when there is already a grading system set.
     */
    public boolean hasExistingGradingSystem() {
        return gradingSystem.isInUse();
    }

    /**
     * Checks if grade provided exist in grading system used.
     *
     * @param grade to check
     * @return validity of grade
     */
    public boolean isValidGrade(Grade grade) {
        return gradingSystem.isValidGrade(grade);
    }

    //// util methods
    @Override
    public String toString() {
        return classes.asUnmodifiableObservableList().size() + " classes";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        generateStudentList();
        return students.asUnmodifiableObservableList();
    }

    /**
     * Generates student list.
     */
    public void generateStudentList() {
        students = new UniqueStudentList();
        for (Class studentClass : classes) {
            for (Student student : studentClass.getStudentsOfThisClass()) {
                students.add(student);
            }
        }
    }

    /**
     * Generates student list.
     *
     * @return True if class list is empty and if there is not grading system in use.
     */
    public boolean isEmpty() {
        return classes.isEmpty() && !gradingSystem.isInUse();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachBook // instanceof handles nulls
                && classes.equals(((TeachBook) other).classes)
                && students.equals(((TeachBook) other).students)
                && gradingSystem.equals(((TeachBook) other).gradingSystem));
    }

    @Override
    public int hashCode() {
        return Objects.hash(classes, students, gradingSystem);
    }

}
