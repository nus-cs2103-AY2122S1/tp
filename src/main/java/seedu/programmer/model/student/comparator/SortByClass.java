package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.Student;

/**
 * Sorts Students by class in ascending order.
 */
public class SortByClass implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return s1.getClassIdValue().compareTo(s2.getClassIdValue());
    }
}
