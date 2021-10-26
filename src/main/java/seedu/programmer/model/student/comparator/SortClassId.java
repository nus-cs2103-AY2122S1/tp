package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Student;

/**
 * A class to help sort Students by class in ascending order.
 */
public class SortClassId implements Comparator<ClassId> {

    @Override
    public int compare(ClassId c1, ClassId c2) {
        if (c1.getClassNum() == c2.getClassNum()) {
            return 0;
        }
        return c1.getClassNum() < c2.getClassNum() ? -1 : 1;
    }
}
