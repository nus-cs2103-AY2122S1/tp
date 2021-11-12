package seedu.teachbook.model.classobject;

import java.util.Comparator;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.student.UniqueStudentList;

/**
 * Represents a Class in the TeachBook.
 */
public class Class {

    private final ClassName className;
    private UniqueStudentList studentsOfThisClass;

    /**
     * Constructs a class with the specified class name.
     *
     * @param className name of the class.
     */
    public Class(ClassName className) {
        this.className = className;
        this.studentsOfThisClass = new UniqueStudentList();
    }

    public ClassName getClassName() {
        return className;
    }

    public UniqueStudentList getUniqueStudentListOfThisClass() {
        return studentsOfThisClass;
    }

    public ObservableList<Student> getStudentsOfThisClass() {
        return studentsOfThisClass.asUnmodifiableObservableList();
    }

    public void setStudentsOfThisClass(UniqueStudentList studentsOfThisClass) {
        this.studentsOfThisClass = studentsOfThisClass;
    }

    public int getClassSize() {
        return this.studentsOfThisClass.size();
    }

    public void addStudent(Student student) {
        this.studentsOfThisClass.add(student);
    }

    public void removeStudent(Student student) {
        this.studentsOfThisClass.remove(student);
    }

    public boolean containsStudent(Student student) {
        return studentsOfThisClass.contains(student);
    }

    public void setStudent(Student target, Student editedStudent) {
        studentsOfThisClass.setStudent(target, editedStudent);
    }

    /**
     * Sorts the student list of this class according to the order induced by the specified comparator.
     */
    public void reorderStudents(Comparator<? super Student> comparator) {
        studentsOfThisClass.sort(comparator);
    }

    /**
     * Returns true if both classes have the same name.
     * This defines a weaker notion of equality between two classes.
     *
     * @param otherClass other class to be checked against.
     * @return {@code true} if both classes have the same name.
     */
    public boolean isSameClass(Class otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getClassName().equals(getClassName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Class)) {
            return false;
        }

        Class otherClass = (Class) other;
        return otherClass.getClassName().equals(this.className)
                && otherClass.getUniqueStudentListOfThisClass().equals(this.studentsOfThisClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, studentsOfThisClass);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassName());
        return builder.toString();
    }

}
