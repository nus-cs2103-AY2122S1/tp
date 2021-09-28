package seedu.address.model.Tuition;

import seedu.address.model.person.Classes;
import seedu.address.model.person.Person;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

/**
 * Represents students who are in this tuition class
 */
public class Student {
    public final ArrayList<Person> students;

    public Student(ArrayList<Person> students) {
        requireNonNull(students);
        this.students = students;
    }

    @Override
    public String toString() {
        return students.toString();
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Classes // instanceof handles nulls
                && equalClasses(((Student) other).students)); // state check
    }

    public boolean equalClasses(ArrayList<Person> cmpStudents) {
        if (cmpStudents.size() != this.students.size())
            return false;
        for (int i = 0; i < students.size(); i++) {
            if (!cmpStudents.get(i).equals(this.students.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Person> getStudents() {
        return students;
    }
}
