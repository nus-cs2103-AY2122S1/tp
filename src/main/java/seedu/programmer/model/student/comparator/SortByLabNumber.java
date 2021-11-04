package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;

/**
 * A class to help sort Students in ascending order.
 */
public class SortByLabNumber implements Comparator<Lab> {

    @Override
    public int compare(Lab l1, Lab l2) {
        LabNum labNum1 = l1.getLabNum();
        LabNum labNum2 = l2.getLabNum();

        if (labNum1 == labNum2) {
            return 0;
        }

        return labNum1.getLabNum() < labNum2.getLabNum() ? -1 : 1;
    }
}
