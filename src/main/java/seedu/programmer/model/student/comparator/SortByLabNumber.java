package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.Lab;

/**
 * A class to help sort Students in ascending order.
 */
public class SortByLabNumber implements Comparator<Lab> {

    @Override
    public int compare(Lab l1, Lab l2) {
        int labNum1 = l1.getLabNum();
        int labNum2 = l2.getLabNum();

        if (labNum1 == labNum2) {
            return 0;
        }

        return labNum1 < labNum2 ? -1 : 1;
    }
}
