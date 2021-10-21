package seedu.programmer.model.student;

import java.util.Comparator;

/**
 * A class to help sort Students in ascending order.
 */
public class SortByName implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().fullName.compareTo(s2.getName().fullName);
    }
}
