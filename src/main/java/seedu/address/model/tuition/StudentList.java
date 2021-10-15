package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Classes;

/**
 * Represents students who are in this tuition class
 */
public class StudentList {
    private final ArrayList<String> students;

    /**
     * Constructor for student class.
     *
     * @param students
     */
    public StudentList(List<String> students) {
        requireNonNull(students);
        this.students = (ArrayList<String>) students;
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
                && equalClasses(((StudentList) other).students)); // state check
    }

    /**
     *
     * @param cmpStudents
     * @return
     */
    public boolean equalClasses(ArrayList<String> cmpStudents) {
        if (cmpStudents.size() != this.students.size()) {
            return false;
        }
        for (int i = 0; i < students.size(); i++) {
            if (!cmpStudents.get(i).equals(this.students.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    /**
     * Returns whether or not Student List is empty.
     * @returns true if there are no students in the list, false otherwise
     */
    public boolean isEmpty() {
        return students.isEmpty();
    }
}
