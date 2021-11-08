package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.Student;

/**
 * Sorts Students by their name in ascending order.
 */
public class SortByStudentName implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return s1.getNameValue().compareTo(s2.getNameValue());
    }
}
